package com.thinkmobiles.easyerp.presentation.managers;

import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Lynx on 2/17/2017.
 */

public abstract class ErrorManager {

    public static ErrorViewHelper.ErrorType getErrorType(Throwable t) {
        if(t == null) return ErrorViewHelper.ErrorType.LIST_EMPTY;
        else if(t instanceof UnknownHostException || t instanceof SocketTimeoutException)
            return ErrorViewHelper.ErrorType.NETWORK;
        else
            return ErrorViewHelper.ErrorType.UNKNOWN;
    }

    public static String getErrorMessage(Throwable t) {
        if(t == null)
            return "Sorry, but list is empty.\nPlease try again later!";    //exist in res
         else if(t instanceof UnknownHostException || t instanceof SocketTimeoutException)
            return "Connection problems. Make sure your internet connection established";
        else
            return "Something going wrong :(";
    }
}
