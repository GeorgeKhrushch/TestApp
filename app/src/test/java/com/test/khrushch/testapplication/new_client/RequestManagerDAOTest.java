package com.test.khrushch.testapplication.new_client;

import com.test.khrushch.testapplication.new_client.exception.ParseException;
import com.test.khrushch.testapplication.structure.WeatherInfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ultra on 02.04.18.
 */
public class RequestManagerDAOTest {

    @Test
    public void shouldParseValidWeatherInfo() throws Exception {
        String json = "{\"coord\":{\"lon\":24.02,\"lat\":49.84},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"пасмурно\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":9,\"pressure\":1010,\"humidity\":70,\"temp_min\":9,\"temp_max\":9},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":150},\"clouds\":{\"all\":75},\"dt\":1522427400,\"sys\":{\"type\":1,\"id\":7361,\"message\":0.0023,\"country\":\"UA\",\"sunrise\":1522382639,\"sunset\":1522428801},\"id\":702550,\"name\":\"Lviv\",\"cod\":200}";
        WeatherInfo info = RequestManagerDAO.remapSingleResponse(json, WeatherInfo.class);
        assertTrue(info.getWeather().get(0).getIcon().equals("04d"));
        assertEquals((long) info.getSys().getId(), (long)7361);
        assertEquals((float) info.getMain().getTemp(), (float)9, 0.005);
    }

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionWhenParseInvalidWeatherInfo() throws Exception {
        String json = "{\"coord\":{\"lon\":24.02,\"lat\":49.84},\"weather\":[{\"id\":803,\"main\"";
        WeatherInfo info = RequestManagerDAO.remapSingleResponse(json, WeatherInfo.class);
    }
}