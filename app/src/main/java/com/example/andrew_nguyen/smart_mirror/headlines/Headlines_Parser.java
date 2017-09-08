package com.example.andrew_nguyen.smart_mirror.headlines;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.todays_weather.Todays_Weather_Parser;
import com.example.andrew_nguyen.smart_mirror.tools.Tools;
import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Headlines_Parser extends AsyncTask<Void, Void, Void> {
    final String TAG = "Headlines Parser: ";
    Handler uiHandler;
    private String headline_1, headline_2, headline_3, headline_4, title, description;
    public Headlines_Parser() {
        uiHandler = new Handler(Looper.getMainLooper());
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {//Parsing Json...
            String[] headlines = new String[4];
            JSONObject reader, article;
            JSONArray articles;
            Headlines_Call hc = new Headlines_Call();
            Log.d(TAG, "doInBackground: Response from URL: " + hc.makeHeadlinesCall());
            reader = new JSONObject(hc.makeHeadlinesCall());
            articles = reader.getJSONArray("articles");
            for(int i = 0; i<4; i++){
                article = articles.getJSONObject(i);
                title = article.getString("title");
                description = article.getString("description");
                if(Tools.countWords(title)<4){
                    headlines[i] = description;
                }
                else{
                    headlines[i] = title;
                }
            }
            headline_1 = headlines[0];
            headline_2 = headlines[1];
            headline_3 = headlines[2];
            headline_4 = headlines[3];

        //Update UI...
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Home.headline_1_tv.setText(headline_1);
                Home.headline_2_tv.setText(headline_2);
                Home.headline_3_tv.setText(headline_3);
                Home.headline_4_tv.setText(headline_4);
            }
        });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
