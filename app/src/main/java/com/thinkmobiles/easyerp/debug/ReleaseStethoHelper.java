package com.thinkmobiles.easyerp.debug;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ReleaseStethoHelper implements IStethoConfigure {
    @Override
    public void init(Application application) {
        // Nothing
    }

    @Override
    public void configureInterceptor(OkHttpClient.Builder httpClient) {
        // Nothing
    }
}
