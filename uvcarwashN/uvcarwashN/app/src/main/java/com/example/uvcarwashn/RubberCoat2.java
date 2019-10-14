package com.example.uvcarwashn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RubberCoat2 extends AppCompatActivity {
    private Button button;

    private TextView price;
    private DatabaseReference firebaseDatabase;
    private ValueEventListener  valueEventListenerRead;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseDatabase.removeEventListener(valueEventListenerRead);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseDatabase.removeEventListener(valueEventListenerRead);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubber_coat2);

        price=findViewById(R.id.input_email);
        button=(Button)findViewById(R.id.RegisterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RubberCoat2.this,BookNow.class);
                intent.putExtra("wash","Rubber_Coating");
                startActivity(intent);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Pricing");


        //Read Data
        valueEventListenerRead = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String rc_price="0";

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    if(d.getKey().equals("RubberCoating"))
                        rc_price="Rate- ₹"+d.getValue().toString();


                    /*s.append("UID:" + d.getKey() + "\n");
                    for (DataSnapshot db : d.getChildren()) {
                        s.append("\t" + db.getKey() + db.getValue(String.class) + "\n");
                    }*/
                }
                price.setText(rc_price);
                Toast.makeText(getApplicationContext(),"Price Updated",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RubberCoat2.this, databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        };

        firebaseDatabase.addValueEventListener(valueEventListenerRead);


    }
}
