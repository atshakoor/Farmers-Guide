package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JWPDetails extends AppCompatActivity {
    String crop;
    Button cultp_land, cultp_fert, cultp_disease, cultp_harvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwpdetails);
        cultp_land=(Button)findViewById(R.id.cultp_land);
        cultp_fert= (Button)findViewById(R.id.cultp_fert);
        cultp_disease=(Button)findViewById(R.id.cultp_disease);
        cultp_harvest=(Button)findViewById(R.id.cultp_harvest);

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


        cultp_land.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultpLand");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });
        cultp_fert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultpfert");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cultp_disease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "culptDiseases");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });

        cultp_harvest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsProcedures.class);
                myIntent.putExtra("processSelected", "cultpHarvest");
                myIntent.putExtra("cropSelected", crop);
                startActivity(myIntent);


            }
        });



    }
}
