package com.thinkmobiles.easyerp.develop;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * @author Michael Soyma (Created on 2/23/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface IStethoConfigure {
    void init(final Application application);
    void configureInterceptor(OkHttpClient.Builder httpClient);
}
