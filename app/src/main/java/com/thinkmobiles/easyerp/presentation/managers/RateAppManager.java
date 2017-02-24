package com.thinkmobiles.easyerp.presentation.managers;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;

import static com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app.AboutAppPresenter.LINK_MARKET;
import static com.thinkmobiles.easyerp.presentation.screens.about.tabs.about_app.AboutAppPresenter.SCHEME_MARKET;

/**
 * Created by Lynx on 2/24/2017.
 */

@EBean
public class RateAppManager {

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 7;

    @RootContext
    protected Activity activity;

    @Pref
    protected AppDefaultStatesPreferences_ statesPreferences;

    @AfterInject
    public void init() {
        //skip if don't needed
        if (statesPreferences.dontShowRateDialog().get()) return;

        // Increment launch counter
        long appLaunchesCount = statesPreferences.getAppLaunchesCount().get();
        statesPreferences.edit().getAppLaunchesCount().put(++appLaunchesCount).apply();

        // Get date of first launch
        if (statesPreferences.getFirstLaunchTime().get() == 0)
            statesPreferences.edit().getFirstLaunchTime().put(System.currentTimeMillis()).apply();


        // Wait at least n days before opening
        if (statesPreferences.getAppLaunchesCount().get() >= LAUNCHES_UNTIL_PROMPT
                && (System.currentTimeMillis() >= statesPreferences.getFirstLaunchTime().get() + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000))) {
            //display dialog
            displayRateDialog();
        }
    }

    private void displayRateDialog() {
        new AlertDialog.Builder(activity, R.style.DefaultTheme_NoTitleDialogWithAnimation)
                .setTitle(R.string.title_rate_app)
                .setMessage(R.string.msg_rate_app)
                .setPositiveButton(R.string.btn_rate, (dialog, which) -> startRateAppIntent())
                .setNegativeButton(R.string.btn_no_thanks, (dialog, which) -> {
                    statesPreferences.edit()
                            .dontShowRateDialog()
                            .put(true)
                            .apply();
                })
                .setNeutralButton(R.string.btn_later, (dialog, which) -> {

                })
                .show();
    }

    private void startRateAppIntent() {
        statesPreferences.edit()
                .dontShowRateDialog()
                .put(true)
                .apply();
        Uri uri = Uri.parse(String.format(SCHEME_MARKET, BuildConfig.APPLICATION_ID));
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(String.format(LINK_MARKET, BuildConfig.APPLICATION_ID))));
        }
    }

}
