package com.thinkmobiles.easyerp.presentation.managers;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Lynx on 2/24/2017.
 */

@EBean
public class RateAppManager {

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 7;

    @RootContext
    protected Application application;

    @Pref
    protected AppDefaultStatesPreferences_ statesPreferences;

    @AfterInject
    public void init() {
        //skip if don't needed
        if(statesPreferences.dontShowRateDialog().get()) return;

        // Increment launch counter
        long appLaunchesCount = statesPreferences.getAppLaunchesCount().get();
        statesPreferences.edit().getAppLaunchesCount().put(++appLaunchesCount).apply();

        // Get date of first launch
        if(statesPreferences.getFirstLaunchTime().get() == 0)
            statesPreferences.edit().getFirstLaunchTime().put(System.currentTimeMillis()).apply();


        // Wait at least n days before opening
        if (statesPreferences.getAppLaunchesCount().get() >= LAUNCHES_UNTIL_PROMPT
                && (System.currentTimeMillis() >= statesPreferences.getFirstLaunchTime().get() + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) ) {
           //display dialog
            displayRateDialog();
        }
    }

    private void displayRateDialog() {
        new AlertDialog.Builder(application)
                .setTitle("Rate this app")
                .setMessage("If you enjoy using this app, would you mind taking a moment to rate it? It wan't take more than a minute. Thank you for your support!")
                .setPositiveButton("Rate", (dialog, which) -> startRateAppIntent())
                .setNegativeButton("No, thanks", (dialog, which) -> {
                    statesPreferences.edit()
                            .dontShowRateDialog()
                            .put(true)
                            .apply();
                })
                .setNeutralButton("Later", (dialog, which) -> {

                })
                .show();
    }

    private void startRateAppIntent() {
        statesPreferences.edit()
                .dontShowRateDialog()
                .put(true)
                .apply();
        Uri uri = Uri.parse("market://details?id=" + application.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            application.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            application.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + application.getPackageName())));
        }
    }

}
