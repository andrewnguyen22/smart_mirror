package com.example.andrew_nguyen.smart_mirror.ui;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.andrew_nguyen.smart_mirror.Groceries.Gmail_Call;
import com.example.andrew_nguyen.smart_mirror.R;
import com.example.andrew_nguyen.smart_mirror.google_calendar.Calendar_Call;
import com.example.andrew_nguyen.smart_mirror.tools.FragmentAdapter;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class Main extends AppCompatActivity implements LocationListener, EasyPermissions.PermissionCallbacks {
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove the status bar
        setContentView(R.layout.main);
        ctx = this;
        //setup viewpager
        setupViewPager();
    }
    //TODO install back button app + all needed apps on android device prior to installing smart mirror
    //TODO make app the launcher, disable home button etc.
    @Override
    public void onBackPressed(){
        //do nothing
    }
    private void setupViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(Home.newInstance("Home"), "Fragment Created");
        adapter.addFragment(Apps.newInstance("Apps"), "Fragment Created");
        viewPager.setAdapter(adapter);
    }

    public void spotify(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void instagram(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void youtube(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void pinterest(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void gmail(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void facebook(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void twitter(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    public void timer(View view) {
        Apps.openApp(ctx, "com.google.android.apps.maps");
    }

    @Override
    public void onLocationChanged(Location location) {
        Home.onLocationChanged(location, this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    //************************************************
    //Below Is Google Boilerplate Code
    //************************************************

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        ((AppCompatActivity) this).onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
        Log.e("Main", "Made it to onRequestPermissionsResult");
    }

    @Override
    public void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    Log.e("Main",
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");
                } else {
                    Home.update_calendars(this);
                    new Gmail_Call(this);
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                Log.e("Main", "REQUEST");
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Log.e("Main", "ACCOUNT NAME = " + accountName);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        Calendar_Call.mCredential.setSelectedAccountName(accountName);
                        Gmail_Call.mCredential.setSelectedAccountName(accountName);
                        new Gmail_Call(this);
                        Home.update_calendars(this);
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    Home.update_calendars(this);
                    new Gmail_Call(this);
                }
                break;
        }
    }
}
