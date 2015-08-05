package com.alexcompany.contacts.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alexcompany.contacts.contacts.model.Contact;
import com.alexcompany.contacts.contacts.model.ContactAdapter;

import java.util.ArrayList;


public class MainActivity extends Activity {

    public static RecyclerView recyclerView;
    public static RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Alex", "Lopez", "2305 Alexader Farms, Marietta", "546-972-215", "alex@gmail.com"));
        contactList.add(new Contact("Fernanda", "Ferrer", "2305 Alexader Farms, Marietta", "717-762-934", "fer@gmail.com"));
        contactList.add(new Contact("Arturo", "Monti", "2305 Alexader Farms, Marietta", "192-838-827", "artur@gmail.com"));

        adapter = new ContactAdapter(contactList);

        recyclerView.setAdapter(adapter);

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
}
