package com.thinkmobiles.easyerp.data.api.interceptors;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class AddCookieInterceptor implements Interceptor {

    protected CookieManager cookieManager;

    public AddCookieInterceptor(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public AddCookieInterceptor() {
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        String cookie = cookieManager.getCookie();
        Request.Builder builder = chain.request().newBuilder();
        if (!TextUtils.isEmpty(cookie))
        builder.addHeader(Constants.HEADER_COOKIE, cookie);
        return chain.proceed(builder.build());
    }
}
