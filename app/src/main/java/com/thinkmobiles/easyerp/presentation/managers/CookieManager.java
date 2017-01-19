package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.presentation.utils.CookieSharedPreferences_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Lynx on 1/19/2017.
 */


@EBean
public class CookieManager {

    @Pref
    protected CookieSharedPreferences_ cookieSharedPreferences;

    public boolean isCookieExpired() {
        if(!isCookieExists() && !TextUtils.isEmpty(cookieSharedPreferences.getCookieExpireDate().get())) {
            return DateManager.isCookieExpired(cookieSharedPreferences.getCookieExpireDate().get());
        } else
            return false;
    }

    public boolean isCookieExists() {
        return !TextUtils.isEmpty(cookieSharedPreferences.getCookies().get());
    }

    public void clearCookie() {
        if(isCookieExists()) {
            cookieSharedPreferences.clear();
        }
    }

    public void updateCookie(String cookie) {
        if(!TextUtils.isEmpty(cookie)) {
            cookieSharedPreferences.edit().getCookies().put(cookie).apply();
        }
    }

    public void updateCookie(String cookie, String expiredDate) {
        updateCookie(cookie);
        if(!TextUtils.isEmpty(expiredDate)) {
            cookieSharedPreferences.edit().getCookieExpireDate().put(expiredDate).apply();
        }
    }

    public String getCookie() {
        if(isCookieExists()) {
            return cookieSharedPreferences.getCookies().get();
        } else
            return "";
    }
}
