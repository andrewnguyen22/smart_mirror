package com.example.andrew_nguyen.smart_mirror.todays_weather;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.ui.Home;
import com.example.andrew_nguyen.smart_mirror.tools.Icon_Translator;
import com.example.andrew_nguyen.smart_mirror.tools.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Todays_Weather_Parser extends AsyncTask<Void, Void, Void> {
    Double latitude, longitude;
    Context ctx;
    private Handler uiHandler;
    final String TAG = "Todays Weather Parser: ";

    public Todays_Weather_Parser(Double l, Double lo, Context c) {
        super();
        latitude = l;
        longitude = lo;
        ctx = c;
        uiHandler = new Handler(Looper.getMainLooper());
    }

    private String weather_icon_code, iconUrl, todays_humidity, todays_high, todays_low, weather_description, todays_location, todays_temperature, cityname, unix_utc_sunset, unix_utc_sunrise;

    @Override
    protected Void doInBackground(Void... voids) {
        Weather_Call weather_call = new Weather_Call();
        String todays_weather_json_string = weather_call.makeServiceCall(Double.toString(latitude), Double.toString(longitude));
        Log.d(TAG, "Response from url: " + todays_weather_json_string);
        //Parsing...
        if (todays_weather_json_string != null) {
            try {
                JSONObject reader = new JSONObject(todays_weather_json_string);
                JSONArray weather_array = reader.getJSONArray("weather");
                JSONObject weather = weather_array.getJSONObject(0);
                JSONObject main2 = reader.getJSONObject("main");
                JSONObject sys = reader.getJSONObject("sys");
                //todays weather icon
                weather_icon_code = weather.getString("icon");
                iconUrl = "http://openweathermap.org/img/w/" + weather_icon_code + ".png";
                //todays weather description
                weather_description = weather.getString("description");
                //todays humidity
                todays_humidity = "Humidity: " + main2.getString("humidity") + "%";
                //todays high / low
                todays_high = Tools.no_decimal_string(main2.getString("temp_max")) + " ℉";
                todays_low = Tools.no_decimal_string(main2.getString("temp_min")) + "℉";
                todays_temperature = Tools.no_decimal_string(main2.getString("temp")) + "℉";
                //Sunrise Sunset
                unix_utc_sunrise = sys.getString("sunrise");
                unix_utc_sunset = sys.getString("sunset");
                //Name
                cityname = reader.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "Couldn't get current weather json from server.");
        }
        //Updating info to UI...
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Home.todays_weather_iv.setBackgroundDrawable(new Icon_Translator().translate(ctx, weather_icon_code, weather_description));
                    Home.todays_weather_descript_tv.setText(Tools.capitalizeString(weather_description));
                    Home.todays_humidity_tv.setText(todays_humidity);
                    Home.todays_high_low_tv.setText("High: " + todays_high + "  |  Low: " + todays_low);
                    Home.todays_location_tv.setText(todays_location);
                    Home.todays_temperature_tv.setText(todays_temperature);
                    Home.todays_location_tv.setText(cityname);
                    //TODO Get City and state and '.setText(cityname, statename);'
                    //Sunrise and Sunset
                    long unixTime = System.currentTimeMillis() / 1000L;
                    long sunset = Long.parseLong(unix_utc_sunset);
                    long sunrise = Long.parseLong(unix_utc_sunrise);
                    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm aa");
                    time_format.setTimeZone(TimeZone.getDefault());
                    if (unixTime > sunrise && unixTime < sunset) {//After Sunrise show sunset time
                        Date d = new Date(sunset * 1000);
                        Home.todays_sunset_sunrise_tv.setText("The sun will set at " + time_format.format(d));
                    } else {//Before Sunrise show sunrise time
                        Date d = new Date(sunrise * 1000);
                        Home.todays_sunset_sunrise_tv.setText("The sun will rise at " + time_format.format(d));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }
}
