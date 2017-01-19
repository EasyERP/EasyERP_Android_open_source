package com.thinkmobiles.easyerp.presentation.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Lynx on 1/13/2017.
 */

@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface CookieSharedPreferences {

    @DefaultString("")
    String getCookies();

    @DefaultString("")
    String getCookieExpireDate();

}
