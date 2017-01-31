package com.thinkmobiles.easyerp.data.api.interceptors;

import android.util.Log;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class ReceiveCookieInterceptor implements Interceptor {

    protected CookieManager cookieManager;

    public ReceiveCookieInterceptor(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public ReceiveCookieInterceptor() {
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers(Constants.HEADER_SET_COOKIE).isEmpty() && originalResponse.isSuccessful()) {
            String[] allCookie = originalResponse.headers(Constants.HEADER_SET_COOKIE).get(0).split("; ");
            String cookie = allCookie[0];
            String expired = allCookie[2]; //Expires=Wed, 18 Jan 2017 08:58:07 GMT
            cookieManager.updateCookie(cookie, expired);
            Log.d("myLogs", "Save cookie to SP: cookie = " + cookie + "; expired = " + expired);
        }
        return originalResponse;
    }
}
