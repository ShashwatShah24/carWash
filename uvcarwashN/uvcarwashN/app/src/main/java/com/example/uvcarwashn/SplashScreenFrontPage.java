package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenFrontPage extends AppCompatActivity {

    private final static int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_front_page);

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreenFrontPage.this,Services.class);
                startActivity(intent);
                finish();
            }
        })*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenFrontPage.this,MainActivity.class);
                SplashScreenFrontPage.this.startActivity(mainIntent);
                SplashScreenFrontPage.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
