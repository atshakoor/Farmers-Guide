package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActualCosts extends AppCompatActivity {



    private EditText mSeed;
    private EditText mFertilizer;
    private EditText mSpray;
    private EditText mPlough;
    private EditText mCultivate;
    private EditText mMaintenance;
    private EditText mHarvest;
    private EditText mOthers;
    public static int totalPcost;

    public static String aSeed;
    public static String aFertilizer;
    public static String aSpray;
    public static String aPlough;
    public static String aCultivate;
    public static String aMaintenance;
    public static String aHarvest;
    public static String aOthers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_costs);



        mSeed=(EditText) findViewById(R.id.aseedField);
        mFertilizer=(EditText) findViewById(R.id.afertilizerField);
        mSpray=(EditText) findViewById(R.id.asprayField);
        mPlough=(EditText) findViewById(R.id.aploughingField);
        mCultivate=(EditText) findViewById(R.id.acultivationField);
        mMaintenance=(EditText) findViewById(R.id.amaintainField);
        mHarvest=(EditText) findViewById(R.id.aharvestField);
        mOthers=(EditText) findViewById(R.id.aothersField);
    }

    public void nextPage(View view){


        aSeed  = mSeed.getText().toString();
        aFertilizer = mFertilizer.getText().toString();
        aSpray =mSpray.getText().toString();
        aPlough = mPlough.getText().toString();
        aCultivate =mCultivate.getText().toString();
        aMaintenance =mMaintenance.getText().toString();
        aHarvest =mHarvest.getText().toString();
        aOthers =mOthers.getText().toString();


        totalPcost = Integer.parseInt(aSeed) + Integer.parseInt(aFertilizer) + Integer.parseInt(aSpray) + Integer.parseInt(aPlough) + Integer.parseInt(aCultivate) + Integer.parseInt(aMaintenance)+ Integer.parseInt(aHarvest)+ Integer.parseInt(aOthers);
//System.out.println(totalPcost);

        Intent intent = new Intent(this, CostCalculator.class);
        /*Bundle extras = new Bundle();
        extras.putString("Year",eYear);
        extras.putString("Crop",eCrop);
        extras.putString("TotalPCost", String.valueOf(totalPcost));
        intent.putExtras(extras);*/
        startActivity(intent);




    }
}
