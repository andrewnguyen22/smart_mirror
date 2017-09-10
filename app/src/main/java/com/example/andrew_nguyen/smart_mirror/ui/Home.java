package com.example.andrew_nguyen.smart_mirror.ui;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrew_nguyen.smart_mirror.Groceries.Gmail_Call;
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
import com.example.andrew_nguyen.smart_mirror.twitter.Constant_Utils;
import com.example.andrew_nguyen.smart_mirror.twitter.Get_Trends;

import java.util.Calendar;
import java.util.EventListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pub.devrel.easypermissions.EasyPermissions;

public class Home extends AppCompatActivity implements LocationListener, EasyPermissions.PermissionCallbacks {
    final String TAG = "Home";
    private final String AUSFCALID = "c0dgk1pj046hv0p5q2ba5ole25r1qo9g@import.calendar.google.com";
    private final String APRIMCALID = "amnguyen1@mail.usf.edu";
    private final String KUSFCALID = "th6857fot65vrhjf1d7edgdsnr1c1d4s@import.calendar.google.com";
    private final String KPRIMCALID = "kelseyk1@mail.usf.edu";

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
    ImageView lightswitch_iv,
            next_button_iv;

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
    public static TextView quote_of_the_day_tv;
    String quote_of_the_day;

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

    //Latitude and Longitude
    public static double latitude, longitude;
    public static String cityname, statename;
    //Context
    Context ctx;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove the status bar
        setContentView(R.layout.home);
        //Start Variable Declaration *
        ctx = this;
        //Today
        todays_weather_iv = (ImageView) findViewById(R.id.todays_weather_icon);
        todays_weather_descript_tv = (TextView) findViewById(R.id.weather_description);
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
        tweet4_tv = (TextView) findViewById(R.id.tweet_4);
        tweet5_tv = (TextView) findViewById(R.id.tweet_5);
        tweet6_tv = (TextView) findViewById(R.id.tweet_6);
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
        Timer timer = new Timer();
        timer.schedule(new Update_UI(), 1000, 300000);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        new Todays_Weather_Parser(latitude, longitude, ctx).execute();//Todays
        new Forecast_Weather_Parser(latitude, longitude, ctx).execute();//forecast
        new Headlines_Parser().execute();//Headlines
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    private class Update_UI extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
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
                    update_calendars();
                    new Gmail_Call(ctx);
                }
            });
        }
    }

    public void distance_dialog(View view) {
        new Distance_Dialog(ctx);
    }

    private void update_calendars() {
        Event_Lists.reset_Andrews_event_list();
        Event_Lists.reset_Kelseys_event_list();
        new Calendar_Call(ctx, APRIMCALID);
        new Calendar_Call(ctx, AUSFCALID);
        new Calendar_Call(ctx, KPRIMCALID);
        new Calendar_Call(ctx, KUSFCALID);
    }

    //************************************************
    //Below Is Google Boilerplate Code
    //************************************************

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        ((AppCompatActivity) ctx).onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
        Log.e(TAG, "Made it to onRequestPermissionsResult");
    }
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    Log.e(TAG,
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");
                } else {
                    update_calendars();
                    new Gmail_Call(ctx);
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                Log.e(TAG, "REQUEST");
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Log.e(TAG, "ACCOUNT NAME = " + accountName);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        Calendar_Call.mCredential.setSelectedAccountName(accountName);
                        Gmail_Call.mCredential.setSelectedAccountName(accountName);
                        new Gmail_Call(ctx);
                        update_calendars();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    update_calendars();
                    new Gmail_Call(ctx);
                }
                break;
        }
    }
}
