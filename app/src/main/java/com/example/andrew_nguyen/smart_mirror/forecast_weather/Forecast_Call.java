package com.example.andrew_nguyen.smart_mirror.forecast_weather;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Forecast_Call {
    //OpenWeatherUrl
    final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    //OpenWeatherAPIKey
    final String OWAPIKEY = "c0c4a4b4047b97ebc5948ac9c48c0559";

    public String makeServiceCallForecast(String latitude, String longitude) {
        String OWUrl = OPEN_WEATHER_URL + "forecast/daily?lat=" + latitude + "&lon=" + longitude + "&units=imperial&cnt=5&lang=en&appid=" + OWAPIKEY;
        return Http_Service.get_json(OWUrl);
    }
}
