package com.example.john.weatherapp.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

/**
 * Created by John on 2018-02-27.
 */

public class WeatherParser implements WeatherHandler {
    private OWM openWeatherMap;
    private final String apiKey = "bd650fca069330b6e8e745b64994dc7e";
    private boolean isMetric;
    private OWM.Unit temperatureUnit;
    private static  WeatherHandler singleton;
    private CurrentWeather currentWeather;

    private WeatherParser() {
        isMetric = true;
        temperatureUnit = OWM.Unit.METRIC;
        openWeatherMap = new OWM(apiKey);
        openWeatherMap.setUnit(OWM.Unit.METRIC);

    }




    /**
     * @param
     * @return the current temperature of the city
     */
    public Double getCityTemperature(String cityName) throws APIException {

        CurrentWeather cityContainer = openWeatherMap.currentWeatherByCityName(cityName);
        return cityContainer.getMainData().getTemp();
    }

    public static WeatherHandler getInstance() {
        if (singleton == null){
            singleton = new WeatherParser();
        }
        return singleton;
    }


    /**
     *
     * @param cityname sets the name of the city to be searched for
     * @return true if the cityname exists, else false
     */
    @Override
    public boolean setCountry(String cityname) throws APIException {
        this.currentWeather = openWeatherMap.currentWeatherByCityName(cityname);

        return (currentWeather.hasCityName());

    }


    /**
     * @return minimal measured temperature of the @param cityname
     * @throws APIException if the current City does not exist.
     */
    @Override
    public String getBaseWeatherInfo() throws APIException {
        StringBuilder trg = new StringBuilder();

        trg.append(getCloudInfo());
        trg.append(getWindInfo());
        trg.append(getTempInfo());


        return trg.toString();
    }


    @Override
    public String getCityName() throws APIException {
        if (currentWeather.hasCityName())
        return currentWeather.getCityName();
        else return "No Name aviable \n";

    }


    /**
     * @return string with information on the current temp/humidity of the city
     */
    @Override
    public String getTempInfo() throws APIException {

        StringBuilder trg = new StringBuilder();

        // adds the minimal measured temperature
        trg.append("Temperature interval: ");

        if(currentWeather.getMainData().hasTempMin())
                trg.append(currentWeather.getMainData().getTempMin());
        else    trg.append("No mintemp data available \n");


        if(currentWeather.getMainData().hasTempMax())
                trg.append(" to ").append(currentWeather.getMainData().getTempMax()).append("° C\n");
        else    trg.append("No maxtemp data available \n");


        if(currentWeather.getMainData().hasHumidity())
                trg.append("Humidity: ").append(currentWeather.getMainData().getHumidity()).append( "%\n");
        else    trg.append("No Humidity data available \n");


        return trg.toString();

    }



    /**
     * @return string with information on the current cloudpercentage of the city,
     *          if no information avaviable, appends no information string.
     *
     */
    @Override
    public String getCloudInfo() throws APIException {

        StringBuilder trg = new StringBuilder();
        trg.append("Cloud ratio: ");

        if(currentWeather.hasCloudData()){
        trg.append(currentWeather.getCloudData().getCloudiness()).append("% \n");}

        else{
            trg.append("No cloud information avaivable \n");}

        return trg.toString();

    }
    /**
     * @param * @param cityName city to be searched for
     * @return string with information on the current winddegree / windspeed of the city
     *
     * if no information avaviable, appends no information string.

     */
    @Override
    public String getWindInfo() throws APIException {

        StringBuilder trg = new StringBuilder();
        trg.append("Speed: ");
        // checks that the speed exists, if not
        if(currentWeather.component13().hasSpeed()) {
            double windSpeed = currentWeather.component13().component1();
            trg.append(windSpeed).append(" meters per second \n");
        }

        else  trg.append("No weatherspeed data available \n");


        trg.append("Windangle /degree: ");

        trg.append(currentWeather.getWindData().component2()).append("° \n");


        return trg.toString();


    }






}
