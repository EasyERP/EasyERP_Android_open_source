package com.thinkmobiles.easyerp.presentation.screens.login;

import android.text.TextUtils;
import android.util.Log;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseError;

import retrofit2.adapter.rxjava.HttpException;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/13/2017.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;
    private LoginContract.LoginModel model;

    private CompositeSubscription compositeSubscription;

    public LoginPresenter(LoginContract.LoginView view, LoginContract.LoginModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void login() {
        String login = view.getLogin();
        String pass = view.getPassword();
        String dbId = view.getDbID();
        boolean isRememberMe = view.isRememberMe();

        if(TextUtils.isEmpty(login)) {
            view.displayError("Input login");
        } else if(TextUtils.isEmpty(pass)) {
            view.displayError("Input password");
        } else if(TextUtils.isEmpty(dbId)) {
            view.displayError("Input database ID");
        } else {
            compositeSubscription.add(
                    model.login(login, pass, dbId, isRememberMe)
                            .subscribe(s -> {
                                if(s.equalsIgnoreCase("OK")) {
                                    view.startHomeScreen();
                                }
                                Log.d("HTTP", "Response: " + s);
                            }, t -> {
                                String errMsg = "";
                                if(t instanceof HttpException) {
                                    HttpException e = (HttpException) t;
                                    ResponseError responseError = Rest.getInstance().parseError(e.response().errorBody());
                                    errMsg = responseError.error;
                                } else {
                                    errMsg = t.getMessage();
                                }
                                view.displayError(errMsg);
                                Log.d("HTTP", "Error: " + t.getMessage());
                            })
            );
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }
}
