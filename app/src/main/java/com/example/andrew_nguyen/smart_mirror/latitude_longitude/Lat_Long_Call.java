package com.example.andrew_nguyen.smart_mirror.latitude_longitude;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Lat_Long_Call {
    final String API = "&key=AIzaSyBXe10E2rgsJD2J4UOysvu8NyEEnYBORTc";
    final String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    public String make_lat_long_call(String address){
        address = address.replaceAll(" ", "+");
        String full_url = URL + address + API;
        return Http_Service.get_json(full_url);
    }
}
