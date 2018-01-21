package com.example.raceme.farmersguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Calender extends AppCompatActivity {
    String newString;
    ImageView imageView1;
    ImageView imageView0;
    Button btnDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        imageView0 = (ImageView) findViewById(R.id.imageView0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        btnDetails=(Button) findViewById(R.id.btn_details);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("cropSelected");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("cropSelected");
        }
        Log.w("E_Value:", "Data : "+newString);


        if(newString.equals("Aus")){
            imageView0.setImageResource(R.drawable.aus0);
            imageView1.setImageResource(R.drawable.aus1);

        }
        else if(newString.equals("Aman")){
            imageView0.setImageResource(R.drawable.aman0);
            imageView1.setImageResource(R.drawable.aman1);

        }
        else if(newString.equals("Boro")){
            imageView0.setImageResource(R.drawable.boro0);
            imageView1.setImageResource(R.drawable.boro1);


        }else if(newString.equals("Wheat")){
            imageView0.setImageResource(R.drawable.wheat0);
            imageView1.setImageResource(R.drawable.wheat1);


        }
        else if(newString.equals("Jute")){
            imageView0.setImageResource(R.drawable.jute0);
            imageView1.setImageResource(R.drawable.jute1);


        }
        else if(newString.equals("Potato")){
            imageView0.setImageResource(R.drawable.potato0);
            imageView1.setImageResource(R.drawable.potato1);


        }
        btnDetails.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(newString.equals("Aus") || newString.equals("Aman") || newString.equals("Boro")){
                    Intent myIntent = new Intent(v.getContext(), AABDetails.class);
                    myIntent.putExtra("cropSelected", newString);
                    startActivity(myIntent);
                }
                else if (newString.equals("Jute") || newString.equals("Wheat") || newString.equals("Potato")){
                    Intent myIntent = new Intent(v.getContext(), JWPDetails.class);
                    myIntent.putExtra("cropSelected", newString);
                    startActivity(myIntent);
                }



            }
        });
    }
}
