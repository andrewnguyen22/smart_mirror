package com.example.andrew_nguyen.smart_mirror.todays_weather;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by andrewnguyen on 10/23/16.
 */

public class Weather_Call {
    //OpenWeatherUrl
    final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    //OpenWeatherAPIKey
    final String OWAPIKEY = "c0c4a4b4047b97ebc5948ac9c48c0559";

    public String makeServiceCall(String latitude, String longitude) {
        String url = OPEN_WEATHER_URL + "weather?lat=" + latitude + "&lon=" + longitude + "&units=imperial&appid=" + OWAPIKEY;
        return Http_Service.get_json(url);
    }

}

