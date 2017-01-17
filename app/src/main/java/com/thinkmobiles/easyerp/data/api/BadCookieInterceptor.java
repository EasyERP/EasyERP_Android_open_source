package com.thinkmobiles.easyerp.data.api;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.utils.AppSharedPreferences_;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Lynx on 1/17/2017.
 */

public class BadCookieInterceptor implements Interceptor {
    protected AppSharedPreferences_ sharedPreferences;

    public BadCookieInterceptor(AppSharedPreferences_ sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if((originalResponse.code() == 404 && TextUtils.isEmpty(Rest.getInstance().parseError(originalResponse.body()).error))
                || originalResponse.code() == 403) {
            sharedPreferences.edit().geCoockies().put("").apply();
            EasyErpApplication.getInstace().restartApp();
        }
        return originalResponse;
    }
}
