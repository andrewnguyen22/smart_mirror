package com.example.andrew_nguyen.smart_mirror.today_in_history;

import com.example.andrew_nguyen.smart_mirror.tools.Http_Service;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Today_In_History_Call {
    final String URL = "http://history.muffinlabs.com/date";
    public String make_history_call(){
        return Http_Service.get_json(URL);
    }
}
