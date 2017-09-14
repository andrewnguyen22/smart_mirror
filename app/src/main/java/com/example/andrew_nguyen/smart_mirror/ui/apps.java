package com.example.andrew_nguyen.smart_mirror.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.andrew_nguyen.smart_mirror.R;

public class Apps extends AppCompatActivity {
    Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove the status bar
        ctx= this;

    }
    public void spotify(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void instagram(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void youtube(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void pinterest(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void gmail(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void facebook(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void twitter(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }
    public void timer(View view){
        openApp(ctx, "com.google.android.apps.maps");
    }

    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            return false;
            //throw new PackageManager.NameNotFoundException();
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }
}
