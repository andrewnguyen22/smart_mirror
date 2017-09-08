package com.example.andrew_nguyen.smart_mirror.tools;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Http_Service {
    public static String get_json(String url_str) {
        String response = "";
        System.out.println("URL STRING + " + url_str);
        try {
            URL url = new URL(url_str);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            if (response != null && !response.isEmpty()) {

            }
            bufferedReader.close();
            IS.close();
            httpURLConnection.disconnect();
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
