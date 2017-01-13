package com.thinkmobiles.easyerp.data.api;

import com.thinkmobiles.easyerp.presentation.utils.AppSharedPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class AddCookieInterceptor implements Interceptor {

    protected AppSharedPreferences_ sharedPreferences;

    public AddCookieInterceptor(AppSharedPreferences_ sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String cookie = sharedPreferences.geCoockies().get();
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(Constants.HEADER_COOKIE, cookie);
        return chain.proceed(builder.build());
    }
}
