package fr.supvinci.googleplace;

import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.google.android.gms.common.util.ArrayUtils.contains;

public class GetPlaces extends AsyncTask<Object, String, String> {

    private String googlePlacesData;
    private GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects){
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];



        UrlLoad downloadURL = new UrlLoad();
        try {
            googlePlacesData = downloadURL.readUrl(url);
            Log.d("urlRK", googlePlacesData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","donnée nearbyplacelist");
        showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {

        Log.d("NB",nearbyPlaceList.toString());
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

           // Log.d("typeGplace", googlePlace.get("type").toString());
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ vicinity);


         int   checkShopping = googlePlace.get("types").contains("shopping_mall") ? 1 : 2;
            if(checkShopping == 1 ){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_shopping));
            }


           int checkCafe = googlePlace.get("types").contains("cafe") ? 1 : 2;
           if(checkCafe == 1 ){

                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cafe));

            }

            int checkRestaurant = googlePlace.get("types").contains("restaurant") ? 1 : 2;
            if(checkRestaurant == 1 ){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_resto));
               // markerOptions.icon(Images.from)
            }


           if(Arrays.asList(googlePlace.get("types")).contains("shopping_mall")){
                Log.d("RKTAG",googlePlace.get("types"));
            }
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


            Marker marker = mMap.addMarker(markerOptions);
            marker.setTag(googlePlace);


            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
