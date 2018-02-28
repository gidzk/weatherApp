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
    //private boolean isMetric;                 NOT USED FOR THIS VERSION
    //private OWM.Unit temperatureUnit;         NOT USED FOR THIS VERSION
    private static  WeatherHandler singleton;
    private CurrentWeather currentWeather;

    private WeatherParser() {
      //  isMetric = true;                      NOT USED FOR THIS VERSION
      //  temperatureUnit = OWM.Unit.METRIC;    NOT USED FOR THIS VERSION
        openWeatherMap = new OWM(apiKey);
        openWeatherMap.setUnit(OWM.Unit.METRIC);

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
    public boolean setCity(String cityname) throws APIException {
        this.currentWeather = openWeatherMap.currentWeatherByCityName(cityname);

        return (currentWeather.hasCityName());

    }




    @Override
    public String getCityName() throws APIException {
        if (currentWeather.hasCityName())
        return currentWeather.getCityName();
        else return "No Name available \n";

    }


    /**
     * @return string with information on the current temp/humidity of the city
     */
    @Override
    public String getTempInfo() throws APIException {

        StringBuilder trg = new StringBuilder();


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

        // checks that the cloudData exists, if not appends that there is no data available
        if(currentWeather.hasCloudData()){
        trg.append(currentWeather.getCloudData().getCloudiness()).append("% \n");}

        else{
            trg.append("No cloud information avaivable \n");}

        return trg.toString();

    }
    /**
     * @return string with information on the current winddegree / windspeed of the city
     * if no information avaviable, appends no information string.
     */
    @Override
    public String getWindInfo() throws APIException {

        StringBuilder trg = new StringBuilder();
        trg.append("Speed: ");
        // checks that the windSpeed exists, if not appends that there is no data available
        if(currentWeather.component13().hasSpeed()) {
            double windSpeed = currentWeather.component13().component1();
            trg.append(windSpeed).append(" meters per second \n");
        }

        else  trg.append("No weatherspeed data available \n");


        trg.append("Windangle /degree: ");

        trg.append(currentWeather.getWindData().component2()).append("° \n");


        return trg.toString();


    }


// ===================== METHODS USED FOR TESTING =====================


    public CurrentWeather getCurrentWeather(){

        return this.currentWeather;

    }



    /**
     * @return minimal measured temperature of the @param cityname
     * @throws APIException if the current City does not exist.
     *
     * USED FOR TESTING
     */
    @Override
    public String getBaseWeatherInfo() throws APIException {
        StringBuilder trg = new StringBuilder();

        trg.append(getCloudInfo());
        trg.append(getWindInfo());
        trg.append(getTempInfo());


        return trg.toString();
    }



    /**
     * @param
     * @return the current temperature of the city
     */
    private Double getCityTemperature(String cityName) throws APIException {

        CurrentWeather cityContainer = openWeatherMap.currentWeatherByCityName(cityName);
        return cityContainer.getMainData().getTemp();
    }




}
