package com.example.andrew_nguyen.smart_mirror.google_calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Event_Lists {
    public static List<Event> andrews_event_list = new ArrayList<Event>(), kelseys_event_list = new ArrayList<Event>();

    public static List<Event> getAndrews_event_list() {
        return andrews_event_list;
    }

    public static void add_to_Andrews_event_list(Event e) {
        andrews_event_list.add(e);
    }

    public static void reset_Andrews_event_list() {
        andrews_event_list.clear();
    }

    public static void order_Andrews_list() {
        try {
            Collections.sort(andrews_event_list, new CustomComparator());
            Update_UI.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAndrews_event_list(List<Event> andrews_event_list) {
        Event_Lists.andrews_event_list = andrews_event_list;
    }

    public static List<Event> getKelseys_event_list() {
        return kelseys_event_list;
    }

    public static void reset_Kelseys_event_list() {
        kelseys_event_list.clear();
    }

    public static void add_to_Kelseys_event_list(Event e) {
        kelseys_event_list.add(e);
    }

    public static void setKelseys_event_list(List<Event> kelseys_event_list) {
        Event_Lists.kelseys_event_list = kelseys_event_list;
    }

    public static void order_Kelseys_list() {
        try {
            Collections.sort(kelseys_event_list, new CustomComparator());
            Update_UI.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CustomComparator implements Comparator<Event> {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.date.compareTo(o2.date);
        }
    }
}
