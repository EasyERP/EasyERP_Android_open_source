package com.thinkmobiles.easyerp.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.utils.AppSharedPreferences_;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Lynx on 1/13/2017.
 */

@EApplication
public class EasyErpApplication extends Application {

    @Pref
    protected AppSharedPreferences_ sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        if(!BuildConfig.PRODUCTION) {
            Stetho.initializeWithDefaults(this);
        }
        Rest.getInstance().setPrefManager(sharedPreferences);
    }
}
