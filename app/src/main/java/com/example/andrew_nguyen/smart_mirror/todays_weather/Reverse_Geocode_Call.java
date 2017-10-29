package com.example.andrew_nguyen.smart_mirror.todays_weather;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Reverse_Geocode_Call {
    final String URL = "http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&featureTypes=&location=";
    public String reverse_geo_call(String lat_ln){
        String full_url = URL + lat_ln;
        return Http_Service.get_json(full_url);
    }
}
