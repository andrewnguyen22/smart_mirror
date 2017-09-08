package com.example.andrew_nguyen.smart_mirror.today_in_history;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Event {
    private  String year;
    private String text;
    public Event(String y, String t){
        year = y;
        text= t;
    }

    public String getYear() {
        return year;
    }
    public String getText() {
        return text;
    }
}
