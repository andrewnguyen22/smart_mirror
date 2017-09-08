package com.example.andrew_nguyen.smart_mirror.driving_distance;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Distance_Parser extends AsyncTask<Void, Void, Void> {
    private Handler uiHandler;

    public Distance_Parser() {
        uiHandler = new Handler(Looper.getMainLooper());
    }
    private String duration1, duration2;
    @Override
    protected Void doInBackground(Void... voids) {
        try {//Parsing...
            String json = new Distance_Call().make_driving_call(Double.toString(Home.latitude), Double.toString(Home.longitude), Home.des1, Home.des2);
            Log.d("DD Parser", "doInBackground: Response from server: "+ json);
            JSONObject reader = new JSONObject(json);
            JSONArray rows = reader.getJSONArray("rows");
            JSONObject rows_ = rows.getJSONObject(0);
            JSONArray elements = rows_.getJSONArray("elements");
            JSONObject des_1 = elements.getJSONObject(0);
            JSONObject des_2 = elements.getJSONObject(1);
            JSONObject duration_1 = des_1.getJSONObject("duration");
            JSONObject duration_2 = des_2.getJSONObject("duration");
            duration1 = duration_1.getString("text");
            duration2 = duration_2.getString("text");

            //Update UI
            //Updating info to UI...
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    Home.driving_1_tv.setText("It takes "+duration1 +" to get to "+ Home.des1);
                    Home.driving_2_tv.setText("It takes "+duration2 + " to get to "+ Home.des2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
