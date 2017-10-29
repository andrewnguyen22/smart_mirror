package com.example.andrew_nguyen.smart_mirror.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Tools {
    public static String no_decimal_string(String s) {
        double convert = Double.parseDouble(s);
        int _convert = (int) Math.round(convert);
        return Integer.toString(_convert);
    }
    public static void get_lat_long(Context ctx) {
        LocationManager locationManager;
        Criteria criteria;
        double netlistentime = 0 * 60 * 1000; // minutes * 60 sec/min * 1000 for milliseconds
        double netlistendistance = 0 * 1609.344; // miles * conversion to meters
        double gpslistentime = 30 * 60 * 1000; // minutes * 60 sec/min * 1000 for milliseconds
        double gpslistendistance = 0 * 1609.344; // miles * conversion to meters
        String bestprovider;
        boolean isNetEnabled;
        boolean isGpsEnabled;

        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        // setup bestProvider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        bestprovider = locationManager.getBestProvider(criteria, true);
        // get an initial current location
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location currentlocation = locationManager.getLastKnownLocation(bestprovider);
        if (currentlocation != null) {
            Home.latitude = currentlocation.getLatitude();
            Home.longitude = currentlocation.getLongitude();
        } else {
            System.out.println("Last known location is null!!");
        }
        //check for network vs gps
        isNetEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isNetEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long) netlistentime, (float) netlistendistance, (LocationListener) ctx);
        }
        // ideally should check for !isProviderEnabled first, then provide option for turning it on
        if (isGpsEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) gpslistentime, (float) gpslistendistance, (LocationListener) ctx);
        }
    }
    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
    public static int countWords(String s){

        int wordCount = 0;

        boolean word = false;
        int endOfLine = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }
    public static String remove_special(String str){
        return str.replaceAll("[^ -~]", "");
    }
}

