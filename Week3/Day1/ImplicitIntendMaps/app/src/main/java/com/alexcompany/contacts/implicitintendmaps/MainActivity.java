package com.alexcompany.contacts.implicitintendmaps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    Button btGoToMap;
    Button btSearch;
    Button btCategorical;
    Button btNavigation;
    Button btNavigateByVicycle;
    EditText etSource;
    EditText etDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGoToMap = (Button) findViewById(R.id.bt_go_to_maps);
        btSearch = (Button) findViewById(R.id.bt_search);
        btCategorical = (Button) findViewById(R.id.bt_categorical);
        btNavigation = (Button) findViewById(R.id.bt_navigation);
        btNavigateByVicycle = (Button) findViewById(R.id.bt_navigate_by_bicycle);
        etSource = (EditText) findViewById(R.id.et_source);
        etDestination = (EditText) findViewById(R.id.et_destination);

        btGoToMap.setOnClickListener(new GoToMapButtonListener());
        btSearch.setOnClickListener(new SearchButtonListener());
        btCategorical.setOnClickListener(new CategoricalButtonListener());
        btNavigation.setOnClickListener(new NavigationButtonListener());
        btNavigateByVicycle.setOnClickListener(new NavigateByVicycleButtonListener());
    }

    class GoToMapButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Creates an Intent that will load a map of San Francisco
            Uri gmmIntentUri = Uri.parse("geo:35.652832,139.839478?z=8");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }

    class SearchButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Use this intent to display search queries within a specified viewport
            Uri gmmIntentUri = Uri.parse("geo:0,0?z=8&q=Jersey City");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }

    class CategoricalButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Search for restaurants nearby
            Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=restaurants");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }

    class NavigationButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Alexander+Farms+Ct+SW,+Marietta,+GA&mode=b");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }

    class NavigateByVicycleButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            String sourceStr = etSource.getText().toString();
            String destinationStr = etDestination.getText().toString();

            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destinationStr +  "&mode=b");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
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
