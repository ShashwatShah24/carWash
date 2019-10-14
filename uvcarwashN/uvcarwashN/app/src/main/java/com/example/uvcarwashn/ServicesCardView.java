package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class ServicesCardView extends AppCompatActivity {

    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_card_view);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        cardView=(CardView) findViewById(R.id.cv1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServicesCardView.this,CarWashing2.class);

                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServicesCardView.this,InteriorCleaning2.class);
                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServicesCardView.this,Rubbing2.class);
                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv4);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServicesCardView.this,TeflonCoat2.class);
                startActivity(intent);
            }
        });


        cardView=(CardView) findViewById(R.id.cv5);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServicesCardView.this,RubberCoat2.class);
                startActivity(intent);
            }
        });


    }


}
