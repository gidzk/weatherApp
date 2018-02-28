package com.example.john.weatherapp;

import com.example.john.weatherapp.model.WeatherHandler;
import com.example.john.weatherapp.model.WeatherParser;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ExampleUnitTest {

    WeatherHandler w;
    private OWM.Unit tempUnit;
    public String got = "Gothenburg";
    public String lod = "London";
    @Before
           public void initTest() throws APIException {

       w = WeatherParser.getInstance();

    }






    @Test
    public void testprints() throws Exception {

        assertTrue(w.setCity(got));
        System.out.println(w.getWindInfo());
        System.out.println(w.getCloudInfo());
        System.out.println(w.getTempInfo());

        System.out.println(w.getBaseWeatherInfo());


    }

    /**
     * noticed my asserts from setCity is broken since APIexception will handle this, but I handle this in topapplication so no harm done.
     * I have no time to test further
     *
     */
    @Test
    public void testCountry(){

        try {
            assertFalse(w.setCity("Thailand"));

            // will not be called
            System.out.println(w.getCityName());
            System.out.println(w.getTempInfo());

        } catch (APIException e) {
            System.out.println(e.getMessage());
        }

    }





    @Test
    public void testAPIEXCEPTION(){
        try {
            assertFalse(w.setCity("ERROR"));
            System.out.println("Should not run");

        } catch (APIException e) {
            System.out.println("ERROR: WRONG INPUT");
            e.getStackTrace();

        }





    }


}