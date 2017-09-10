package com.example.andrew_nguyen.smart_mirror.headlines;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/7/17.
 */

public class Headlines_Call {
    final String API_Key = "c82cea2306aa49a5b828752c67d6c7ea";
    final String URL = "https://newsapi.org/v1/articles?source=usa-today&sortBy=top&apiKey=c82cea2306aa49a5b828752c67d6c7ea";

    public String makeHeadlinesCall() {
        return Http_Service.get_json(URL);
    }
}
