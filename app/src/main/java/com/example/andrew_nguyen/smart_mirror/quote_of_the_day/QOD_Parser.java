package com.example.andrew_nguyen.smart_mirror.quote_of_the_day;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by andrew_nguyen on 9/9/17.
 */

public class QOD_Parser extends AsyncTask<Void, Void, Void> {
    final String TAG = "QOD Parser: ";
    Handler uiHandler;
    public QOD_Parser(){
        uiHandler = new Handler(Looper.getMainLooper());
    }
    String author, quote;
    @Override
    protected Void doInBackground(Void... voids) {
        try {//Parsing Json...
            String json = new QOD_Call().make_call().substring(2);
            json = json.substring(0, json.length() - 1);
            JSONObject reader = new JSONObject(json);
            quote = reader.getString("quoteText");
            author = reader.getString("quoteAuthor");

            //Update UI
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    Home.quote_of_the_day_tv.setText(quote + " - " + author);
                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
