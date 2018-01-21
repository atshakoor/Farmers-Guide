package com.example.raceme.farmersguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnHistory;
    private Button btnOkay;
    String areaSelected;
    TextView hello;
    FirebaseUser user;
    String yearSelected;
    String monthSelected;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnHistory=(Button) findViewById(R.id.btn_Histoty);
        mAuth= FirebaseAuth.getInstance();
        btnOkay= (Button) findViewById(R.id.okay);
        hello = (TextView) findViewById(R.id.user);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with:" + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out");
                }
                // ...
            }
        };


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), History.class);
                startActivity(myIntent);



            }
        });

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

              /*  Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                Log.w("E_Value", "Data: "+formattedDate);*/
                Log.w("E_Value", "Data: "+areaSelected);
                Intent myIntent = new Intent(v.getContext(), SelectCrop.class);
                myIntent.putExtra("areaSelected", areaSelected);
                myIntent.putExtra("yearSelected", yearSelected);
                myIntent.putExtra("monthSelected", monthSelected);
                startActivity(myIntent);


            }
        });
        String[] myRegion = {"Dhaka","Tangail","Barishal","Jessore","Sylhet","Mymenshing","Bogra","Patuakhali","Faridpur","Comilla"};
        String[] myYear = {"2014","2015"};
        String[] myMonth= {"January","February","March","April","May","June","July","August","September","October","November","December"};


        ArrayAdapter<String> myYearAdapder= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myYear);
        ArrayAdapter<String> myRegionAdapder= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myRegion);
        ArrayAdapter<String> myMonthAdapder= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myMonth);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner yearSpinner= (Spinner) findViewById(R.id.ySpinner);
        Spinner monthSpinner= (Spinner) findViewById(R.id.mSpinner);
        spinner.setAdapter(myRegionAdapder);
        yearSpinner.setAdapter(myYearAdapder);
        monthSpinner.setAdapter(myMonthAdapder);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthSelected= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearSelected= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              areaSelected = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.myCrops){
            Intent myIntent = new Intent(this, MyCrops.class);
            startActivity(myIntent);
            return true;
        }
        else{
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
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
