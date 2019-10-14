package com.example.uvcarwashn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_remember;
    SharedPreferences sharedpreferences;
    String uid;

    private static final String flname="vs_number";
    private FirebaseAuth mAuth;
    EditText otp,phone;
    String phoneNumber,codeSent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Help.class);
                Toast.makeText(getApplicationContext(), "help selected",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        textView=(TextView)findViewById(R.id.textView1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Services.class);
                Toast.makeText(getApplicationContext(), "Guest login successful",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        sharedpreferences=getSharedPreferences(flname, Context.MODE_PRIVATE);

        otp=findViewById(R.id.otp);
        phone=findViewById(R.id.number);

        SharedPreferences spref=getSharedPreferences(flname,Context.MODE_PRIVATE);

        getPreferencesData();
        /* uid=spref.getInt(phone.getText().toString(),0);
        phone.setText(uid.toString()); */
        cb_remember=(CheckBox)  findViewById(R.id.remember_me);
        // FOR submitting phone number
        findViewById(R.id.butt_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });
        // FOr submitting otp
        findViewById(R.id.butt_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cno=phone.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("phone_number",cno);
                editor.apply();
                editor.commit();

                verifySignInCode();
            }
        });
    }
    public void getPreferencesData() {
        SharedPreferences sp=getSharedPreferences(flname,MODE_PRIVATE);
        if(sp.contains("phone_number")) {
            String num=sp.getString("phone_number","0");
            phone.setText(num);
            //Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
        }
    }
    public void Remember_Func(View view) {
        CheckBox checkBox=(CheckBox) findViewById(R.id.remember_me);

        String cno=phone.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(checkBox.isChecked()) {
            editor.putString("phone_number",cno);
            editor.apply();
            editor.commit();
            Toast.makeText(getApplicationContext(),"Phone Number wiil be saved for future logins",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),cno,Toast.LENGTH_LONG).show();
        }
        else {
            sharedpreferences.edit().clear().apply();
            Toast.makeText(getApplicationContext(),"Phone Number won't be saved for future logins",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendVerificationCode() {
        phoneNumber=phone.getText().toString();

        if (phoneNumber.isEmpty()) {
            phone.setError("Phone Number Required");
            phone.requestFocus();
            return;
        }

        if ( phoneNumber.length()<10 || phoneNumber.length()>10 ) {
            phone.setError("Invalid Phone Number");
            phone.requestFocus();
            return;
        }

        phoneNumber="+91"+phoneNumber;
        Toast.makeText(getApplicationContext(),"Connecting To server",Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Toast.makeText(getApplicationContext(), "Sign in successfull", Toast.LENGTH_LONG).show();
		startActivity(new Intent(MainActivity.this,Home.class));
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),"Unsuccessfull verification",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(getApplicationContext(),"OTP request sent",Toast.LENGTH_LONG).show();
            codeSent=s;
        }
    };
    private void verifySignInCode() {
        String code=otp.getText().toString();
        if( code.isEmpty()) {
            otp.setError("OTP required");
            otp.requestFocus();
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Successfull login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Home.class));
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(), "OTP incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
