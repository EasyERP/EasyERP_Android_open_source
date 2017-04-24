package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lynx on 1/19/2017.
 */

public abstract class ValidationManager {

    private static final Pattern invalidCharsRegExp = Pattern.compile("^[a-zA-Z0-9_@]+$");
    private static final Pattern passRegExp = Pattern.compile("^[\\w]{3,100}$");

    public static Constants.ErrorCodes isLoginValid(String login) {
        if (TextUtils.isEmpty(login))
            return Constants.ErrorCodes.FIELD_EMPTY;
        else
            return Constants.ErrorCodes.OK;
    }

    public static Constants.ErrorCodes isPasswordValid(String password) {
        final Matcher invalidCharsMatcher = invalidCharsRegExp.matcher(password);
        final Matcher passMatcher = passRegExp.matcher(password);
        if (TextUtils.isEmpty(password))
            return Constants.ErrorCodes.FIELD_EMPTY;
        else if (!passMatcher.matches())
            return Constants.ErrorCodes.SHORTNESS;
        else if (!invalidCharsMatcher.matches())
            return Constants.ErrorCodes.INVALID_CHARS;
        else
            return Constants.ErrorCodes.OK;
    }
}
