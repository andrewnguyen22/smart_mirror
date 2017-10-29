package com.example.andrew_nguyen.smart_mirror.todays_weather;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.tools.Tools;
import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Reverse_Geocode_Parser extends AsyncTask<Void, Void, Void> {
    private Handler uiHandler;

    public Reverse_Geocode_Parser() {
        uiHandler = new Handler(Looper.getMainLooper());
    }
    private String formatted_address;
    @Override
    protected Void doInBackground(Void... voids) {
        try {//Parsing...
            String json = new Reverse_Geocode_Call().reverse_geo_call(""+ Double.toString(Home.longitude)+"," +Double.toString(Home.latitude));
            Log.d("Reverse Parser", "doInBackground: Response from server: "+ json);
            JSONObject reader = new JSONObject(json);
            JSONObject address = reader.getJSONObject("address");
            String country = address.getString("CountryCode");
            String state = address.getString("Region");
            String city = address.getString("City");
            if(country.trim().equals("USA")){
                formatted_address= city +", "+state;
            }
            else{
                String country_name = Country.getCountryFromCode(country);
                country_name = Tools.capitalizeString(country_name);
                if(!TextUtils.isEmpty(city))
                    formatted_address=Tools.remove_special(city+", "+ country_name);
                else
                    formatted_address=Tools.remove_special(country_name);
            }
            //Update UI
            //Updating info to UI...
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    Home.address=formatted_address;
                    Home.todays_location_tv.setText(formatted_address);
                    Log.e("Address", formatted_address );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

