package com.example.andrew_nguyen.smart_mirror.google_calendar;

import android.Manifest;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.andrew_nguyen.smart_mirror.groceries.Gmail_Call;
import com.example.andrew_nguyen.smart_mirror.tools.Google_Utils;
import com.example.andrew_nguyen.smart_mirror.ui.Main;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Calendar_Call {
    public static GoogleAccountCredential mCredential;
    Main ctx;
    private static final String[] SCOPES = {CalendarScopes.CALENDAR_READONLY};

    final String AUSFCALID = "c0dgk1pj046hv0p5q2ba5ole25r1qo9g@import.calendar.google.com";



    public Calendar_Call(Main c) {
        ctx = c;
        mCredential = GoogleAccountCredential.usingOAuth2(ctx.getApplicationContext(), Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
        getResultsFromApi(ctx);
    }

    public static int count =0;
    public static void getResultsFromApi(Main ctx) {
        final String AUSFCALID = "c0dgk1pj046hv0p5q2ba5ole25r1qo9g@import.calendar.google.com";
        final String APRIMCALID = "amnguyen1@mail.usf.edu";
        final String KUSFCALID = "th6857fot65vrhjf1d7edgdsnr1c1d4s@import.calendar.google.com";
        final String KPRIMCALID = "kelseyk1@mail.usf.edu";

        if (!Google_Utils.isGooglePlayServicesAvailable(ctx)) {
            Google_Utils.acquireGooglePlayServices(ctx);
        } else if (mCredential.getSelectedAccountName() == null) {
            Log.e("Calendar: ", "Account is currently null");
           ctx.chooseAccount(mCredential);
        } else if (!Google_Utils.isDeviceOnline(ctx)) {
            Log.e("Calendar: ", "No network connection available.");
        } else {
            for(int i =0; i<5;i++) {
                switch (count) {
                    case 0:
                        new MakeRequestTask(mCredential, ctx, AUSFCALID).execute();
                        count++;
                        Log.e("Calendar: ", "Requesting calendar "+ (i+1));
                        break;
                    case 1:
                        new MakeRequestTask(mCredential, ctx, APRIMCALID).execute();
                        count++;
                        Log.e("Calendar: ", "Requesting calendar "+ (i+1));
                        break;
                    case 2:
                        new MakeRequestTask(mCredential, ctx, KUSFCALID).execute();
                        count++;
                        Log.e("Calendar: ", "Requesting calendar "+ (i+1));
                        break;
                    case 3:
                        new MakeRequestTask(mCredential, ctx, KPRIMCALID).execute();
                        count++;
                        Log.e("Calendar: ", "Requesting calendar "+ (i+1));
                        break;
                    case 4:
                        new Gmail_Call(ctx);
                        Gmail_Call.getResultsFromApi();
                        Log.e("Calendar: ", "Requesting gmail "+ i);
                        count = 0;
                        break;
                }
            }
        }
    }

    private static class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.calendar.Calendar mService = null;
        private Exception mLastError = null;
        Main ctx;
        String calendar_id;
        MakeRequestTask(GoogleAccountCredential credential, Main c, String ci) {
            ctx = c;
            calendar_id = ci;
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.calendar.Calendar.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Smart Mirror")
                    .build();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            Log.e("Calendar: ", "Background task initiated...");
            try {
                return getDataFromApi(calendar_id);
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of the next 10 events from the primary calendar.
         */
        private List<String> getDataFromApi(String calendar_id) throws IOException {
            Log.e("Calendar: ", "Getting Data From Api...");
            // List the next 10 events from the primary calendar.
            DateTime now = new DateTime(System.currentTimeMillis());
            List<String> eventStrings = new ArrayList<String>();
            Events events = mService.events().list(calendar_id)
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            List<Event> items = events.getItems();

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    // All-day events don't have start times, so just use
                    // the start date.
                    start = event.getStart().getDate();
                }
                eventStrings.add(
                        String.format("%s (%s)", event.getSummary(), start));
            }
            return eventStrings;
        }

        @Override
        protected void onPreExecute() {
        }

        private final String AUSFCALID ="c0dgk1pj046hv0p5q2ba5ole25r1qo9g@import.calendar.google.com";
        private final String APRIMCALID="amnguyen1@mail.usf.edu";
        private final String KUSFCALID ="th6857fot65vrhjf1d7edgdsnr1c1d4s@import.calendar.google.com";
        private final String KPRIMCALID="kelseyk1@mail.usf.edu";
        private final String TAG_A = "Andrew";
        private final String TAG_K = "Kelsey";

        @Override
        protected void onPostExecute(List<String> output) {
            if (output == null || output.size() == 0) {
                Log.e("Calendar: ", "No results returned.");
            } else {//TODO OUPUT OF CALL IS HERE
                switch (calendar_id){
                    case AUSFCALID:
                        new Calendar_Parser(output, TAG_A);
                        break;
                    case APRIMCALID:
                        new Calendar_Parser(output, TAG_A);
                        break;
                    case KUSFCALID:
                        new Calendar_Parser(output, TAG_K);
                        break;
                    case KPRIMCALID:
                        new Calendar_Parser(output, TAG_K);
                        break;

                }
                Log.e("Calendar: ", String.valueOf(output));
            }
        }
        static final int REQUEST_AUTHORIZATION = 1001;
        @Override
        protected void onCancelled() {
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    Google_Utils.showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode(), ctx);
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    ((AppCompatActivity) ctx).startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            REQUEST_AUTHORIZATION);
                } else {
                    Log.e("Calendar: ", "The following error occurred:\n"
                            + mLastError.getMessage());
                }
            } else {
                Log.e("Calendar: ", "Request cancelled.");
            }
        }
    }

}
