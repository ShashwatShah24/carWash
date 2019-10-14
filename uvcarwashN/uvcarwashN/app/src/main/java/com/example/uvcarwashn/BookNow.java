package com.example.uvcarwashn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class BookNow extends AppCompatActivity {
    EditText car_number;
    TextView s_type;
    Button submit;
    private String service_type,num;


    private DatabaseReference firebaseDatabase;
    private ValueEventListener valueEventListenerWrite, valueEventListenerRead;
    private static final String flname="vs_number";
    private SharedPreferences sharedpreferences;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //firebaseDatabase.removeEventListener(valueEventListenerRead);
        firebaseDatabase.removeEventListener(valueEventListenerWrite);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
       // firebaseDatabase.removeEventListener(valueEventListenerRead);
        firebaseDatabase.removeEventListener(valueEventListenerWrite);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        service_type=getIntent().getStringExtra("wash");
        Toast.makeText(this, ""+getIntent().getStringExtra("wash"), Toast.LENGTH_SHORT).show();
        car_number = findViewById(R.id.carnumber);
        s_type = findViewById(R.id.sertype);
        s_type.setText(service_type);
        submit = findViewById(R.id.EnglishButton);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("OrderList");
        sharedpreferences=getSharedPreferences(flname, Context.MODE_PRIVATE);

        if(sharedpreferences.contains("phone_number")) {
            num=sharedpreferences.getString("phone_number","0");
            //Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
        }

        //Write Data
        valueEventListenerWrite = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firebaseDatabase.removeEventListener(valueEventListenerWrite);
                /*
                int uid = new Random(1000).nextInt(1000);

                uid = uid < 0 ? -uid : uid;
                */
                firebaseDatabase.child(num + "/Vehicle_number").setValue(car_number.getText().toString());
                firebaseDatabase.child(num + "/Service_type").setValue(service_type);
                firebaseDatabase.child(num + "/Status").setValue("Pending");

                //firebaseDatabase.child(uid+"/Model  number").setValue(model_no.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BookNow.this, databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        };

        //Read Data
      /*  valueEventListenerRead = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuffer s = new StringBuffer();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    s.append("UID:" + d.getKey() + "\n");
                    for (DataSnapshot db : d.getChildren()) {
                        s.append("\t" + db.getKey() + db.getValue(String.class) + "\n");
                    }
                }
                text.setText(s.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BookNow.this, databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        };*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carno=car_number.getText().toString();
                if(carno==null) {
                    car_number.setError("Car number Required");
                    car_number.requestFocus();
                    return;
                }
                else {
                    if(carno.length()==9) {
                        firebaseDatabase.addValueEventListener(valueEventListenerWrite);
                        Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(BookNow.this,Home.class));
                    }
                    else {
                        car_number.setError("Invalid Vehicle Number");
                        car_number.requestFocus();
                        return;
                    }
                    return;
                }

            }
        });
        /*read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase.addValueEventListener(valueEventListenerRead);
            }
        });
*/
    }


}
