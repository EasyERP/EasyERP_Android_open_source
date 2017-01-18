package com.thinkmobiles.easyerp.data.api.interceptors;

import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.CookieSharedPreferences_;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class AddCookieInterceptor implements Interceptor {

    protected CookieSharedPreferences_ cookieSharedPreferences;

    public AddCookieInterceptor(CookieSharedPreferences_ cookieSharedPreferences) {
        this.cookieSharedPreferences = cookieSharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String cookie = cookieSharedPreferences.geCoockies().get();
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(Constants.HEADER_COOKIE, cookie);
        return chain.proceed(builder.build());
    }
}
