package com.example.andrew_nguyen.smart_mirror.driving_distance;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Distance_Call {
    final String API = "&key=AIzaSyAKTw5apR2qGEShm5XrCXGIGoXJBhcrt3I";
    final String APPEND_DES = "&destinations=";
    final String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";
    public String make_driving_call(String latitude, String longitude, String destination1, String destination2){
        String full_url = URL + latitude +","+ longitude + APPEND_DES + destination1 + "|"+destination2 +API;
        full_url = full_url.replaceAll(" ", "%20");
        return Http_Service.get_json(full_url);
    }
}
