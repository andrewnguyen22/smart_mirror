package com.example.andrew_nguyen.smart_mirror.ui;

import android.content.Context;
import android.location.Location;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andrew_nguyen.smart_mirror.groceries.Gmail_Call;
import com.example.andrew_nguyen.smart_mirror.R;
import com.example.andrew_nguyen.smart_mirror.driving_distance.Distance_Dialog;
import com.example.andrew_nguyen.smart_mirror.driving_distance.Distance_Parser;
import com.example.andrew_nguyen.smart_mirror.forecast_weather.Forecast_Weather_Parser;
import com.example.andrew_nguyen.smart_mirror.google_calendar.Calendar_Call;
import com.example.andrew_nguyen.smart_mirror.google_calendar.Event_Lists;
import com.example.andrew_nguyen.smart_mirror.headlines.Headlines_Parser;
import com.example.andrew_nguyen.smart_mirror.quote_of_the_day.QOD_Parser;
import com.example.andrew_nguyen.smart_mirror.today_in_history.Today_In_History_Parser;
import com.example.andrew_nguyen.smart_mirror.todays_weather.Todays_Weather_Parser;
import com.example.andrew_nguyen.smart_mirror.tools.Tools;
import com.example.andrew_nguyen.smart_mirror.twitter.Get_Trends;

import java.util.Timer;
import java.util.TimerTask;

public class Home extends Fragment{
    final String TAG = "Home";

    //Today's Weather
    public static ImageView todays_weather_iv;
    public static TextView todays_weather_descript_tv,
            todays_humidity_tv,
            todays_high_low_tv,
            todays_location_tv,
            todays_temperature_tv,
            todays_sunset_sunrise_tv;

    //5 Day Forecast
    public static ImageView day_1_iv,
            day_2_iv,
            day_3_iv,
            day_4_iv,
            day_5_iv;
    public static TextView day_1_day_tv,
            day_2_day_tv,
            day_3_day_tv,
            day_4_day_tv,
            day_5_day_tv,

    day_1_temp_tv,
            day_2_temp_tv,
            day_3_temp_tv,
            day_4_temp_tv,
            day_5_temp_tv;

    //Headlines
    public static TextView headline_1_tv,
            headline_2_tv,
            headline_3_tv,
            headline_4_tv;

    //Date and Time
    public static TextView time_tv;

    //driving
    public static TextView driving_1_tv,
            driving_2_tv;
    public static String des1 = "SOFWERX", des2 = "St Josephs John Knox";

    //today in history
    public static TextView today_in_history_tv;

    //Lightswitch & Button
    ImageView lightswitch_iv;

    //Grocery List
    public static TextView gitem1_tv,
            gitem2_tv,
            gitem3_tv,
            gitem4_tv,
            gitem5_tv,
            gitem6_tv,
            gitem7_tv,
            gitem8_tv,
            gitem9_tv,
            gitem10_tv,
            gitem11_tv,
            gitem12_tv,
            gitem13_tv,
            gitem14_tv,
            gitem15_tv,
            gitem16_tv,
            gitem17_tv,
            gitem18_tv,
            gitem19_tv,
            gitem20_tv,
            gitem21_tv,
            gitem22_tv,
            gitem23_tv,
            gitem24_tv;

    //Quote of the day
    public static TextView quote_of_the_day_tv;

    //Trending Topics
    public static TextView tweet1_tv,
            tweet2_tv,
            tweet3_tv,
            tweet4_tv,
            tweet5_tv,
            tweet6_tv;

    //Andrews Schedule
    public static TextView a_event1_tv,
            a_event2_tv,
            a_event3_tv,
            a_event4_tv,
            a_event5_tv,
            a_event6_tv,
            a_event7_tv,
            a_event8_tv,
            a_event9_tv;

    //Kelseys Schedule
    public static TextView k_event1_tv,
            k_event2_tv,
            k_event3_tv,
            k_event4_tv,
            k_event5_tv,
            k_event6_tv,
            k_event7_tv,
            k_event8_tv,
            k_event9_tv;
    byte lights_status = 1;
    MediaPlayer light_switch_sound;
    //Latitude and Longitude
    public static double latitude, longitude;
    public static String cityname, statename;
    //Context
    Context ctx;

