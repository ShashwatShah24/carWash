package com.example.uvcarwashn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Waiting extends AppCompatActivity {

    private TextView vehicle_number,service_type,status;

    private DatabaseReference firebaseDatabase;
    private ValueEventListener valueEventListenerWrite, valueEventListenerRead;
    private static final String flname="vs_number";
    private SharedPreferences sharedpreferences;

    private String fnum;

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
        setContentView(R.layout.activity_waiting);

        vehicle_number=findViewById(R.id.veh_num);
        service_type=findViewById(R.id.servicetype);
        status=findViewById(R.id.status);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("OrderList");
        sharedpreferences=getSharedPreferences(flname, Context.MODE_PRIVATE);

        if(sharedpreferences.contains("phone_number")) {
            fnum=sharedpreferences.getString("phone_number","0");
            //Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
        }


        //Read Data
       valueEventListenerRead = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuffer s = new StringBuffer();
                String vnum="Retrieving",stype="Retrieving",stat="Retrieving";

                String num=returnfnum();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    if(d.getKey().equals(num)) {
                        Toast.makeText(Waiting.this, ""+d.getKey(), Toast.LENGTH_SHORT).show();
                        for (DataSnapshot db : d.getChildren()) {

                            if(db.getKey().equals("Service_type"))
                                stype=db.getValue().toString();

                            if(db.getKey().toString().equals("Status"))
                                stat=db.getValue().toString();

                            if(db.getKey().toString().equals("Vehicle_number"))
                                vnum=db.getValue().toString();
                        }
                    }

                    /*s.append("UID:" + d.getKey() + "\n");
                    for (DataSnapshot db : d.getChildren()) {
                        s.append("\t" + db.getKey() + db.getValue(String.class) + "\n");
                    }*/
                }
                vehicle_number.setText(vnum);
                status.setText(stat);
                service_type.setText(stype);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Waiting.this, databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        };

        firebaseDatabase.addValueEventListener(valueEventListenerRead);

    }

    private String returnfnum() {
        return fnum;
    }
}
