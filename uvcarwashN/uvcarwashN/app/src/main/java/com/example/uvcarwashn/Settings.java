package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button=(Button)findViewById(R.id.EnglishButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.this,Home.class);
                Toast.makeText(getApplicationContext(), "English Selected",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.hindiButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.this,Home.class);
                Toast.makeText(getApplicationContext(), "Hindi Selected",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });


    }
}
