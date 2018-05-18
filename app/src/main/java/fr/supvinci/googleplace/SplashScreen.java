package fr.supvinci.googleplace;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreen extends Activity{

    public static int SPLASH_TIME_OUT = 3000;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            public void run(){
                Intent i = new Intent(SplashScreen.this, MapsActivity.class);
                startActivity(i);
                finish();
            }

        },SPLASH_TIME_OUT);
    }
}


