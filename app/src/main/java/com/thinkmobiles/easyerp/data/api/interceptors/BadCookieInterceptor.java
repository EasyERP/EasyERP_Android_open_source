package com.thinkmobiles.easyerp.data.api.interceptors;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Lynx on 1/17/2017.
 */

public class BadCookieInterceptor implements Interceptor {

    protected CookieManager cookieManager;

    public BadCookieInterceptor(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public BadCookieInterceptor() {
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if((originalResponse.code() == 404 && TextUtils.isEmpty(Rest.getInstance().parseError(originalResponse.body()).error))
                || originalResponse.code() == 403) {
            cookieManager.clearCookie();
            EasyErpApplication.getInstance().restartApp();
        }
        return originalResponse;
    }
}
