package com.thinkmobiles.easyerp.presentation;

import android.app.Application;
import android.content.Intent;

import com.crashlytics.android.Crashlytics;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginActivity_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Lynx on 1/13/2017.
 */

@EApplication
public class EasyErpApplication extends Application {

    private static EasyErpApplication INSTANCE;

    @Bean
    protected CookieManager cookieManager;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Rest.getInstance().setCookieManager(cookieManager);

        BuildConfig.STETHO.init(this);
        if(BuildConfig.PRODUCTION)
            Fabric.with(this, new Crashlytics());
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
