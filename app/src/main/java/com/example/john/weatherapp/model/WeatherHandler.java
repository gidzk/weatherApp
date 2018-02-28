package com.example.john.weatherapp.model;

import net.aksingh.owmjapis.api.APIException;

/**
 * Created by John on 2018-02-27.
 */

public interface WeatherHandler {


    public String getBaseWeatherInfo () throws APIException;
    public String getCityName        () throws APIException;


    public String getWindInfo  () throws  APIException;
    public String getCloudInfo  () throws APIException;
    public String getTempInfo   () throws APIException;


    public boolean setCountry(String country) throws APIException;


}
