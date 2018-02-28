package com.example.john.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.john.weatherapp.model.WeatherHandler;
import com.example.john.weatherapp.model.WeatherParser;

import net.aksingh.owmjapis.api.APIException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText textInput;
    WeatherHandler weatherHandler;
    Button forecastButton;


    /**
     * sets up all the widgets, and sets the applications main thread to non-strict mode
     * which enables internet acess.
     */
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

    /**
     *
     * when button is clicked, gets all the data from the model, and sends it to next Activity
     */
    @Override
    public void onClick(View view) {
        String cityname_INPUT = textInput.getText().toString();

        try {
                if (weatherHandler.setCity(cityname_INPUT)) {

                    //--These are for readability--
                    CharSequence cityname  = weatherHandler.getCityName();
                    CharSequence tempInfo  = weatherHandler.getTempInfo();
                    CharSequence cloudInfo = weatherHandler.getCloudInfo();
                    CharSequence windInfo  = weatherHandler.getWindInfo();
                    //----------------------------

                    Intent intent = new Intent(this,WeatherActivity.class);

                    //--These are for readability--
                    ArrayList<String> weatherInfo = new ArrayList<String>();
                    weatherInfo.add(cityname.toString());
                    weatherInfo.add(tempInfo.toString());
                    weatherInfo.add(cloudInfo.toString());
                    weatherInfo.add(windInfo.toString());

                    intent.putExtra(CONSTANTS.BUNDLE_KEY, weatherInfo);
                    startActivity(intent);

                }else{
                    showToastError();
            }



        } catch (APIException e) {
           showToastError();
        }
    }




private void getCityNames(){




}


    /**
     * used if APIexception / error is given
     * handles the exception and gives the user a toast.
     */
    private void showToastError(){
        Context context = getApplicationContext();
        CharSequence text = CONSTANTS.ERROR_TOAST;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }









}
