package com.thinkmobiles.easyerp.presentation.utils;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface DynamicallyPreferencesTemplate {
    int getInt(final String key);
    int getInt(final String key, final int defaultValue);
    void putInt(final String key, final int value);
    String getString(final String key);
    String getString(final String key, final String defaultValue);
    void putString(final String key, final String value);
    long getLong(final String key);
    long getLong(final String key, final long defaultValue);
    void putLong(final String key, final long value);
    boolean getBoolean(final String key);
    boolean getBoolean(final String key, final boolean defaultValue);
    void putBoolean(final String key, final boolean value);
}
