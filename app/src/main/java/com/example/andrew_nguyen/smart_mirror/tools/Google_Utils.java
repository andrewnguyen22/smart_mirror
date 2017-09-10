package com.example.andrew_nguyen.smart_mirror.tools;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.Groceries.Gmail_Call;
import com.example.andrew_nguyen.smart_mirror.google_calendar.Calendar_Call;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Google_Utils {
    public static boolean isGooglePlayServicesAvailable(Context c) {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(c);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }
    public static void acquireGooglePlayServices(Context c) {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(c);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode, c);
        }
    }
    public static boolean isDeviceOnline(Context ctx) {
        ConnectivityManager connMgr =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode, Context ctx) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                (AppCompatActivity) ctx,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    private static final String PREF_ACCOUNT_NAME = "accountName";

    public static void chooseAccount(Context ctx, String calendar_id, GoogleAccountCredential mCredential, String TAG) {
        //TODO Google authentication is all sorts of incorrect please fix...
        Log.e("Calendar: ", "Made it to choose account");
        if (EasyPermissions.hasPermissions(ctx, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = ((AppCompatActivity) ctx).getPreferences(Context.MODE_PRIVATE).getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                Log.e("calendar", accountName);
                mCredential.setSelectedAccountName(accountName);
                if(TAG.equals("calendar")) {
                    Calendar_Call.getResultsFromApi(ctx, calendar_id);
                }
                else{
                    Gmail_Call.getResultsFromApi();
                }

            } else {
                // Start a dialog from which the user can choose an account
                ((AppCompatActivity) ctx).startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            Log.e("Calendar: ", "Made it to MAKelse of choose account");
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(((AppCompatActivity) ctx), "This app needs to access your Google account (via Contacts).", REQUEST_PERMISSION_GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
        }
    }
}
