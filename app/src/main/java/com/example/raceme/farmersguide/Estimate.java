package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Estimate extends AppCompatActivity {



    private EditText mYear;
    private   EditText mCrop;

    private EditText mSeed;
    private EditText mFertilizer;
    private EditText mSpray;
    private EditText mPlough;
    private EditText mCultivate;
    private EditText mMaintenance;
    private EditText mHarvest;
    private EditText mOthers;
    public static int totalEstPcost;
    public static String eSeed;
    public static String eFertilizer;
    public static String eSpray;
    public static String ePlough;
    public static String eCultivate;
    public static String eMaintenance;
    public static String eHarvest;
    public static String eOthers;
    public static String eYear;
    public static String eCrop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);



        mYear=(EditText) findViewById(R.id.yearField);
        mCrop=(EditText) findViewById(R.id.cropName);
        mSeed=(EditText) findViewById(R.id.seedField);
        mFertilizer=(EditText) findViewById(R.id.fertilizerField);
        mSpray=(EditText) findViewById(R.id.sprayField);
        mPlough=(EditText) findViewById(R.id.ploughingField);
        mCultivate=(EditText) findViewById(R.id.cultivationField);
        mMaintenance=(EditText) findViewById(R.id.maintainField);
        mHarvest=(EditText) findViewById(R.id.harvestField);
        mOthers=(EditText) findViewById(R.id.othersField);
    }

    public void nextPage(View view){
        eYear = mYear.getText().toString();
        eCrop = mCrop.getText().toString();



        eSeed  = mSeed.getText().toString();
        eFertilizer = mFertilizer.getText().toString();
        eSpray =mSpray.getText().toString();
        ePlough = mPlough.getText().toString();
        eCultivate =mCultivate.getText().toString();
        eMaintenance =mMaintenance.getText().toString();
        eHarvest =mHarvest.getText().toString();
        eOthers =mOthers.getText().toString();



        totalEstPcost = Integer.parseInt(eSeed) + Integer.parseInt(eFertilizer) + Integer.parseInt(eSpray) + Integer.parseInt(ePlough) + Integer.parseInt(eCultivate) + Integer.parseInt(eMaintenance)+ Integer.parseInt(eHarvest)+ Integer.parseInt(eOthers);
//System.out.println(totalPcost);

        Intent intent = new Intent(this, ActualCosts.class);
        /*Bundle extras = new Bundle();
        extras.putString("Year",eYear);
        extras.putString("Crop",eCrop);
        extras.putString("TotalPCost", String.valueOf(totalPcost));
        intent.putExtras(extras);*/
        startActivity(intent);




    }
}

