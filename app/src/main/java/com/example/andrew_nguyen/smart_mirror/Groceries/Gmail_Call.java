package com.example.andrew_nguyen.smart_mirror.groceries;

import com.example.andrew_nguyen.smart_mirror.tools.Google_Utils;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;

import com.google.api.services.gmail.GmailScopes;

import com.google.api.services.gmail.model.*;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gmail_Call {
    public static GoogleAccountCredential mCredential;
    static final int REQUEST_AUTHORIZATION = 1001;
    private static final String[] SCOPES = {GmailScopes.GMAIL_READONLY};
    static Context ctx;

    public Gmail_Call(Context ctx) {
        this.ctx = ctx;
        mCredential = GoogleAccountCredential.usingOAuth2(ctx.getApplicationContext(), Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());getResultsFromApi();
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    public static void getResultsFromApi() {
        if (!Google_Utils.isGooglePlayServicesAvailable(ctx)) {
            Google_Utils.acquireGooglePlayServices(ctx);
        } else if (mCredential.getSelectedAccountName() == null) {
            Google_Utils.chooseAccount(ctx, "", mCredential, "gmail");
        } else if (!Google_Utils.isDeviceOnline(ctx)) {
            Log.e("GMAIL CALL: ", "No network connection available.");
        } else {
            new Gmail_Request_Task(mCredential).execute();
        }
    }

    /**
     * An asynchronous task that handles the Gmail API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private static class Gmail_Request_Task extends AsyncTask<Void, Void, String> {
        private com.google.api.services.gmail.Gmail mService = null;
        private Exception mLastError = null;

        Gmail_Request_Task(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.gmail.Gmail.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Smart Mirror")
                    .build();
        }

        /**
         * Background task to call Gmail API.
         *
         * @param params no parameters needed for this task.
         */
        @Override
        protected String doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of Gmail labels attached to the specified account.
         *
         * @return List of Strings labels.
         * @throws IOException
         */
        private String getDataFromApi() throws IOException {
            // Get the body of the lates email
            final String USER = "me";
            ListMessagesResponse response = mService.users().messages().list(USER).setMaxResults(10l).execute();
            List<Message> messages = new ArrayList<Message>();
            while (response.getMessages() != null) {
                messages.addAll(response.getMessages());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = mService.users().messages().list(USER).setMaxResults(10l).setPageToken(pageToken).execute();
                } else {
                    break;
                }
            }
            for (Message message : messages) {
                System.out.println(message.toPrettyString());
            }
            Message message = mService.users().messages().get(USER, messages.get(0).getId()).setFormat("FULL").execute();
            return message.getSnippet();
            //TODO allow smart mirror app to run for a few days on a real device and make sure it is functioning properly
        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String output) {
            if (TextUtils.isEmpty(output)) {
                Groceries_Parser.parse(" ");
            } else {
                Log.e("GMAIL CALL: ", output);
                Groceries_Parser.parse(output);
            }
        }

        @Override
        protected void onCancelled() {
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    Google_Utils.showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode(), ctx);
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    ((AppCompatActivity) ctx).startActivityForResult(//TODO may be the issue
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            REQUEST_AUTHORIZATION);
                } else {
                    Log.e("GMAIL CALL: ", "The following error occurred:\n"
                            + mLastError.getMessage());
                }
            } else {
                Log.e("GMAIL CALL", "Request Canceled");
            }
        }
    }
}