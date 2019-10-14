package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeBottomNavigation extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(HomeBottomNavigation.this, Home.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_services:
                    mTextMessage.setText(R.string.title_dashboard);
                    Intent i2 = new Intent(HomeBottomNavigation.this, Services.class);
                    startActivity(i2);
                    return true;
                case R.id.navigation_waiting:
                    mTextMessage.setText(R.string.title_notifications);
                   /* Intent i3 = new Intent(HomeBottomNavigation.this, Services.class);
                    startActivity(i3);*/
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bottom_navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
