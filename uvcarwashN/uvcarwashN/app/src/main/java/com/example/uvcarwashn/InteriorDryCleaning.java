package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InteriorDryCleaning extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interior_dry_cleaning);

        button=(Button)findViewById(R.id.RegisterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InteriorDryCleaning.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
