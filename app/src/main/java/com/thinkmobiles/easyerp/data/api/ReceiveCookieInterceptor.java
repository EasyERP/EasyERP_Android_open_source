package com.thinkmobiles.easyerp.data.api;

import android.util.Log;

import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.CookieSharedPreferences_;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Lynx on 1/13/2017.
 */

public class ReceiveCookieInterceptor implements Interceptor {

    protected CookieSharedPreferences_ cookieSharedPreferences;

    public ReceiveCookieInterceptor(CookieSharedPreferences_ cookieSharedPreferences) {
        this.cookieSharedPreferences = cookieSharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers(Constants.HEADER_SET_COOKIE).isEmpty()) {
            String[] allCookie = originalResponse.headers(Constants.HEADER_SET_COOKIE).get(0).split("; ");
            String cookie = allCookie[0];
            String expired = allCookie[2]; //Expires=Wed, 18 Jan 2017 08:58:07 GMT
            cookieSharedPreferences.edit()
                    .geCoockies()
                    .put(cookie)
                    .apply();
            cookieSharedPreferences.edit()
                    .getCookieExpireDate()
                    .put(expired)
                    .apply();
            Log.d("myLogs", "Save cookie to SP: cookie = " + cookie + "; expired = " + expired);
        }
        return originalResponse;
    }
}
