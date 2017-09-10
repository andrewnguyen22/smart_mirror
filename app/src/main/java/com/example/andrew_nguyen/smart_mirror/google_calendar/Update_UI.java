package com.example.andrew_nguyen.smart_mirror.google_calendar;

import android.os.Handler;
import android.os.Looper;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

import java.util.List;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Update_UI {
    public static void update() {
        Handler uiHandler;
        uiHandler = new Handler(Looper.getMainLooper());
        try {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    List<Event> andrews_list = Event_Lists.getAndrews_event_list();
                    List<Event> kelseys_list = Event_Lists.getKelseys_event_list();
                    if (andrews_list.size() > 9) {
                        if (andrews_list.size() > 0) {
                            for (int i = 0; i < andrews_list.size(); i++) {
                                String temp = andrews_list.get(i).text + " - " + andrews_list.get(i).date_string;
                                switch (i) {
                                    case 0:
                                        Home.a_event1_tv.setText(temp);
                                        break;
                                    case 1:
                                        Home.a_event2_tv.setText(temp);
                                        break;
                                    case 2:
                                        Home.a_event3_tv.setText(temp);
                                        break;
                                    case 3:
                                        Home.a_event4_tv.setText(temp);
                                        break;
                                    case 4:
                                        Home.a_event5_tv.setText(temp);
                                        break;
                                    case 5:
                                        Home.a_event6_tv.setText(temp);
                                        break;
                                    case 6:
                                        Home.a_event7_tv.setText(temp);
                                        break;
                                    case 7:
                                        Home.a_event8_tv.setText(temp);
                                        break;
                                    case 8:
                                        Home.a_event9_tv.setText(temp);
                                        break;
                                }
                            }
                        }
                    }
                    else{//list less than 9
                        int n_empty = 9-andrews_list.size();
                        String temp = " ";
                        for(int i =0; i<n_empty; i++){
                            switch (i) {
                                case 0:
                                    Home.a_event9_tv.setText(temp);
                                    break;
                                case 1:
                                    Home.a_event8_tv.setText(temp);
                                    break;
                                case 2:
                                    Home.a_event7_tv.setText(temp);
                                    break;
                                case 3:
                                    Home.a_event6_tv.setText(temp);
                                    break;
                                case 4:
                                    Home.a_event5_tv.setText(temp);
                                    break;
                                case 5:
                                    Home.a_event4_tv.setText(temp);
                                    break;
                                case 6:
                                    Home.a_event3_tv.setText(temp);
                                    break;
                                case 7:
                                    Home.a_event2_tv.setText(temp);
                                    break;
                                case 8:
                                    Home.a_event1_tv.setText(temp);
                                    break;
                            }
                        }
                    }

                    //Kelseys List


                    if (kelseys_list.size() > 9) {
                        if (kelseys_list.size() > 0) {
                            for (int i = 0; i < kelseys_list.size(); i++) {
                                String temp = kelseys_list.get(i).text + " - " + kelseys_list.get(i).date_string;
                                switch (i) {
                                    case 0:
                                        Home.k_event1_tv.setText(temp);
                                        break;
                                    case 1:
                                        Home.k_event2_tv.setText(temp);
                                        break;
                                    case 2:
                                        Home.k_event3_tv.setText(temp);
                                        break;
                                    case 3:
                                        Home.k_event4_tv.setText(temp);
                                        break;
                                    case 4:
                                        Home.k_event5_tv.setText(temp);
                                        break;
                                    case 5:
                                        Home.k_event6_tv.setText(temp);
                                        break;
                                    case 6:
                                        Home.k_event7_tv.setText(temp);
                                        break;
                                    case 7:
                                        Home.k_event8_tv.setText(temp);
                                        break;
                                    case 8:
                                        Home.k_event9_tv.setText(temp);
                                        break;
                                }
                            }
                        }
                    }
                    else{//list less than 9
                        int n_empty = 9-kelseys_list.size();
                        String temp = " ";
                        for(int i =0; i<n_empty; i++){
                            switch (i) {
                                case 0:
                                    Home.k_event1_tv.setText(temp);
                                    break;
                                case 1:
                                    Home.k_event2_tv.setText(temp);
                                    break;
                                case 2:
                                    Home.k_event3_tv.setText(temp);
                                    break;
                                case 3:
                                    Home.k_event4_tv.setText(temp);
                                    break;
                                case 4:
                                    Home.k_event5_tv.setText(temp);
                                    break;
                                case 5:
                                    Home.k_event6_tv.setText(temp);
                                    break;
                                case 6:
                                    Home.k_event7_tv.setText(temp);
                                    break;
                                case 7:
                                    Home.k_event8_tv.setText(temp);
                                    break;
                                case 8:
                                    Home.k_event9_tv.setText(temp);
                                    break;
                            }
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
