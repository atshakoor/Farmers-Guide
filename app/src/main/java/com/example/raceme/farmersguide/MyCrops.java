package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MyCrops extends AppCompatActivity {
    String newString;
    ArrayList<String> a1 = new ArrayList<String>();
    private static final String TAG = "MainActivity";
    FirebaseUser user;
    ListView myCrops ;
    String cropSelected;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_crops);
        mAuth= FirebaseAuth.getInstance();
        final ArrayList<String> myStringArray= new ArrayList<>();
        myCrops= (ListView) findViewById(R.id.listviewMycrops);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("mycrop");
                a1.add(Integer.parseInt(user.getUid()),newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("mycrop");
        }



        for (int i = 0; i < a1.size(); i++) {
            String mycrop= a1.get(Integer.parseInt(user.getUid()));
            myStringArray.add(mycrop);


        }
        myStringArray.add("Boro");
        myStringArray.add("Aus");

        ArrayAdapter<String> myAdapder= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
        myCrops.setAdapter(myAdapder);
        myCrops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newString = (String) parent.getItemAtPosition(position);
                if(String.valueOf(newString.charAt(0)).equals("A")){
                    if(String.valueOf(newString.charAt(1)).equals("u")){
                        cropSelected="Aus";
                    }
                    else{
                        cropSelected="Aman";
                    }
                }
                if(String.valueOf(newString.charAt(0)).equals("B")){
                    cropSelected="Boro";

                }
                if(String.valueOf(newString.charAt(0)).equals("W")){
                    cropSelected="Wheat";

                }
                if(String.valueOf(newString.charAt(0)).equals("J")){
                    cropSelected="Jute";

                }
                if(String.valueOf(newString.charAt(0)).equals("P")){
                    cropSelected="Potato";

                }

                Intent myIntent = new Intent(view.getContext(), Calender.class);
                Intent mycropIntent = new Intent(view.getContext(), MyCrops.class);
                mycropIntent.putExtra("mycrop", cropSelected);
                myIntent.putExtra("cropSelected", cropSelected);
                startActivity(myIntent);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
