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




      //  owm = new OWM("bd650fca069330b6e8e745b64994dc7e");

    }






    @Test
    public void testprints() throws Exception {

        assertTrue(w.setCountry(got));
        System.out.println(w.getWindInfo());
        System.out.println(w.getCloudInfo());
        System.out.println(w.getTempInfo());

        System.out.println(w.getBaseWeatherInfo());


    }

    @Test
    public void testAPIEXCEPTION(){
        try {
            assertFalse(w.setCountry("ERROR"));
            System.out.println("Should not run");

        } catch (APIException e) {
            System.out.println("ERROR: WRONG INPUT");

        }


    }


}