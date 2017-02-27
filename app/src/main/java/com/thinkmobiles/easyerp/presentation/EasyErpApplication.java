package com.thinkmobiles.easyerp.presentation;

import android.app.Application;
import android.content.Intent;

import com.crashlytics.android.Crashlytics;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginActivity_;
import com.thinkmobiles.easyerp.presentation.utils.AppDefaultStatesPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.uxcam.UXCam;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Lynx on 1/13/2017.
 */

@EApplication
public class EasyErpApplication extends Application {

    private static EasyErpApplication INSTANCE;

    @Pref
    protected AppDefaultStatesPreferences_ statesPreferences;

    @Bean
    protected CookieManager cookieManager;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Rest.getInstance().setCookieManager(cookieManager);

        GoogleAnalyticHelper.init(this);

        BuildConfig.STETHO.init(this);
        if(BuildConfig.PRODUCTION)
            Fabric.with(this, new Crashlytics());

        UXCam.startWithKey(Constants.UXCAM_ACCOUNT_KEY);
    }

    public static EasyErpApplication getInstance() {
        return INSTANCE;
    }

    public void restartApp() {
        LoginActivity_.intent(this)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .start();
    }

}
