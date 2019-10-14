package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Services extends AppCompatActivity {


    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);



        cardView=(CardView) findViewById(R.id.cv1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Services.this,WashingExterior.class);

                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Services.this,InteriorDryCleaning.class);
                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Services.this,Rubbing.class);
                startActivity(intent);
            }
        });

        cardView=(CardView) findViewById(R.id.cv4);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Services.this,TeflonCoating.class);
                startActivity(intent);
            }
        });


        cardView=(CardView) findViewById(R.id.cv5);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Services.this,RubberCoat.class);
                startActivity(intent);
            }
        });





    }

}
