package com.example.andrew_nguyen.smart_mirror.quote_of_the_day;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/9/17.
 */

public class QOD_Call {
    final String url = "https://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=jsonp&jsonp=?";
    public String make_call(){
        return Http_Service.get_json(url);
    }
}
