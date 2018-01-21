package com.example.raceme.farmersguide;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SelectCrop extends AppCompatActivity {
    String cropSelected;
    Button btnCropSelect;
    String areaSelected;
    String yearSelected;
    String monthSelected;
    String newString;
    int year;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_crop);
        mStorageRef = FirebaseStorage.getInstance().getReference();


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                areaSelected= null;
                yearSelected=null;
                monthSelected=null;
            } else {
                areaSelected= extras.getString("areaSelected");
                yearSelected= extras.getString("yearSelected");
                monthSelected=extras.getString("monthSelected");
            }
        } else {
            areaSelected= (String) savedInstanceState.getSerializable("areaSelected");
            yearSelected= (String) savedInstanceState.getSerializable("yearSelected");
            monthSelected= (String) savedInstanceState.getSerializable("monthSelected");
        }




       // btnCropSelect= (Button) findViewById(R.id.btn_cropselect);
        final ArrayList<String> myStringArray= new ArrayList<>();
        //Algo working from here
        if(yearSelected != null) {
            year = Integer.parseInt(yearSelected);
            Log.w("E_Value", "Data: "+Integer.parseInt(yearSelected));
        } else {
            //decide what to do when end of file
        }

        DecimalFormat decimalFormat = new DecimalFormat("#00.000");
        String area = areaSelected;
        String month = monthSelected;
        ArrayList<String> As = new ArrayList<>(Arrays.asList("March", "April", "May","June","July"));
        ArrayList<String> Am = new ArrayList<>(Arrays.asList("June","July","August","September","October","November"));
        ArrayList<String> B = new ArrayList<>(Arrays.asList("October","November","December","January","February","March", "April", "May"));
        ArrayList<String> W = new ArrayList<>(Arrays.asList("November","December","January","February","March", "April"));
        ArrayList<String> J = new ArrayList<>(Arrays.asList("March", "April", "May","June","July","August"));
        ArrayList<String> P = new ArrayList<>(Arrays.asList("November","December","January","February"));

        double Aus = 0; double Aman = 0; double Boro = 0; double Wheat = 0; double Jute = 0; double Potato = 0;

        ArrayList<Double> outputs = new ArrayList<Double>();

        ResultGeneration rr = null;

        if(As.contains(month))  {
            rr = new ResultGeneration("Aus", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Aus");
            String res=rr.getResult(year)+"";
            myStringArray.add("Aus                      " +res.substring(0, Math.min(res.length(), 4)));
        }
        if(Am.contains(month)){
            rr = new ResultGeneration("Aman", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Aman");
            String res=rr.getResult(year)+"";
            myStringArray.add("Aman                    " +res.substring(0, Math.min(res.length(), 4)));
        }
        if(B.contains(month)){
            rr = new ResultGeneration("Boro", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Boro");
            String res=rr.getResult(year)+"";
            myStringArray.add("Boro                      " +res.substring(0, Math.min(res.length(), 4)));
        }
        if(W.contains(month)){
            rr = new ResultGeneration("Wheat", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Wheat");
            String res=rr.getResult(year)+"";
            myStringArray.add("Wheat                   " +res.substring(0, Math.min(res.length(), 4)));
        }
        if(J.contains(month)){
            rr = new ResultGeneration("Jute", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Jute");
            String res=rr.getResult(year)+"";
            myStringArray.add("Jute                      " +res.substring(0, Math.min(res.length(), 4)));
        }
        if(P.contains(month)){
            rr = new ResultGeneration("Potato", area  /*<--Enter area name*/);
            //outputs.add(rr.getResult(year /*<--Enter year*/));
            System.out.println("Potato");
            String res=rr.getResult(year)+"";
            myStringArray.add("Potato                   " +res.substring(0, Math.min(res.length(), 4)));
        }


        // System.out.println(outputs);





        //ends here



        ArrayAdapter<String> myAdapder= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
        ListView listView =(ListView) findViewById(R.id.listview);

        listView.setAdapter(myAdapder);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

 /*       spinner.setAdapter(myAdapder);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               newString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
   /*     btnCropSelect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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

                Intent myIntent = new Intent(v.getContext(), Calender.class);
                myIntent.putExtra("cropSelected", cropSelected);
                startActivity(myIntent);


            }
        });*/

    }
}