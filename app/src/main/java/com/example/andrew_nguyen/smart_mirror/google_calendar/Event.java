package com.example.andrew_nguyen.smart_mirror.google_calendar;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Event {
    Date date;
    String text, date_string;
    public Event(String s) {
        final SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat date_and_time_format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        final SimpleDateFormat date_string_format_time = new SimpleDateFormat("EE, hh:mm a - MMM dd");
        final SimpleDateFormat date_string_format_date = new SimpleDateFormat("EE, MMM dd");
        String date, time, date_and_time;
        String[] separated2, separated3, separated4, separated;
        try {
            separated = s.split("\\(");
            text = separated[0];
            if(text.startsWith("[")){
                text= text.substring(1);
            }
            text = text.replaceAll("\\[.*?\\]", "");
            separated2 = separated[1].split("\\)");//Separated into
            if (separated2[0].contains("T")) {//Has Time
                separated3 = separated2[0].split("(?=\\p{Upper})");
                date = separated3[0];
                time = separated3[1];
                separated4 = time.split("\\.");
                time = separated4[0].substring(1);
                System.out.println("Time " + time);
                System.out.println("Date " + date);
                date_and_time = date + " " + time;
                this.date = date_and_time_format.parse(date_and_time);
                date_string = date_string_format_time.format(this.date);
            } else {//No time string
                date = separated2[0];
                System.out.println("Date " + date);
                this.date = date_format.parse(date);
                date_string = date_string_format_date.format(this.date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
