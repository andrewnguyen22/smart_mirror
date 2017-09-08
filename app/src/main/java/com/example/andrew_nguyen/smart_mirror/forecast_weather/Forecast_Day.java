package com.example.andrew_nguyen.smart_mirror.forecast_weather;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.andrew_nguyen.smart_mirror.tools.Icon_Translator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by andrew_nguyen on 9/6/17.
 */

public class Forecast_Day {
    String day_of_the_week;
    String temperature;
    Drawable icon;

    public Forecast_Day(String unix_utc, String temp, String icn, String des, Context ctx){
        SimpleDateFormat time_format = new SimpleDateFormat("EE");
        time_format.setTimeZone(TimeZone.getDefault());
        long unix = Long.parseLong(unix_utc);
        Date d = new Date(unix * 1000);
        day_of_the_week =time_format.format(d).toString();
        System.out.println("day of the week " + day_of_the_week);
        temperature = temp;
        icon = new Icon_Translator().translate(ctx, icn, des);
    }
}
