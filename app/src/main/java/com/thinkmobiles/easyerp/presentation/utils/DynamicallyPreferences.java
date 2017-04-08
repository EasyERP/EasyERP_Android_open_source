package com.thinkmobiles.easyerp.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class DynamicallyPreferences implements DynamicallyPreferencesTemplate {

    private static final String PREFS_NAME = "ERPDynamicallyPrefs";

    @RootContext
    protected Context context;
    private SharedPreferences sharedPreferences;

    @AfterInject
    protected void initPreferences() {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public int getInt(final String key) {
        return getInt(key, 0);
    }

    @Override
    public int getInt(final String key, final int defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getInt(key, defaultValue);
        else return defaultValue;
    }

    @Override
    public void putInt(String key, int value) {
        if (sharedPreferences != null)
            sharedPreferences.edit()
                    .putInt(key, value)
                    .apply();
    }

    @Override
    public String getString(final String key) {
        return getString(key, null);
    }

    @Override
    public String getString(final String key, final String defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getString(key, defaultValue);
        else return defaultValue;
    }

    @Override
    public void putString(String key, String value) {
        if (sharedPreferences != null)
            sharedPreferences.edit()
                    .putString(key, value)
                    .apply();
    }

    @Override
    public long getLong(final String key) {
        return getLong(key, 0);
    }

    @Override
    public long getLong(final String key, final long defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getLong(key, defaultValue);
        else return defaultValue;
    }

    @Override
    public void putLong(String key, long value) {
        if (sharedPreferences != null)
            sharedPreferences.edit()
                    .putLong(key, value)
                    .apply();
    }

    @Override
    public boolean getBoolean(final String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(final String key, final boolean defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getBoolean(key, defaultValue);
        else return defaultValue;
    }

    @Override
    public void putBoolean(String key, boolean value) {
        if (sharedPreferences != null)
            sharedPreferences.edit()
                    .putBoolean(key, value)
                    .apply();
    }
}
