package com.thinkmobiles.easyerp.presentation.managers;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * Created by Lynx on 1/19/2017.
 */

public abstract class ValidationManager {

    public static Constants.ErrorCodes isLoginValid(String login) {
        if(TextUtils.isEmpty(login))
            return Constants.ErrorCodes.FIELD_EMPTY;
        else
            return Constants.ErrorCodes.OK;
    }

    public static Constants.ErrorCodes isPasswordValid(String password) {
        if(TextUtils.isEmpty(password))
            return Constants.ErrorCodes.FIELD_EMPTY;
        else
            return Constants.ErrorCodes.OK;
    }

    public static Constants.ErrorCodes isDbIDValid(String dbID) {
        if(TextUtils.isEmpty(dbID))
            return Constants.ErrorCodes.FIELD_EMPTY;
        else
            return Constants.ErrorCodes.OK;
    }

}
