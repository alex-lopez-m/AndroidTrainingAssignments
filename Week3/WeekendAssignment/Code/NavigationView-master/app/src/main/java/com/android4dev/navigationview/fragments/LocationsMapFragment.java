package com.android4dev.navigationview.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.android4dev.navigationview.MainActivity;
import com.android4dev.navigationview.R;
import com.android4dev.navigationview.app.AppController;
import com.android4dev.navigationview.model.Product;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android1 on 8/16/2015.
 */
public class LocationsMapFragment extends Fragment {

    private GoogleMap googleMap;

    private static final String URL_JSON_OBJECT_MARKERS = "http://10.1.10.76/ASOS/get_all_products.php";

    private static final String TAG_MARKERS = "markers";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_LAT = "lat";
    private static final String TAG_LNG = "lng";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.locations_map, container, false);
        initilizeMap(v);
        if(googleMap != null) {
            makeJsonObjReq();
        }
        return v;
    }

    private void initilizeMap(View v) {
        if (googleMap == null) {

            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                URL_JSON_OBJECT_MARKERS,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(MainActivity.class.getSimpleName(), "RESPUESTA: " + response.toString());
                        //Toast.makeText(getActivity(), "RESPUESTA: " + response.toString(), Toast.LENGTH_LONG).show();

                        String jsonStr = response.toString();

                        if (jsonStr != null) {

                            try {

                                JSONArray markers = response.getJSONArray(TAG_MARKERS);

                                for (int i = 0; i < markers.length(); i++) {
                                    JSONObject m = markers.getJSONObject(i);

                                    googleMap.addMarker(
                                            new MarkerOptions()
                                            .position(new LatLng(m.getDouble(TAG_LAT), m.getDouble(TAG_LNG)))
                                                    .title(m.getString(TAG_NAME))
                                                    .snippet(m.getString(TAG_ADDRESS)));

                                }

                                JSONObject m = markers.getJSONObject(markers.length() - 1 );

                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(new LatLng(m.getDouble(TAG_LAT), m.getDouble(TAG_LNG))).zoom(12).build();

                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("ServiceHandler", "Couldn't get any data from the url");
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + error.getMessage());
                        Log.d(MainActivity.class.getSimpleName(), "ERROR: " + error.getMessage());
                        Toast.makeText(getActivity(), "ERROR!!! ", Toast.LENGTH_LONG).show();
                    }
                });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, "");
    }

}
