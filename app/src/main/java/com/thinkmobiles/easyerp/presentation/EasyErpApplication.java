package com.thinkmobiles.easyerp.presentation;

import android.app.Application;
import android.content.Intent;

import com.facebook.stetho.Stetho;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

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
        if(!BuildConfig.PRODUCTION) {
            Stetho.initializeWithDefaults(this);
        }
        Rest.getInstance().setCookieManager(cookieManager);
    }

    public static EasyErpApplication getInstance() {
        return INSTANCE;
    }

    public void restartApp() {
        Intent startIntent = getPackageManager()
                .getLaunchIntentForPackage(getPackageName())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
    }
}
