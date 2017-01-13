package com.thinkmobiles.easyerp.data.api;

import com.thinkmobiles.easyerp.presentation.utils.AppSharedPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class ReceiveCookieInterceptor implements Interceptor {

    protected AppSharedPreferences_ sharedPreferences;

    public ReceiveCookieInterceptor(AppSharedPreferences_ sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers(Constants.HEADER_SET_COOKIE).isEmpty()) {
            String[] allCookie = originalResponse.headers(Constants.HEADER_SET_COOKIE).get(0).split("; ");
            String cookie = allCookie[0];
            sharedPreferences.edit()
                    .geCoockies()
                    .put(cookie)
                    .apply();
        }
        return originalResponse;
    }
}
