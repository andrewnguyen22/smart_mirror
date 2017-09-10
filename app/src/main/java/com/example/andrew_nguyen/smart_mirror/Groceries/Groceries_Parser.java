package com.example.andrew_nguyen.smart_mirror.Groceries;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrew_nguyen on 9/10/17.
 */

public class Groceries_Parser {
    public static void parse(String s){
        try {
            List<String> grocery_list = Arrays.asList(s.split(","));
            if(grocery_list.size()>0)
            Update_UI.update(grocery_list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
