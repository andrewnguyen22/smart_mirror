package com.example.andrew_nguyen.smart_mirror.ui;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
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

import com.example.andrew_nguyen.smart_mirror.groceries.Gmail_Call;
import com.example.andrew_nguyen.smart_mirror.R;
import com.example.andrew_nguyen.smart_mirror.google_calendar.Calendar_Call;
import com.example.andrew_nguyen.smart_mirror.tools.FragmentAdapter;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class Main extends AppCompatActivity implements LocationListener, EasyPermissions.PermissionCallbacks {
    public static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove the status bar
        setContentView(R.layout.main);
        ctx = this;
        //setup viewpager
        setupViewPager();
        //List apps installed on device
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
        for (int i = 0; i < pkgAppsList.size(); i++) {
            System.out.println("List of installed apps: App #" + i + " - " + pkgAppsList.get(i));
        }
        //Disable lockscreen
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();
    }

    //TODO install back button app + all needed apps on android device prior to installing smart mirror
    @Override
    public void onBackPressed() {
        //do nothing
    }

    private void setupViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(Home.newInstance("Home"), "Fragment Created");
        adapter.addFragment(Apps.newInstance("Apps"), "Fragment Created");
        viewPager.setAdapter(adapter);
    }
    public void google_calendar(View view) {
        Apps.openApp(ctx, "com.google.android.calendar");
    }

    public void weather(View view) {
        Apps.openApp(ctx, "com.weather.Weather");
    }

    public void today_in_his(View view) {
        Apps.openApp(ctx, "com.alexandrucene.dayhistory");
    }

    public void usa_today(View view) {
        Apps.openApp(ctx, "com.usatoday.android.news");
    }

    public void time(View view) {
        Apps.openApp(ctx, "com.chineseinspiration");
    }

    public void twitter_trending(View view) {Apps.openApp(ctx, "com.twitter.android");}

    public void spotify(View view) {
        Apps.openApp(ctx, "com.spotify.music");
    }

    public void instagram(View view) {
        Apps.openApp(ctx, "com.instagram.android");
    }

    public void youtube(View view) {
        Apps.openApp(ctx, "com.google.android.youtube");
    }

    public void pinterest(View view) {
        Apps.openApp(ctx, "com.pinterest");
    }

    public void gmail(View view) {
        Apps.openApp(ctx, "com.google.android.gm");
    }

    public void facebook(View view) {
        Apps.openApp(ctx, "com.facebook.lite");
    }

    public void twitter(View view) {
        Apps.openApp(ctx, "com.twitter.android");
    }

    public void timer(View view) {
        Apps.openApp(ctx, "com.sportstracklive.stopwatch");
    }

    public void news(View view){Apps.openApp(ctx, "com.usatoday.android.news");}//TODO open news app on headlines click
    //TODO open twitter to headlines

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
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
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
        onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
        Log.e("Main", "Requesting Permissions via Easy Permissions...");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Main", "RESULT PROCESSED AS" + requestCode);
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
                Log.e("Main", "Requested Account Name Via Account Picker...");
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Log.e("Main", "Account name is " + accountName);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        Calendar_Call.mCredential.setSelectedAccountName(accountName);
                        Home.update_calendars(this);
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    Log.e("Main", "Request authorization: result is okay...");
                    Home.update_calendars(this);
                    new Gmail_Call(this);
                }
                break;
            case REQUEST_PERMISSION_GET_ACCOUNTS: {
                if (resultCode == RESULT_OK) {
                    Log.e("Main", "Get accounts has now been authorized");
                    Home.update_calendars(this);
                    new Gmail_Call(this);
                }
            }
        }

    }

    public static int count = 0;
    public static void chooseAccount(GoogleAccountCredential mCredential) {
        //TODO Google authentication is all sorts of incorrect please fix...
        Log.e("Calendar: ", "Choosing account...");
        if (EasyPermissions.hasPermissions(ctx, Manifest.permission.GET_ACCOUNTS)) {
            Log.e("Calendar: ", "Easy Permissions is available...");
            String accountName = ((Main)ctx).getPreferences(Context.MODE_PRIVATE).getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                Log.e("Calendar: ", "account name is not null...");
                Log.e("calendar", accountName);
                mCredential.setSelectedAccountName(accountName);
                Calendar_Call.getResultsFromApi((Main)ctx);
            } else {
                Log.e("Calendar: ", "Start activity for result called");
                // Start a dialog from which the user can choose an account
                ((Main)ctx).startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            Log.e("Calendar: ", "Easy Permissions does not have permission to run yet...");
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(((AppCompatActivity) ctx), "This app needs to access your Google account (via Contacts).", REQUEST_PERMISSION_GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
        }
    }
}
