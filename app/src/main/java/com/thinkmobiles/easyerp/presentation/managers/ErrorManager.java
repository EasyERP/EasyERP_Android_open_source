package com.thinkmobiles.easyerp.presentation.managers;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Lynx on 2/17/2017.
 */

public abstract class ErrorManager {

    public static Constants.ErrorType getErrorType(Throwable t) {
        if(t == null) return Constants.ErrorType.LIST_EMPTY;
        else if(t instanceof UnknownHostException || t instanceof SocketTimeoutException)
            return Constants.ErrorType.NETWORK;
        else
            return Constants.ErrorType.UNKNOWN;
    }

    public static String getErrorMessage(Throwable t) {
        if(t == null)
            return "Sorry, but list is empty.\nPlease try again later!";    //exist in res
        else if(t instanceof HttpException) {
            HttpException e = (HttpException) t;
            if (e.response().errorBody() != null) {
                ResponseError responseError = Rest.getInstance().parseError(e.response().errorBody());
                return responseError.error;
            } else return e.message();
        }
         else if(t instanceof UnknownHostException || t instanceof SocketTimeoutException)
            return "Connection problems. Make sure your internet connection established";
        else
            return "Something going wrong :(";
    }

    @StringRes
    public static int getErrorMessage(Constants.ErrorType type) {
        switch (type) {
            case LIST_EMPTY:
                return R.string.list_is_empty;
            case NETWORK:
                return R.string.error_connection;
            case UNKNOWN:
                return R.string.error_unknown;
        }
        return 0;
    }

    @DrawableRes
    public static int getErrorIcon(Constants.ErrorType type) {
        switch (type) {
            case LIST_EMPTY:
                return R.drawable.ic_empty_content;
            case NETWORK:
            case UNKNOWN:
                return R.drawable.ic_error;
        }
        return 0;
    }
}
