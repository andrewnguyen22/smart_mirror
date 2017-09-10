package com.example.andrew_nguyen.smart_mirror.google_calendar;

import java.util.List;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Calendar_Parser {
    private final String TAG_A = "Andrew";
    private final String TAG_K = "Kelsey";
    public Calendar_Parser(List<String> string_list, String TAG){
        if(TAG.equals(TAG_A)) {
            for (int i = 0; i < string_list.size(); i++) {
                Event e = new Event(string_list.get(i));
                Event_Lists.add_to_Andrews_event_list(e);
            }
            Event_Lists.order_Andrews_list();
        }
        else{
            for (int i = 0; i < string_list.size(); i++) {
                Event e = new Event(string_list.get(i));
                Event_Lists.add_to_Kelseys_event_list(e);
            }
            Event_Lists.order_Kelseys_list();;
        }

    }
//TODO turn list of strings into list of 'Event's class
    //TODO send to Event Lists
}
