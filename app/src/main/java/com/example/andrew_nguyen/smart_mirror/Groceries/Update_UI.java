package com.example.andrew_nguyen.smart_mirror.Groceries;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew_nguyen on 9/10/17.
 */

public class Update_UI {
    public static void update(final List<String> grocery_list) {
        Handler uiHandler;
        final List<TextView > textView_list = new ArrayList<TextView>();
        populate_tvList(textView_list);
        uiHandler = new Handler(Looper.getMainLooper());
        try {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<grocery_list.size(); i++){
                        textView_list.get(i).setText(grocery_list.get(i));
                    }
                    for(int i=23; i>=grocery_list.size(); i--){
                        textView_list.get(i).setText(" ");
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void populate_tvList(List<TextView> textView_list){
        textView_list.add(Home.gitem1_tv);
        textView_list.add(Home.gitem2_tv);
        textView_list.add(Home.gitem3_tv);
        textView_list.add(Home.gitem4_tv);
        textView_list.add(Home.gitem5_tv);
        textView_list.add(Home.gitem6_tv);
        textView_list.add(Home.gitem7_tv);
        textView_list.add(Home.gitem8_tv);
        textView_list.add(Home.gitem9_tv);
        textView_list.add(Home.gitem10_tv);
        textView_list.add(Home.gitem11_tv);
        textView_list.add(Home.gitem12_tv);
        textView_list.add(Home.gitem13_tv);
        textView_list.add(Home.gitem14_tv);
        textView_list.add(Home.gitem15_tv);
        textView_list.add(Home.gitem16_tv);
        textView_list.add(Home.gitem17_tv);
        textView_list.add(Home.gitem18_tv);
        textView_list.add(Home.gitem19_tv);
        textView_list.add(Home.gitem20_tv);
        textView_list.add(Home.gitem21_tv);
        textView_list.add(Home.gitem22_tv);
        textView_list.add(Home.gitem23_tv);
        textView_list.add(Home.gitem24_tv);
    }
}
