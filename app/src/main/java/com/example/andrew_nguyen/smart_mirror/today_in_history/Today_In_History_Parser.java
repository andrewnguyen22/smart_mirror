package com.example.andrew_nguyen.smart_mirror.today_in_history;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.andrew_nguyen.smart_mirror.tools.Tools;
import com.example.andrew_nguyen.smart_mirror.ui.Home;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Today_In_History_Parser extends AsyncTask<Void, Void, Void>{
    Handler uiHandler;
    public Today_In_History_Parser() {
        uiHandler = new Handler(Looper.getMainLooper());
    }
    private JSONArray events;
    Event[] event_array;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Parsing...
            String json = new Today_In_History_Call().make_history_call();
            JSONObject reader = new JSONObject(json);
            JSONObject data = reader.getJSONObject("data");
            events = data.getJSONArray("Events");
            JSONObject event;
            String year, text;
            event_array = new Event[events.length()];
            for(int i =0; i<events.length(); i++){
                event = events.getJSONObject(i);
                year = Tools.remove_special(event.getString("year"));
                text = Tools.remove_special(event.getString("text"));
                event_array[i] = new Event(year, text);
            }
            //Update UI
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    int i = random.nextInt(events.length());
                    Home.today_in_history_tv.setText(event_array[i].getText() + " - " + event_array[i].getYear());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
