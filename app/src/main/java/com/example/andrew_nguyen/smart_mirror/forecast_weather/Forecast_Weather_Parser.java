package com.example.andrew_nguyen.smart_mirror.forecast_weather;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrew_nguyen.smart_mirror.tools.Tools;
import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Forecast_Weather_Parser extends AsyncTask<Void, Void, Void> {
    Double latitude, longitude;
    Context ctx;
    private Handler uiHandler;
    final String TAG = "Forcast_Weathr_Parser: ";

    public Forecast_Weather_Parser(Double l, Double lo, Context c) {
        super();
        latitude = l;
        longitude = lo;
        ctx = c;
        uiHandler = new Handler(Looper.getMainLooper());
    }
    @Override
    protected Void doInBackground(Void... voids) {
        //Parse the forecast info...
        final Forecast_Day[] forecastDay_array = new Forecast_Day[5];
        Forecast_Call fc = new Forecast_Call();
        String forecast_json = fc.makeServiceCallForecast(Double.toString(latitude), Double.toString(longitude));
        Log.d(TAG, "Response from url: " + forecast_json);
        if (forecast_json != null) {
            try {
                JSONObject forecast_day, weather_object, temp, reader;
                JSONArray weather, list;
                String description, icn, unix, temperature;
                reader = new JSONObject(forecast_json);
                list = reader.getJSONArray("list");
                for (int i = 0; i < 5; i++) {//5day forcast
                    forecast_day = list.getJSONObject(i);
                    weather = forecast_day.getJSONArray("weather");
                    weather_object = weather.getJSONObject(0);
                    description = weather_object.getString("description");
                    icn = weather_object.getString("icon");
                    unix = forecast_day.getString("dt");
                    temp = forecast_day.getJSONObject("temp");
                    temperature = Tools.no_decimal_string(temp.getString("day")) + " ℉";
                    Forecast_Day forecastDay = new Forecast_Day(unix, temperature, icn, description, ctx);
                    forecastDay_array[i] = forecastDay;
                    //TODO get the data from the array to the ui
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "Couldn't get 5 day forecast json from server.");
        }
        //Updating Ui now...
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i<5; i++){
                        switch(i){
                            case 0:
                                update_ui(Home.day_1_day_tv, Home.day_1_temp_tv, Home.day_1_iv, forecastDay_array[0]);
                                break;
                            case 1:
                                update_ui(Home.day_2_day_tv, Home.day_2_temp_tv, Home.day_2_iv, forecastDay_array[1]);
                                break;
                            case 2:
                                update_ui(Home.day_3_day_tv, Home.day_3_temp_tv, Home.day_3_iv, forecastDay_array[2]);
                                break;
                            case 3:
                                update_ui(Home.day_4_day_tv, Home.day_4_temp_tv, Home.day_4_iv, forecastDay_array[3]);
                                break;
                            case 4:
                                update_ui(Home.day_5_day_tv, Home.day_5_temp_tv, Home.day_5_iv, forecastDay_array[4]);
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }
    public void update_ui(TextView dow, TextView temp, ImageView icn, Forecast_Day fd){
        dow.setText(Tools.capitalizeString(fd.day_of_the_week));
        icn.setImageDrawable(fd.icon);
        temp.setText(fd.temperature);
    }
}
