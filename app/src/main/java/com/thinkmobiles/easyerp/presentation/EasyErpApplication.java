package com.thinkmobiles.easyerp.presentation;

import android.app.Application;
import android.content.Intent;

import com.facebook.stetho.Stetho;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.utils.CookieSharedPreferences_;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Lynx on 1/13/2017.
 */

@EApplication
public class EasyErpApplication extends Application {

    private static EasyErpApplication INSTANCE;

    @Pref
    protected CookieSharedPreferences_ cookieSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        if(!BuildConfig.PRODUCTION) {
            Stetho.initializeWithDefaults(this);
        }
        Rest.getInstance().setPrefManager(cookieSharedPreferences);
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
