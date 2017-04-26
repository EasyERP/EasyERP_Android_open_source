package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;
import android.util.Patterns;

import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lynx on 1/19/2017.
 */

public abstract class ValidationManager {

    private static final Pattern invalidCharsRegExp = Pattern.compile("^[a-zA-Z0-9_@]+$");
    private static final Pattern invalidEmailRegExp = Patterns.EMAIL_ADDRESS;
    private static final Pattern shortRegExp = Pattern.compile("^[a-zA-Z0-9_@.]{3,100}$");

    public static Constants.ErrorCode isNameValid(String name) {
        if (TextUtils.isEmpty(name))
            return Constants.ErrorCode.FIELD_EMPTY;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isLoginValid(String login) {
        final Matcher loginMatcher = shortRegExp.matcher(login);
        if (TextUtils.isEmpty(login))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (!loginMatcher.matches())
            return Constants.ErrorCode.SHORTNESS;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isPasswordValid(String password) {
        final Matcher invalidCharsMatcher = invalidCharsRegExp.matcher(password);
        final Matcher passMatcher = shortRegExp.matcher(password);
        if (TextUtils.isEmpty(password))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (!invalidCharsMatcher.matches())
            return Constants.ErrorCode.INVALID_CHARS;
        else if (!passMatcher.matches())
            return Constants.ErrorCode.SHORTNESS;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isEmailValid(String email) {
        final Matcher invalidCharsMatcher = invalidEmailRegExp.matcher(email);
        if (TextUtils.isEmpty(email))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (!invalidCharsMatcher.matches())
            return Constants.ErrorCode.INVALID_EMAIL;
        else
            return Constants.ErrorCode.OK;
    }
}
