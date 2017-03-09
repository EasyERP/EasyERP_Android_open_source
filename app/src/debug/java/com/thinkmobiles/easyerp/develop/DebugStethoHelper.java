package com.thinkmobiles.easyerp.develop;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class DebugStethoHelper implements IStethoConfigure {
    @Override
    public void init(Application application) {
        Stetho.initializeWithDefaults(application);
    }

    @Override
    public void configureInterceptor(OkHttpClient.Builder httpClient) {
        httpClient.addNetworkInterceptor(new StethoInterceptor());
    }
}
