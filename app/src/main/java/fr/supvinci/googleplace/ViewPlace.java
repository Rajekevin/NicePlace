package fr.supvinci.googleplace;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ViewPlace extends AppCompatActivity {

    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        photo = (ImageView)findViewById(R.id.photo);

        if(getIntent().getExtras() != null) {
            HashMap<String, String> googlePlace = (HashMap<String, String>) getIntent().getExtras().get("googlePlace");

            Toast.makeText(this, googlePlace.get("place_name"), Toast.LENGTH_SHORT).show();

            TextView textViewPlaceName = (TextView) findViewById(R.id.place_name);
            textViewPlaceName.setText(googlePlace.get("place_name"));

            TextView textViewPlaceAdress = (TextView) findViewById(R.id.address);
            textViewPlaceAdress.setText(googlePlace.get("vicinity"));


            // affichage de l'image
           /* Picasso.with(ViewPlace.this)
                    .load(item.getImage())
                    .into(photo);*/

         /*   TextView textViewOH = (TextView) findViewById(R.id.open_hour);
            textViewOH.setText(googlePlace.get("open_now"));*/

        }
    }

    public String getPhotoOfPlace(String photo_reference, int maxWidth){
        StringBuilder url =new  StringBuilder("https://maps.googleapis.com/maps/api/photo/place/photo");
        url.append("?maxWidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+"AIzaSyDx-HUuhr_CSY_umdJVYY0xxCIT46BVDYs"); //Google Photo Api
            return url.toString();
        }
}
