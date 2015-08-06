package com.alexcompany.contacts.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexcompany.contacts.contacts.app.AppController;
import com.alexcompany.contacts.contacts.model.Contact;
import com.alexcompany.contacts.contacts.model.ContactAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    public static RecyclerView recyclerView;
    public static RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter adapter;

    private static final String URL_JSON_ARRAY_CONTACT = "http://fast-gorge.herokuapp.com/contacts"; //"http://api.androidhive.info/contacts";

    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_FIRST_NAME = "first_name";
    private static final String TAG_SURNAME = "surname";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_PHONE_NUMBER = "phone_number";
    private static final String TAG_EMAIL = "email";

    private ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        contactList = new ArrayList<Contact>();

        /*
        contactList.add(new Contact("Alex", "Lopez", "2305 Alexader Farms, Marietta", "546-972-215", "alex@gmail.com"));
        contactList.add(new Contact("Fernanda", "Ferrer", "2305 Alexader Farms, Marietta", "717-762-934", "fer@gmail.com"));
        contactList.add(new Contact("Arturo", "Monti", "2305 Alexader Farms, Marietta", "192-838-827", "artur@gmail.com"));
        */

        adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);

        makeJsonArrayReq();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeJsonArrayReq(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                URL_JSON_ARRAY_CONTACT,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        Log.d(MainActivity.class.getSimpleName(), "RES: " + response.toString());
                        //Toast.makeText(MainActivity.this,"RES: " + response.toString(),Toast.LENGTH_LONG).show();

                        String jsonStr = response.toString();

                        if (jsonStr != null) {

                            try {

                                JSONArray contacts = new JSONArray(jsonStr);

                                for (int i = 0; i < contacts.length(); i++) {

                                    JSONObject c = contacts.getJSONObject(i);

                                    Contact contact = new Contact();
                                    contact.setFirstName(c.getString(TAG_FIRST_NAME));
                                    contact.setSurname(c.getString(TAG_SURNAME));
                                    contact.setAddress(c.getString(TAG_ADDRESS));
                                    contact.setPhoneNumber(c.getString(TAG_PHONE_NUMBER));
                                    contact.setEmail(c.getString(TAG_EMAIL));

                                    //Log.d(MainActivity.class.getSimpleName(), "Contact " + contact);

                                    contactList.add(contact);

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
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + volleyError.getMessage());
                        Log.d(MainActivity.class.getSimpleName(), "ERROR");
                        Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_LONG).show();
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest, "");
    }

    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                URL_JSON_ARRAY_CONTACT,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(MainActivity.class.getSimpleName(), "RESPUESTA: " + response.toString());
                        Toast.makeText(MainActivity.this,"RESPUESTA: " + response.toString(),Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + error.getMessage());

                    }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,"");
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }
}
