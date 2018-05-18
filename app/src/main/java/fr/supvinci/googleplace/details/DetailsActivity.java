package fr.supvinci.googleplace.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.supvinci.googleplace.R;

public class DetailsActivity extends AppCompatActivity {

    // délcaration
    private TextView textViewTitle;
    private ListView listViewData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewData = findViewById(R.id.listViewData);

        Log.d("INTENT","googlePlace====>" + getIntent() + "]");
        // check intent extras
        if (getIntent().getExtras() != null) {
            boolean intentGooglePlace = getIntent().getExtras().getBoolean("intentGooglePlace");

            if (intentGooglePlace) {
                textViewTitle.setText(R.string.listing_title_restaurant);


            } else {
                textViewTitle.setText(R.string.listing_title_hotels);
            }

        }


    }

}