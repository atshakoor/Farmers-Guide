package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class History extends AppCompatActivity {
    private Button btnHistory;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnHistory= (Button) findViewById(R.id.btn_view_history);
        btnAdd= (Button) findViewById(R.id.btn_new_data);




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Estimate.class);
                startActivity(myIntent);



            }
        });


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), PreviousHistory.class);
                startActivity(myIntent);



            }
        });


    }


}
