package com.example.raceme.farmersguide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CostCalculator extends AppCompatActivity {

    private static final String TAG = "CostCalculator";

    private EditText eSale;
    private EditText aSale;
    String fYear;
    String fCrop;
    int costPrice;
    int estCostPrice;
    double actualPorL;
    double estimatePorl;
    private TextView eProfitLoss;
    private TextView aProfitLoss;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mRef;

    String eProLoss;
    String aProLoss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_calculator);



        System.out.println(ActualCosts.totalPcost);
        System.out.println(Estimate.totalEstPcost);

        fYear = Estimate.eYear;
        fCrop = Estimate.eCrop;
        costPrice = ActualCosts.totalPcost;
        estCostPrice = Estimate.totalEstPcost;

        eSale = (EditText) findViewById(R.id.estiCost);
        aSale = (EditText) findViewById(R.id.actCost);

        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        mRef= mFirebaseDatabase.getReference();





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



        // Read from the database





    }


    private void addDetails(String aproloss, String eproloss){

        //String id = databasecosts.push().getKey();
        //databasecosts.child(id).setValue()


        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        //Costs costs = new Costs(estCostPrice,costPrice, eproloss, aproloss);

        DatabaseReference year =  mRef.child(userID).child(fYear);
        //mRef.child(userID).child("Year").setValue(fYear);
        DatabaseReference crop= year.child(fCrop);
        //mRef.child(userID).child("Crop").setValue(fCrop);

        DatabaseReference esticost= crop.child("EstimatedCosts");
        DatabaseReference actucost= crop.child("ActualCosts");
        DatabaseReference estiPL= crop.child("EstiPL");
        DatabaseReference actuPL= crop.child("ActuPL");
        esticost.setValue(estCostPrice);
        actucost.setValue(costPrice);
        estiPL.setValue(eproloss);
        actuPL.setValue(aproloss);




        //DatabaseReference esti= crop.child("EstimatedCosts");







    }


    public void pPLButton(View v){

        String estSellPrice = eSale.getText().toString();
        String sellPrice = aSale.getText().toString();
        eProfitLoss = (TextView) findViewById(R.id.esprofitLoss);
        aProfitLoss = (TextView) findViewById(R.id.acprofitLoss);


        if((Double.parseDouble(sellPrice))>=costPrice){

            actualPorL= ((((Double.parseDouble(sellPrice))-costPrice)/(Double.parseDouble(sellPrice)))*100);
            String apl = actualPorL+"";

            aProLoss= apl.substring(0, Math.min(apl.length(), 4)) + "% Profit";

            System.out.println(actualPorL);
            aProfitLoss.setText(apl.substring(0, Math.min(apl.length(), 4))  + "% Profit");



        }else{

            actualPorL =((costPrice-Double.parseDouble(sellPrice))/Double.parseDouble(sellPrice))*100;
            String apl = actualPorL+"";

            System.out.println(actualPorL);

            aProLoss= apl.substring(0, Math.min(apl.length(), 4)) + "% Loss";

            aProfitLoss.setText(apl.substring(0, Math.min(apl.length(), 4)) + "% Loss");
        }





        if((Double.parseDouble(estSellPrice))>=estCostPrice){

            estimatePorl= ((Double.parseDouble(estSellPrice)-estCostPrice)/Double.parseDouble(estSellPrice))*100;
            String epl = estimatePorl+"";
            eProLoss=epl.substring(0, Math.min(epl.length(), 4)) + "% Estimated Profit";

            System.out.println(estimatePorl);
            eProfitLoss.setText(epl.substring(0, Math.min(epl.length(), 4)) + "% Estimated Profit");


        }else{

            estimatePorl =((estCostPrice-Double.parseDouble(estSellPrice))/Double.parseDouble(estSellPrice))*100;

            String epl = estimatePorl+"";

            eProLoss=epl.substring(0, Math.min(epl.length(), 4)) +"% Estimated Loss";

            System.out.println(estimatePorl);
            eProfitLoss.setText(epl.substring(0, Math.min(epl.length(), 4)) + "% Estimated Loss");
        }


        addDetails(aProLoss, eProLoss);

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
