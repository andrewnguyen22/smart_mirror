package com.example.andrew_nguyen.smart_mirror.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.andrew_nguyen.smart_mirror.ui.Splash;

/**
 * Created by andrew_nguyen on 9/14/17.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, Splash.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
