package com.example.andrew_nguyen.smart_mirror.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent I = new Intent(this, Runtime_Permissions.class);
        startActivity(I);
        finish();
    }
}
