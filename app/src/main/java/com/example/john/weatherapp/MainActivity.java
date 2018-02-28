package com.example.john.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.demo.Main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText textInput;
    WeatherHandler weatherHandler;
    Button forecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInput = findViewById(R.id.textInput);
        forecastButton = findViewById(R.id.forecastButton);
        forecastButton.setOnClickListener(this);
        weatherHandler = WeatherParser.getInstance();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }


    @Override
    public void onClick(View view) {
        String cityname_INPUT = textInput.getText().toString();

        try {
                if (weatherHandler.setCountry(cityname_INPUT)) {

                    String cityname  = weatherHandler.getCityName();
                    String cloudInfo = weatherHandler.getCloudInfo();
                    String tempInfo  = weatherHandler.getTempInfo();
                    String windInfo  = weatherHandler.getWindInfo();

                    System.out.println(cityname);



                }else{
                    showToastError();
                    System.out.println("ELSE STATEMENT");
            }



        } catch (APIException e) {
           showToastError();
            System.out.println("CATCH STATEMENT");
        }
    }


    private void showToastError(){
        Context context = getApplicationContext();
        CharSequence text = ERRORMESSAGES.ERROR_TOAST;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }









}
