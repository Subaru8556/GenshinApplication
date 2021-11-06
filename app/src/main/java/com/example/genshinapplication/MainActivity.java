package com.example.genshinapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private String layoutRatio;
    private int layoutWidth;
    private int layoutHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        ConstraintLayout layout = findViewById(R.id.activity_main);
        layoutWidth = layout.getWidth();
        layoutHeight = layout.getHeight();

        Log.d("log:","layout_width "+layoutWidth);
        Log.d("log:","layout_height "+layoutHeight);

        float aspectRatio = (float) layoutHeight/(float) layoutWidth;
        aspectRatio = aspectRatio*9;

        Log.d("log:","aspect_ratio "+aspectRatio);

        layout_selector(aspectRatio);

        Log.d("log:","layout_aspect_ratio "+layoutRatio);


    }

    private void layout_selector(float ratio){
        if(ratio < 16){
            layoutRatio = "16";
        }else if(ratio < 19){
            String layout = layout_calculator(640.0f, (float)layoutHeight, (float)layoutWidth, 760.0f);
            if(Objects.equals(layout, "small")){
                layoutRatio = "16";
            }else{
                layoutRatio = "19";
            }
        }else if(ratio < 20){
            String layout = layout_calculator(760.0f, (float)layoutHeight, (float)layoutWidth, 800.0f);
            if(Objects.equals(layout, "small")){
                layoutRatio = "19";
            }else{
                layoutRatio = "20";
            }
        }else if(ratio < 21){
            String layout = layout_calculator(800.0f, (float)layoutHeight, (float)layoutWidth, 840.0f);
            if(Objects.equals(layout, "small")){
                layoutRatio = "20";
            }else{
                layoutRatio = "21";
            }
        }else{
            layoutRatio = "21";
        }
    }

    private String layout_calculator(float small_x, float layout_x, float layout_y, float large_y){
        float cal_small_x = small_x*layout_y/360;
        float cal_large_y = 360*layout_x/large_y;
        //Log.d("log:","cal_small_x "+cal_small_x);
        //Log.d("log:","cal_large_y "+cal_large_y);

        float dif_x = layout_x-cal_small_x;
        float dif_y = layout_y-cal_large_y;
        //Log.d("log:","dif_x "+dif_x);
        //Log.d("log:","dif_y "+dif_y);

        if(dif_x < dif_y){
            return "small";
        }else if(dif_y < dif_x){
            return "large";
        }else{
            return "small";
        }
    }
}