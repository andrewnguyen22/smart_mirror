package com.example.andrew_nguyen.smart_mirror.twitter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew_nguyen on 9/9/17.
 */

public class Get_Trends extends AsyncTask<Void, Void, Void>{
    String twitter_url;
    Handler uiHandler;
    public Get_Trends() {
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final String TAG = "Get Trends: ";
        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();
        final List<String> tweet_name_list = new ArrayList<String>(), tweet_volume_list = new ArrayList<String>();
        final List<TextView> tv_list = new ArrayList<TextView>();
        try {
            Log.e(TAG, "Get Trends Called");
            URL url = new URL(Constant_Utils.URL_USA_TRENDING);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");

            String jsonString = Twitter_Authentication.appAuthentication();
            JSONObject jsonObjectDocument = new JSONObject(jsonString);
            String token = jsonObjectDocument.getString("token_type") + " "
                    + jsonObjectDocument.getString("access_token");
            httpConnection.setRequestProperty("Authorization", token);

            httpConnection.setRequestProperty("Authorization", token);
            httpConnection.setRequestProperty("Content-Type",
                    "application/json");
            httpConnection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            Log.d(TAG,
                    "GET response code: "
                            + String.valueOf(httpConnection
                            .getResponseCode()));
            Log.d(TAG, "JSON response: " + response.toString());
            //Parse JSON Respons
            JSONArray reader = new JSONArray(response.toString());
            JSONObject reader_ = reader.getJSONObject(0);
            JSONArray trends = reader_.getJSONArray("trends");
            for(int i = 0; i<5; i++){
                JSONObject tweet = trends.getJSONObject(i);
                tweet_name_list.add(tweet.getString("name"));
                tweet_volume_list.add(tweet.getString("tweet_volume"));
            }
            //Update UI
            tv_list.add(Home.tweet1_tv);
            tv_list.add(Home.tweet2_tv);
            tv_list.add(Home.tweet3_tv);
            tv_list.add(Home.tweet4_tv);
            tv_list.add(Home.tweet5_tv);
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i<5; i++) {
                        if (tweet_volume_list.get(i).equals("null")) {
                            tv_list.get(i).setText(tweet_name_list.get(i));
                        } else {
                            tv_list.get(i).setText(tweet_name_list.get(i) + " - " + "Currently " + tweet_volume_list.get(i) + " tweets");

                        }
                    }
                }

            });

        } catch (Exception e) {
            Log.e(TAG, "GET error: " + Log.getStackTraceString(e));

        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();

            }
        }

        return null;
    }
}
