package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AABDetails extends AppCompatActivity {
    Button cult_seed, cult_land, cult_urea, cult_diseases, cult_harvest, cult_insects;

    String crop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aabdetails);
        cult_seed=(Button)findViewById(R.id.cult_seed);
        cult_land= (Button)findViewById(R.id.cult_landsdlingfert);
        cult_urea= (Button)findViewById(R.id.cult_ureaIW);
        cult_diseases=(Button)findViewById(R.id.cult_diseases);
        cult_harvest=(Button)findViewById(R.id.cult_harvesting);
        cult_insects=(Button)findViewById(R.id.cult_insects);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                crop= null;
            } else {
                crop= extras.getString("cropSelected");
            }
        } else {
            crop= (String) savedInstanceState.getSerializable("cropSelected");
        }


        cult_seed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultSeed");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cult_land.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultLand");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });
        cult_urea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultUrea");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cult_diseases.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultDiseases");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cult_harvest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultHarvest");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cult_insects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultInsects");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });


    }
}
