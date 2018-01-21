package com.example.raceme.farmersguide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class PreviousHistory extends AppCompatActivity {
    private static final String TAG = "PreviouHistory";


    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mRef;
    private String userID;


    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_history);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        mRef= mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID= user.getUid();
        mListView= (ListView) findViewById(R.id.listview);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
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





       mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               //showData(dataSnapshot);

                Log.w("E_Value", "Data: "+dataSnapshot.getValue());

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




    }

    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<String> array = new ArrayList<>();
        DataSnapshot du= dataSnapshot.child(userID);
        for(DataSnapshot dy : du.getChildren()){



             array.add(dy.getKey());
            String year= dy.getKey();

            Log.w("E_Value", "Data: "+year);
            DataSnapshot dc = du.child(year);
            //array.add(String.valueOf(dc.getChildren()));

            for(DataSnapshot dcosts : dc.getChildren()){
                array.add("    "+dcosts.getKey());
                String crop= dcosts.getKey();
                Log.w("E_Value", "Data: "+crop);



                for(DataSnapshot dfinal : dcosts.getChildren()){
                    array.add("          "+dfinal.getKey()+ " : " + dfinal.getValue());
                }



            }



           // String year= String.valueOf(ds.child(userID).getValue());
           /* Costs costs = new Costs();
           String ref =costs.setYear(ds.child(userID).getValue(Costs.class).getYear());
            String dc =costs.setCrop(ds.child(ref).getValue(Costs.class).getCrop());

            costs.setEsticost(ds.child(dc).getValue(Costs.class).getEsticost());
            costs.setActucost(ds.child(dc).getValue(Costs.class).getActucost());
            costs.setEstiPL(ds.child(dc).getValue(Costs.class).getEstiPL());
            costs.setActuPL(ds.child(dc).getValue(Costs.class).getActuPL());

            ArrayList<String> array = new ArrayList<>();
            array.add(costs.getYear());
            array.add(costs.getCrop());
            array.add(String.valueOf(costs.getEsticost()));
            array.add(String.valueOf(costs.getActucost()));
            array.add(costs.getEstiPL());
            array.add(costs.getActuPL());

            System.out.print(String.valueOf(costs.getEsticost()));

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);*/


        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        mListView.setAdapter(adapter);

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



    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
