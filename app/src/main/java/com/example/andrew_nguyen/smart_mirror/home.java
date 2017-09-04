package com.example.andrew_nguyen.smart_mirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class home extends AppCompatActivity {

    //Today's Weather
    ImageView todays_weather_iv;
    TextView todays_precepitation_tv,
            todays_humidity_tv,
            todays_high_low_tv,
            todays_location_tv,
            todays_temperature_tv,
            todays_sunset_sunrise_tv;
    String todays_precepitation,
            todays_humidity,
            todays_high_low,
            todays_location,
            todays_temperature,
            todays_sunset_sunrise;

    //5 Day Forecast
    ImageView day_1_iv,
            day_2_iv,
            day_3_iv,
            day_4_iv,
            day_5_iv;
    TextView day_1_day_tv,
            day_2_day_tv,
            day_3_day_tv,
            day_4_day_tv,
            day_5_day_tv,
            day_1_temp_tv,
            day_2_temp_tv,
            day_3_temp_tv,
            day_4_temp_tv,
            day_5_temp_tv;
    String day_1_day,
            day_2_day,
            day_3_day,
            day_4_day,
            day_5_day,
            day_1_temp,
            day_2_temp,
            day_3_temp,
            day_4_temp,
            day_5_temp;

    //Headlines
    TextView headline_1_tv,
            headline_2_tv,
            headline_3_tv,
            headline_4_tv;
    String headline_1,
            headline_2,
            headline_3,
            headline_4;

    //Date and Time
    TextView date_tv,
            time_tv;
    String date,
            time;

    //driving
    TextView driving_1_tv,
            driving_2_tv;
    String driving_1,
            driving_2;

    //today in history
    TextView today_in_history_tv;
    String today_in_history;

    //Lightswitch & Button
    ImageView lightswitch_iv,
            next_button_iv;

    //Grocery List
    TextView gitem1_tv,
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
    String gitem1,
            gitem2,
            gitem3,
            gitem4,
            gitem5,
            gitem6,
            gitem7,
            gitem8,
            gitem9,
            gitem10,
            gitem11,
            gitem12,
            gitem13,
            gitem14,
            gitem15,
            gitem16,
            gitem17,
            gitem18,
            gitem19,
            gitem20,
            gitem21,
            gitem22,
            gitem23,
            gitem24;
    //Quote of the day
    TextView quote_of_the_day_tv;
    String quote_of_the_day;

    //Trending Topics
    TextView tweet1_tv,
            tweet2_tv,
            tweet3_tv;
    String tweet1,
            tweet2,
            tweet3;

    //Andrews Schedule
    TextView a_event1_tv,
            a_event2_tv,
            a_event3_tv,
            a_event4_tv,
            a_event5_tv,
            a_event6_tv,
            a_event7_tv,
            a_event8_tv,
            a_event9_tv;
    String a_event1,
            a_event2,
            a_event3,
            a_event4,
            a_event5,
            a_event6,
            a_event7,
            a_event8,
            a_event9;

    //Kelseys Schedule
    TextView k_event1_tv,
            k_event2_tv,
            k_event3_tv,
            k_event4_tv,
            k_event5_tv,
            k_event6_tv,
            k_event7_tv,
            k_event8_tv,
            k_event9_tv;
    String k_event1,
            k_event2,
            k_event3,
            k_event4,
            k_event5,
            k_event6,
            k_event7,
            k_event8,
            k_event9;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove the status bar
        setContentView(R.layout.home);
        //Start Variable Declaration *
        //Today
        todays_weather_iv = (ImageView) findViewById(R.id.todays_weather_icon);
        todays_precepitation_tv = (TextView) findViewById(R.id.precipitation);
        todays_humidity_tv = (TextView) findViewById(R.id.humidity);
        todays_high_low_tv = (TextView) findViewById(R.id.high_low_temp);
        todays_location_tv = (TextView) findViewById(R.id.weather_location);
        todays_temperature_tv = (TextView) findViewById(R.id.todays_temperature);
        todays_sunset_sunrise_tv = (TextView) findViewById(R.id.sunrise_sunset);
        //5 day forecast
        day_1_day_tv = (TextView) findViewById(R.id.day_1_title_tv);
        day_2_day_tv = (TextView) findViewById(R.id.day_2_title_tv);
        day_3_day_tv = (TextView) findViewById(R.id.day_3_title_tv);
        day_4_day_tv = (TextView) findViewById(R.id.day_4_title_tv);
        day_5_day_tv = (TextView) findViewById(R.id.day_5_title_tv);
        day_1_iv = (ImageView) findViewById(R.id.day_1_iv);
        day_2_iv = (ImageView) findViewById(R.id.day_2_iv);
        day_3_iv = (ImageView) findViewById(R.id.day_3_iv);
        day_4_iv = (ImageView) findViewById(R.id.day_4_iv);
        day_5_iv = (ImageView) findViewById(R.id.day_5_iv);
        day_1_temp_tv = (TextView) findViewById(R.id.day_1_temp_tv);
        day_2_temp_tv = (TextView) findViewById(R.id.day_2_temp_tv);
        day_3_temp_tv = (TextView) findViewById(R.id.day_3_temp_tv);
        day_4_temp_tv = (TextView) findViewById(R.id.day_4_temp_tv);
        day_5_temp_tv = (TextView) findViewById(R.id.day_5_temp_tv);
        //Headlines
        headline_1_tv = (TextView) findViewById(R.id.headline_1_tv);
        headline_2_tv = (TextView) findViewById(R.id.headline_2_tv);
        headline_3_tv = (TextView) findViewById(R.id.headline_3_tv);
        headline_4_tv = (TextView) findViewById(R.id.headline_4_tv);
        //Date_Time
        date_tv = (TextView) findViewById(R.id.date_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        //driving today
        driving_1_tv = (TextView) findViewById(R.id.driving_to_location_1);
        driving_2_tv = (TextView) findViewById(R.id.driving_to_location_2);
        //Today in History
        today_in_history_tv = (TextView) findViewById(R.id.today_in_history);
        //Light switch and next button
        lightswitch_iv = (ImageView) findViewById(R.id.lightswitch_iv);
        next_button_iv = (ImageView) findViewById(R.id.next_page_button);
        //Grocery List
        gitem1_tv = (TextView) findViewById(R.id.shopping_item_1);
        gitem2_tv = (TextView) findViewById(R.id.shopping_item_2);
        gitem3_tv = (TextView) findViewById(R.id.shopping_item_3);
        gitem4_tv = (TextView) findViewById(R.id.shopping_item_4);
        gitem5_tv = (TextView) findViewById(R.id.shopping_item_5);
        gitem6_tv = (TextView) findViewById(R.id.shopping_item_6);
        gitem7_tv = (TextView) findViewById(R.id.shopping_item_7);
        gitem8_tv = (TextView) findViewById(R.id.shopping_item_8);
        gitem9_tv = (TextView) findViewById(R.id.shopping_item_9);
        gitem10_tv = (TextView) findViewById(R.id.shopping_item_10);
        gitem11_tv = (TextView) findViewById(R.id.shopping_item_11);
        gitem12_tv = (TextView) findViewById(R.id.shopping_item_12);
        gitem13_tv = (TextView) findViewById(R.id.shopping_item_13);
        gitem14_tv = (TextView) findViewById(R.id.shopping_item_14);
        gitem15_tv = (TextView) findViewById(R.id.shopping_item_15);
        gitem16_tv = (TextView) findViewById(R.id.shopping_item_16);
        gitem17_tv = (TextView) findViewById(R.id.shopping_item_17);
        gitem18_tv = (TextView) findViewById(R.id.shopping_item_18);
        gitem19_tv = (TextView) findViewById(R.id.shopping_item_19);
        gitem20_tv = (TextView) findViewById(R.id.shopping_item_20);
        gitem21_tv = (TextView) findViewById(R.id.shopping_item_21);
        gitem22_tv = (TextView) findViewById(R.id.shopping_item_22);
        gitem23_tv = (TextView) findViewById(R.id.shopping_item_23);
        gitem24_tv = (TextView) findViewById(R.id.shopping_item_24);
        //Quote of the day
        quote_of_the_day_tv = (TextView) findViewById(R.id.quote_of_the_day_tv);
        //trending
        tweet1_tv = (TextView) findViewById(R.id.tweet_1);
        tweet2_tv = (TextView) findViewById(R.id.tweet_2);
        tweet3_tv = (TextView) findViewById(R.id.tweet_3);
        //Andrews Schedule
        a_event1_tv = (TextView) findViewById(R.id.andrews_1_task);
        a_event2_tv = (TextView) findViewById(R.id.andrews_2_task);
        a_event3_tv = (TextView) findViewById(R.id.andrews_3_task);
        a_event4_tv = (TextView) findViewById(R.id.andrews_4_task);
        a_event5_tv = (TextView) findViewById(R.id.andrews_5_task);
        a_event6_tv = (TextView) findViewById(R.id.andrews_6_task);
        a_event7_tv = (TextView) findViewById(R.id.andrews_7_task);
        a_event8_tv = (TextView) findViewById(R.id.andrews_8_task);
        a_event9_tv = (TextView) findViewById(R.id.andrews_9_task);
        //Kelseys Schedule
        k_event1_tv = (TextView) findViewById(R.id.kelseys_1_task);
        k_event2_tv = (TextView) findViewById(R.id.kelseys_2_task);
        k_event3_tv = (TextView) findViewById(R.id.kelseys_3_task);
        k_event4_tv = (TextView) findViewById(R.id.kelseys_4_task);
        k_event5_tv = (TextView) findViewById(R.id.kelseys_5_task);
        k_event6_tv = (TextView) findViewById(R.id.kelseys_6_task);
        k_event7_tv = (TextView) findViewById(R.id.kelseys_7_task);
        k_event8_tv = (TextView) findViewById(R.id.kelseys_8_task);
        k_event9_tv = (TextView) findViewById(R.id.kelseys_9_task);
        //End Variable Declaration *

        
    }
}
