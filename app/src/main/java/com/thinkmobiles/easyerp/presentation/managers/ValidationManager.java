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

//    private static final Pattern invalidCharsRegExp = Pattern.compile("^[a-zA-Z0-9_$@!%*?&]+$");
    private static final Pattern invalidEmailRegExp = Patterns.EMAIL_ADDRESS;
    private static final Pattern invalidPhoneRegExp = Patterns.PHONE;
    private static final Pattern invalidWebSiteRegExp = Patterns.WEB_URL;
//    private static final Pattern shortRegExp = Pattern.compile("^[a-zA-Z0-9_@.]{8,100}$");
    private static final Pattern passwordMatcherFromWeb = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@!%*?&])[A-Za-z\\d$@!%*?&]{8,}");

    private static final Pattern hasUpperCaseRegExp = Pattern.compile("[A-Z]");
    private static final Pattern hasSymbolRegExp = Pattern.compile("[_@]");
    private static final Pattern hasNumberRegExp = Pattern.compile("[0-9]");

    public static Constants.ErrorCode isNameValid(String name) {
        if (TextUtils.isEmpty(name))
            return Constants.ErrorCode.FIELD_EMPTY;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isLoginValid(String login) {
//        final Matcher loginMatcher = shortRegExp.matcher(login);
        if (TextUtils.isEmpty(login))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (login.length() < 8 && login.length() > 100)
            return Constants.ErrorCode.SHORTNESS;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isPasswordValid(String password) {
//        final Matcher invalidCharsMatcher = invalidCharsRegExp.matcher(password);
//        final Matcher passMatcher = shortRegExp.matcher(password);
        if (TextUtils.isEmpty(password))
            return Constants.ErrorCode.FIELD_EMPTY;
//        else if (!invalidCharsMatcher.matches())
//            return Constants.ErrorCode.INVALID_CHARS;
        else if (password.length() < 8 && password.length() > 100)
            return Constants.ErrorCode.SHORTNESS;
//        else if (!hasUpperCaseRegExp.matcher(password).find()
//                || !hasNumberRegExp.matcher(password).find()
//                || !hasSymbolRegExp.matcher(password).find())
        else if (!passwordMatcherFromWeb.matcher(password).find()) //TODO: this find() is incorrect, but PM said it's ok :)
            return Constants.ErrorCode.WEAK_PASSWORD;
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

    public static Constants.ErrorCode isPhoneValid(String phone) {
        if (TextUtils.isEmpty(phone))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (!invalidPhoneRegExp.matcher(phone).matches())
            return Constants.ErrorCode.INVALID_PHONE;
        else
            return Constants.ErrorCode.OK;
    }

    public static Constants.ErrorCode isSiteValid(String site) {
        if (TextUtils.isEmpty(site))
            return Constants.ErrorCode.FIELD_EMPTY;
        else if (!invalidWebSiteRegExp.matcher(site).matches())
            return Constants.ErrorCode.INVALID_SITE;
        else
            return Constants.ErrorCode.OK;
    }
}
