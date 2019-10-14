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

public class feedback extends AppCompatActivity {

    EditText name,feedback;
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

    /*@Override
    protected void onPause() {
        super.onPause();
        firebaseDatabase.removeEventListener(valueEventListenerRead);
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        submit = findViewById(R.id.EnglishButton);
        name=findViewById(R.id.input_name);
        feedback=findViewById(R.id.input_feedback);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("FeedBack");
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
                firebaseDatabase.child(num + "/Feedback").setValue(feedback.getText().toString());
                firebaseDatabase.child(num + "/Name").setValue(name.getText().toString());

                //firebaseDatabase.child(uid+"/Model  number").setValue(model_no.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(feedback.this, databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        };

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String person_name=name.getText().toString();
                String feed=feedback.getText().toString();

                if(person_name==null) {
                    name.setError("Name Required");
                    name.requestFocus();
                    return;
                }
                else if(feed==null) {
                    name.setError("Feedback Required");
                    name.requestFocus();
                    return;
                }
                else {
                    if(feed.length()<=100 && person_name.length()<=30) {
                        firebaseDatabase.addValueEventListener(valueEventListenerWrite);
                        Toast.makeText(getApplicationContext(),"Thank You for giving us Feedback",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(feedback.this,Home.class));
                    }
                    else if(feed.length()>100){
                        feedback.setError("More than 100 words");
                        feedback.requestFocus();
                        return;
                    }
                    else {
                        name.setError("More than 30 words");
                        feedback.requestFocus();
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
