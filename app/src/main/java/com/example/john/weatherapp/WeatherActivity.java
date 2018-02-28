package com.example.john.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    ArrayList<String> weatherinfo;
    TextView cityName;
    TextView tempInfo;
    TextView cloudInfo;
    TextView windInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather);
        initTextViews();

        ArrayList<String> weatherinfo = (ArrayList<String>) getIntent().getSerializableExtra(CONSTANTS.BUNDLE_KEY);

        setTexts(weatherinfo);





    }


    private void setTexts(ArrayList<String> weatherinfo){

        cityName.setText(weatherinfo.get(0));
        tempInfo.setText(weatherinfo.get(1));
        cloudInfo.setText(weatherinfo.get(2));
        windInfo.setText(weatherinfo.get(3));


    }







    private void initTextViews(){

        cityName = findViewById(R.id.cityName);
        tempInfo = findViewById(R.id.tempInfo);
        cloudInfo =findViewById(R.id.cloudInfo);
        windInfo = findViewById(R.id.windinfo);


    }






}
