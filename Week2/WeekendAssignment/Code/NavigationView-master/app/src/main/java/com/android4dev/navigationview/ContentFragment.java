package com.android4dev.navigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android4dev.navigationview.app.AppController;
import com.android4dev.navigationview.model.Product;
import com.android4dev.navigationview.model.adpaters.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {

    private static final String URL_JSON_ARRAY_PRODUCT = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/anyproduct_details.json?catid=1760169";

    private static final String TAG_ASSOCIATED_PRODUCTS = "AssociatedProducts";
    private static final String TAG_BASE_PRICE = "BasePrice";
    private static final String TAG_PRODUCT_IMG_URL = "ProductImageUrls";
    public static RecyclerView recyclerView;
    public static RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter adapter;

    private ArrayList<Product> productList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.products_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        productList = new ArrayList<Product>();

        /*productList.add(new Product("http://images.asos.com/inv/media/4/6/2/3/1703264/bakedapple/image1l.jpg", "$15.00"));
        productList.add(new Product("http://images.asos.com/inv/media/4/6/2/3/1703264/bakedapple/image1l.jpg","$43.00"));
        productList.add(new Product("http://images.asos.com/inv/media/4/6/2/3/1703264/bakedapple/image1l.jpg","$32.00"));
        productList.add(new Product("http://images.asos.com/inv/media/4/6/2/3/1703264/bakedapple/image1l.jpg","$100.00"));
        productList.add(new Product("http://images.asos.com/inv/media/4/6/2/3/1703264/bakedapple/image1l.jpg","$34.00"));
        */

        makeJsonObjReq();

        adapter = new ProductAdapter(getActivity(), productList);
        recyclerView.setAdapter(adapter);
        return v;
    }


    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                URL_JSON_ARRAY_PRODUCT,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(MainActivity.class.getSimpleName(), "RESPUESTA: " + response.toString());
                        //Toast.makeText(getActivity(), "RESPUESTA: " + response.toString(), Toast.LENGTH_LONG).show();

                        String jsonStr = response.toString();

                        if (jsonStr != null) {

                            try {

                                JSONArray products = response.getJSONArray(TAG_ASSOCIATED_PRODUCTS);

                                for (int i = 0; i < products.length(); i++) {
                                    JSONObject jo = products.getJSONObject(i);
                                    Product product = new Product(jo.getJSONArray(TAG_PRODUCT_IMG_URL).get(0).toString(), jo.getString(TAG_BASE_PRICE));
                                    productList.add(product);
                                    //Log.d(getActivity().class.getSimpleName(), "Product " + product);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("ServiceHandler", "Couldn't get any data from the url");
                        }

                        adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + error.getMessage());
                        Toast.makeText(getActivity(), "ERROR!!! ", Toast.LENGTH_LONG).show();
                    }
                });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, "");
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }


}
