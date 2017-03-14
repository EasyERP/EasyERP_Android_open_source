package com.thinkmobiles.easyerp.presentation.managers;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

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
    protected Activity mActivity;

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
        new AlertDialog.Builder(mActivity, R.style.DefaultTheme_NoTitleDialogWithAnimation)
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
                    statesPreferences.getAppLaunchesCount().remove();
                    statesPreferences.edit().getFirstLaunchTime().put(System.currentTimeMillis()).apply();
                })
                .show();
    }

    private void startRateAppIntent() {
        statesPreferences.edit()
                .dontShowRateDialog()
                .put(true)
                .apply();
        IntentActionHelper.callViewIntent(mActivity,
                String.format(IntentActionHelper.FORMAT_MARKET, BuildConfig.APPLICATION_ID),
                String.format(IntentActionHelper.FORMAT_LINK_MARKET, BuildConfig.APPLICATION_ID));
    }
}
