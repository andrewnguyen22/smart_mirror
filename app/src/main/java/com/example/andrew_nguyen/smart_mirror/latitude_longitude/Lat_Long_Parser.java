package com.example.andrew_nguyen.smart_mirror.latitude_longitude;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.ui.Home;
import com.example.andrew_nguyen.smart_mirror.ui.Main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Lat_Long_Parser extends AsyncTask<Void, Void, Void> {
    private Handler uiHandler;

    public Lat_Long_Parser() {
        uiHandler = new Handler(Looper.getMainLooper());
    }
    private String formatted_address, lat, lng;
    @Override
    protected Void doInBackground(Void... voids) {
        try {//Parsing...
            String json = new Lat_Long_Call().make_lat_long_call(Home.address);
            Log.d("Lat Long Parser", "doInBackground: Response from server: "+ json);
            JSONObject reader = new JSONObject(json);
            JSONArray results = reader.getJSONArray("results");
            JSONObject results_ = results.getJSONObject(0);
            JSONObject geometry = results_.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            lat=location.getString("lat");
            lng=location.getString("lng");
            formatted_address = results_.getString("formatted_address");
            List<String> address = Arrays.asList(formatted_address.split(","));
            address_format(address, address.size());

            //Update UI
            //Updating info to UI...
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    Home.longitude=Double.parseDouble(lng);
                    Home.latitude=Double.parseDouble(lat);
                    Home.address=formatted_address;
                    Home.location_change(Main.ctx);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void address_format(List<String> addr, int size){
        try {
            String state;
            switch (size) {
                case 1:
                    formatted_address = addr.get(0);
                    break;
                case 2:
                    formatted_address = addr.get(0) + ", " + addr.get(1);
                    break;
                case 3:
                    formatted_address = addr.get(0) + ", " + addr.get(1);
                    break;
                case 4:
                    state = addr.get(2);
                    state = state.replaceAll("[0-9]","");
                    state = state.replaceAll("\\s+","");
                    formatted_address = addr.get(1) + ", " + state;
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

