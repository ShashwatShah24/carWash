package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 /*   private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(Home.this, Home.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_services:
                    mTextMessage.setText(R.string.title_dashboard);
                    Intent i2 = new Intent(Home.this, Services.class);
                    startActivity(i2);
                    return true;
                case R.id.navigation_waiting:
                    mTextMessage.setText(R.string.title_notifications);
                   *//* Intent i3 = new Intent(HomeBottomNavigation.this, Services.class);
                    startActivity(i3);*//*
                    return true;
            }
            return false;
        }
    };
*/


    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardView=(CardView) findViewById(R.id.cv1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Home.this,Waiting.class);

                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Home.this,About.class);

                startActivity(intent);
            }
        });

       /* mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     /*   mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

   /* @Override
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i1 = new Intent(Home.this, Home.class);
            Toast.makeText(getApplicationContext(), "Home Selected",
                    Toast.LENGTH_LONG).show();
            startActivity(i1);
        } else if (id == R.id.nav_services) {
            Intent i2 = new Intent(Home.this, ServicesCardView.class);
            Toast.makeText(getApplicationContext(), "Services Selected",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        } else if (id == R.id.nav_waiting) {
          Intent i2 = new Intent(Home.this, Waiting.class);
            Toast.makeText(getApplicationContext(), "Waiting selected",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        } /*else if (id == R.id.nav_settings) {
            Intent i2 = new Intent(Home.this, Settings.class);
            Toast.makeText(getApplicationContext(), "Select your language",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        }*/ else if (id == R.id.nav_feeback) {
            Intent i2 = new Intent(Home.this, feedback.class);
            Toast.makeText(getApplicationContext(), "Send us feedback",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        } else if (id == R.id.nav_about) {
            Intent i2 = new Intent(Home.this, About.class);
            Toast.makeText(getApplicationContext(), "About Selected",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        } else if (id == R.id.nav_logout) {
            Intent i2 = new Intent(Home.this, MainActivity.class);
            Toast.makeText(getApplicationContext(), "Logging out",
                    Toast.LENGTH_LONG).show();
            startActivity(i2);
        }
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

      /*  Button next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }

        });*/

    }
}
