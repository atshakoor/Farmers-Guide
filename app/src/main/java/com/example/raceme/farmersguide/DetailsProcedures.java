package com.example.raceme.farmersguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailsProcedures extends AppCompatActivity {
String crop;
    String process;
    ImageView imgDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_procedures);
        imgDetails= (ImageView) findViewById(R.id.processDetails);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                crop= null;
            } else {
                process= extras.getString("processSelected");
                crop= extras.getString("cropSelected");
            }
        } else {
            process= (String) savedInstanceState.getSerializable("processSelected");
            crop= (String) savedInstanceState.getSerializable("cropSelected");
        }

        if(crop.equals("Aman")){
            if(process.equals("cultSeed")){
                imgDetails.setImageResource(R.drawable.beejaman);
            }
            else if(process.equals("cultLand")){
                imgDetails.setImageResource(R.drawable.jomiaman);
            }
            else if(process.equals("cultUrea")){
                imgDetails.setImageResource(R.drawable.guti_aman);
            }
            else if(process.equals("cultDiseases")){
                imgDetails.setImageResource(R.drawable.rogaman);
            }
            else if(process.equals("cultHarvest")){
                imgDetails.setImageResource(R.drawable.fosholkortonaman);
            }
            else if(process.equals("cultInsects")){
                imgDetails.setImageResource(R.drawable.pokaaman);
            }
        }

       else if(crop.equals("Aus")){
            if(process.equals("cultSeed")){
                imgDetails.setImageResource(R.drawable.beejaus);
            }
            else if(process.equals("cultLand")){
                imgDetails.setImageResource(R.drawable.jomiaus);
            }
            else if(process.equals("cultUrea")){
                imgDetails.setImageResource(R.drawable.ausguti);
            }
            else if(process.equals("cultDiseases")){
                imgDetails.setImageResource(R.drawable.rogaus);
            }
            else if(process.equals("cultHarvest")){
                imgDetails.setImageResource(R.drawable.ausharvesting);
            }
            else if(process.equals("cultInsects")){
                imgDetails.setImageResource(R.drawable.pokaaman);
            }
        }
        else if(crop.equals("Boro")){
            if(process.equals("cultSeed")){
                imgDetails.setImageResource(R.drawable.beejboro);
            }
            else if(process.equals("cultLand")){
                imgDetails.setImageResource(R.drawable.jomiboro);
            }
            else if(process.equals("cultUrea")){
                imgDetails.setImageResource(R.drawable.gutiboro);
            }
            else if(process.equals("cultDiseases")){
                imgDetails.setImageResource(R.drawable.rogboro);
            }
            else if(process.equals("cultHarvest")){
                imgDetails.setImageResource(R.drawable.boroharvest);
            }
            else if(process.equals("cultInsects")){
                imgDetails.setImageResource(R.drawable.pokaboro);
            }
        }
        else if (crop.equals("Jute")){
            if(process.equals("cultpLand")){
                imgDetails.setImageResource(R.drawable.jutebeej);
            }
            else if(process.equals("cultpfert")){
                imgDetails.setImageResource(R.drawable.ferjute);
            }
            else if(process.equals("culptDiseases")){
                imgDetails.setImageResource(R.drawable.rogjute);
            }
            else if(process.equals("cultpHarvest")){
                imgDetails.setImageResource(R.drawable.harvestjute);
            }
        }
        else if (crop.equals("Potato")){
            if(process.equals("cultpLand")){
                imgDetails.setImageResource(R.drawable.jomialu);
            }
            else if(process.equals("cultpfert")){
                imgDetails.setImageResource(R.drawable.feralu);
            }
            else if(process.equals("culptDiseases")){
                imgDetails.setImageResource(R.drawable.rogalu);
            }
            else if(process.equals("cultpHarvest")){
                imgDetails.setImageResource(R.drawable.harvestalu);
            }
        }
        else if (crop.equals("Wheat")){
            if(process.equals("cultpLand")){
                imgDetails.setImageResource(R.drawable.seedwheat);
            }
            else if(process.equals("cultpfert")){
                imgDetails.setImageResource(R.drawable.ferwheat);
            }
            else if(process.equals("culptDiseases")){
                imgDetails.setImageResource(R.drawable.rogwheat);
            }
            else if(process.equals("cultpHarvest")){
                imgDetails.setImageResource(R.drawable.harvestwheat);
            }
        }


    }
}
