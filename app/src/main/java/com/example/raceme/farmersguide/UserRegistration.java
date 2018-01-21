package com.example.raceme.farmersguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegistration extends AppCompatActivity {
    private Button buttonRegister;
    private EditText emailAdd;
    private EditText passwd;
    private TextView goTologin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        progressDialog = new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        buttonRegister=(Button) findViewById(R.id.regisButton);
        emailAdd =(EditText) findViewById(R.id.regisEm);
        passwd=(EditText) findViewById(R.id.registerpw);
        goTologin= (TextView) findViewById(R.id.gotoLogin);




        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });


        goTologin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myIntent);

            }
        });
    }
    private void registerUser(){
        String email = emailAdd.getText().toString().trim();
        String pass= passwd.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter Email to Register",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter Password to Register",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserRegistration.this,"Registered SucessFully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserRegistration.this,"Not Registered, try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