    LinearLayout topBar, blue_bar_top, r_top, driving, r_mid_blue_bar, history, rbot_blue_bar, grocery, left_sidebar;
    RelativeLayout middle;
    public Home() {
        //Constructor
    }
    public static Home newInstance(String example_argument) {
         Home tabFragmentOne = new Home();
        return tabFragmentOne;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Start Variable Declaration *
        ctx = getActivity();
        //Today
        todays_weather_iv = (ImageView) getActivity().findViewById(R.id.todays_weather_icon);
        todays_weather_descript_tv = (TextView) getActivity().findViewById(R.id.weather_description);
        todays_humidity_tv = (TextView) getActivity().findViewById(R.id.humidity);
        todays_high_low_tv = (TextView) getActivity().findViewById(R.id.high_low_temp);
        todays_location_tv = (TextView) getActivity().findViewById(R.id.weather_location);
        todays_temperature_tv = (TextView) getActivity().findViewById(R.id.todays_temperature);
        todays_sunset_sunrise_tv = (TextView) getActivity().findViewById(R.id.sunrise_sunset);
        //5 day forecast
        day_1_day_tv = (TextView) getActivity().findViewById(R.id.day_1_title_tv);
        day_2_day_tv = (TextView) getActivity().findViewById(R.id.day_2_title_tv);
        day_3_day_tv = (TextView) getActivity().findViewById(R.id.day_3_title_tv);
        day_4_day_tv = (TextView) getActivity().findViewById(R.id.day_4_title_tv);
        day_5_day_tv = (TextView) getActivity().findViewById(R.id.day_5_title_tv);
        day_1_iv = (ImageView) getActivity().findViewById(R.id.day_1_iv);
        day_2_iv = (ImageView) getActivity().findViewById(R.id.day_2_iv);
        day_3_iv = (ImageView) getActivity().findViewById(R.id.day_3_iv);
        day_4_iv = (ImageView) getActivity().findViewById(R.id.day_4_iv);
        day_5_iv = (ImageView) getActivity().findViewById(R.id.day_5_iv);
        day_1_temp_tv = (TextView) getActivity().findViewById(R.id.day_1_temp_tv);
        day_2_temp_tv = (TextView) getActivity().findViewById(R.id.day_2_temp_tv);
        day_3_temp_tv = (TextView) getActivity().findViewById(R.id.day_3_temp_tv);
        day_4_temp_tv = (TextView) getActivity().findViewById(R.id.day_4_temp_tv);
        day_5_temp_tv = (TextView) getActivity().findViewById(R.id.day_5_temp_tv);
        //Headlines
        headline_1_tv = (TextView) getActivity().findViewById(R.id.headline_1_tv);
        headline_2_tv = (TextView) getActivity().findViewById(R.id.headline_2_tv);
        headline_3_tv = (TextView) getActivity().findViewById(R.id.headline_3_tv);
        headline_4_tv = (TextView) getActivity().findViewById(R.id.headline_4_tv);
        //Date_Time
        time_tv = (TextView) getActivity().findViewById(R.id.time_tv);
        //driving today
        driving_1_tv = (TextView) getActivity().findViewById(R.id.driving_to_location_1);
        driving_2_tv = (TextView) getActivity().findViewById(R.id.driving_to_location_2);
        //Today in History
        today_in_history_tv = (TextView) getActivity().findViewById(R.id.today_in_history);
        //Light switch and next button
        lightswitch_iv = (ImageView) getActivity().findViewById(R.id.lightswitch_iv);
        lightswitch_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lights_out();
            }
        });
        //Grocery List
        gitem1_tv = (TextView) getActivity().findViewById(R.id.shopping_item_1);
        gitem2_tv = (TextView) getActivity().findViewById(R.id.shopping_item_2);
        gitem3_tv = (TextView) getActivity().findViewById(R.id.shopping_item_3);
        gitem4_tv = (TextView) getActivity().findViewById(R.id.shopping_item_4);
        gitem5_tv = (TextView) getActivity().findViewById(R.id.shopping_item_5);
        gitem6_tv = (TextView) getActivity().findViewById(R.id.shopping_item_6);
        gitem7_tv = (TextView) getActivity().findViewById(R.id.shopping_item_7);
        gitem8_tv = (TextView) getActivity().findViewById(R.id.shopping_item_8);
        gitem9_tv = (TextView) getActivity().findViewById(R.id.shopping_item_9);
        gitem10_tv = (TextView) getActivity().findViewById(R.id.shopping_item_10);
        gitem11_tv = (TextView) getActivity().findViewById(R.id.shopping_item_11);
        gitem12_tv = (TextView) getActivity().findViewById(R.id.shopping_item_12);
        gitem13_tv = (TextView) getActivity().findViewById(R.id.shopping_item_13);
        gitem14_tv = (TextView) getActivity().findViewById(R.id.shopping_item_14);
        gitem15_tv = (TextView) getActivity().findViewById(R.id.shopping_item_15);
        gitem16_tv = (TextView) getActivity().findViewById(R.id.shopping_item_16);
        gitem17_tv = (TextView) getActivity().findViewById(R.id.shopping_item_17);
        gitem18_tv = (TextView) getActivity().findViewById(R.id.shopping_item_18);
        gitem19_tv = (TextView) getActivity().findViewById(R.id.shopping_item_19);
        gitem20_tv = (TextView) getActivity().findViewById(R.id.shopping_item_20);
        gitem21_tv = (TextView) getActivity().findViewById(R.id.shopping_item_21);
        gitem22_tv = (TextView) getActivity().findViewById(R.id.shopping_item_22);
        gitem23_tv = (TextView) getActivity().findViewById(R.id.shopping_item_23);
        gitem24_tv = (TextView) getActivity().findViewById(R.id.shopping_item_24);
        //Quote of the day
        quote_of_the_day_tv = (TextView) getActivity().findViewById(R.id.quote_of_the_day_tv);
        //trending
        tweet1_tv = (TextView) getActivity().findViewById(R.id.tweet_1);
        tweet2_tv = (TextView) getActivity().findViewById(R.id.tweet_2);
        tweet3_tv = (TextView) getActivity().findViewById(R.id.tweet_3);
        tweet4_tv = (TextView) getActivity().findViewById(R.id.tweet_4);
        tweet5_tv = (TextView) getActivity().findViewById(R.id.tweet_5);
        tweet6_tv = (TextView) getActivity().findViewById(R.id.tweet_6);
        //Andrews Schedule
        a_event1_tv = (TextView) getActivity().findViewById(R.id.andrews_1_task);
        a_event2_tv = (TextView) getActivity().findViewById(R.id.andrews_2_task);
        a_event3_tv = (TextView) getActivity().findViewById(R.id.andrews_3_task);
        a_event4_tv = (TextView) getActivity().findViewById(R.id.andrews_4_task);
        a_event5_tv = (TextView) getActivity().findViewById(R.id.andrews_5_task);
        a_event6_tv = (TextView) getActivity().findViewById(R.id.andrews_6_task);
        a_event7_tv = (TextView) getActivity().findViewById(R.id.andrews_7_task);
        a_event8_tv = (TextView) getActivity().findViewById(R.id.andrews_8_task);
        a_event9_tv = (TextView) getActivity().findViewById(R.id.andrews_9_task);
        //Kelseys Schedule
        k_event1_tv = (TextView) getActivity().findViewById(R.id.kelseys_1_task);
        k_event2_tv = (TextView) getActivity().findViewById(R.id.kelseys_2_task);
        k_event3_tv = (TextView) getActivity().findViewById(R.id.kelseys_3_task);
        k_event4_tv = (TextView) getActivity().findViewById(R.id.kelseys_4_task);
        k_event5_tv = (TextView) getActivity().findViewById(R.id.kelseys_5_task);
        k_event6_tv = (TextView) getActivity().findViewById(R.id.kelseys_6_task);
        k_event7_tv = (TextView) getActivity().findViewById(R.id.kelseys_7_task);
        k_event8_tv = (TextView) getActivity().findViewById(R.id.kelseys_8_task);
        k_event9_tv = (TextView) getActivity().findViewById(R.id.kelseys_9_task);
        //Linear Layouts for lights out functionality
        topBar = (LinearLayout) getActivity().findViewById(R.id.top_bar);
        blue_bar_top = (LinearLayout) getActivity().findViewById(R.id.blue_bar_top);
        middle= (RelativeLayout) getActivity().findViewById(R.id.middle_ll);
        r_top= (LinearLayout) getActivity().findViewById(R.id.rtopSidebar_blue_bar);
        driving = (LinearLayout) getActivity().findViewById(R.id.driving_box);
        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distance_dialog();
            }
        });
        r_mid_blue_bar = (LinearLayout) getActivity().findViewById(R.id.rmid_blue_bar);
        history= (LinearLayout) getActivity().findViewById(R.id.tod_in_his);
        rbot_blue_bar= (LinearLayout) getActivity().findViewById(R.id.rbot_blue_bar);
        grocery = (LinearLayout) getActivity().findViewById(R.id.grocery_list);
        left_sidebar = (LinearLayout) getActivity().findViewById(R.id.left_sidebar_linear_layout) ;
        light_switch_sound = MediaPlayer.create(getActivity(), R.raw.swtch);
        //End Variable Declaration *
        Timer timer = new Timer();
        timer.schedule(new Update_UI(), 1000, 300000);
    }

    public static void onLocationChanged(Location location, Context ctx) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        new Todays_Weather_Parser(latitude, longitude, ctx).execute();//Todays
        new Forecast_Weather_Parser(latitude, longitude, ctx).execute();//forecast
        new Headlines_Parser().execute();//Headlines
    }


    private class Update_UI extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Tools.get_lat_long(ctx);
                    new Todays_Weather_Parser(latitude, longitude, ctx).execute();
                    new Forecast_Weather_Parser(latitude, longitude, ctx).execute();//forecast
                    new Headlines_Parser().execute();
                    new Distance_Parser().execute();
                    new Today_In_History_Parser().execute();
                    new QOD_Parser().execute();
                    new Get_Trends().execute();
                    update_calendars(ctx);
                    new Gmail_Call(ctx);
                }
            });
        }
    }

    public void distance_dialog() {
        new Distance_Dialog(ctx);
    }

    public static void update_calendars(Context ctx) {
        final String AUSFCALID = "c0dgk1pj046hv0p5q2ba5ole25r1qo9g@import.calendar.google.com";
        final String APRIMCALID = "amnguyen1@mail.usf.edu";
        final String KUSFCALID = "th6857fot65vrhjf1d7edgdsnr1c1d4s@import.calendar.google.com";
        final String KPRIMCALID = "kelseyk1@mail.usf.edu";
        Event_Lists.reset_Andrews_event_list();
        Event_Lists.reset_Kelseys_event_list();
        new Calendar_Call(ctx, APRIMCALID);
        new Calendar_Call(ctx, AUSFCALID);
        new Calendar_Call(ctx, KPRIMCALID);
        new Calendar_Call(ctx, KUSFCALID);
    }
    public void lights_out(){
        if(lights_status==1) {
            topBar.setVisibility(View.INVISIBLE);
            blue_bar_top.setVisibility(View.INVISIBLE);
            middle.setVisibility(View.INVISIBLE);
            r_top.setVisibility(View.INVISIBLE);
            driving.setVisibility(View.INVISIBLE);
            r_mid_blue_bar.setVisibility(View.INVISIBLE);
            history.setVisibility(View.INVISIBLE);
            rbot_blue_bar.setVisibility(View.INVISIBLE);
            grocery.setVisibility(View.INVISIBLE);
            left_sidebar.setVisibility(View.INVISIBLE);
            light_switch_sound.start();
            lightswitch_iv.setImageResource(R.drawable.light_off);
            lights_status=0;
        }
        else{
            topBar.setVisibility(View.VISIBLE);
            blue_bar_top.setVisibility(View.VISIBLE);
            middle.setVisibility(View.VISIBLE);
            r_top.setVisibility(View.VISIBLE);
            driving.setVisibility(View.VISIBLE);
            r_mid_blue_bar.setVisibility(View.VISIBLE);
            history.setVisibility(View.VISIBLE);
            rbot_blue_bar.setVisibility(View.VISIBLE);
            grocery.setVisibility(View.VISIBLE);
            left_sidebar.setVisibility(View.VISIBLE);
            light_switch_sound.start();
            lightswitch_iv.setImageResource(R.drawable.light_on);
            lights_status=1;
        }
    }


}