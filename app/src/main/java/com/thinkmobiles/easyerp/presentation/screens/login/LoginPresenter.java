package com.thinkmobiles.easyerp.presentation.screens.login;

import android.util.Log;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.adapter.rxjava.HttpException;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/13/2017.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;
    private LoginContract.LoginModel loginModel;
    private LoginContract.UserModel userModel;
    private CookieManager cookieManager;

    private CompositeSubscription compositeSubscription;

    public LoginPresenter(LoginContract.LoginView view, LoginContract.LoginModel loginModel, LoginContract.UserModel userModel, CookieManager cookieManager) {
        this.view = view;
        this.loginModel = loginModel;
        this.userModel = userModel;
        this.cookieManager = cookieManager;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void login() {
        String login = view.getLogin();
        String pass = view.getPassword();
        String dbId = view.getDbID();

        Constants.ErrorCodes errCodeLogin = ValidationManager.isLoginValid(login);
        Constants.ErrorCodes errCodePassword = ValidationManager.isPasswordValid(pass);
        Constants.ErrorCodes errCodeDbID = ValidationManager.isDbIDValid(dbId);

        view.displayLoginError(errCodeLogin);
        view.displayPasswordError(errCodePassword);
        view.displayDbIdError(errCodeDbID);

        if(errCodeLogin == Constants.ErrorCodes.OK
                && errCodePassword == Constants.ErrorCodes.OK
                && errCodeDbID == Constants.ErrorCodes.OK) {
            compositeSubscription.add(
                    loginModel.login(login, pass, dbId)
                            .subscribe(s -> {
                                if(s.equalsIgnoreCase("OK")) {
                                    getCurrentUser();
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
    public void getCurrentUser() {
        compositeSubscription.add(
                userModel.getCurrentUser()
                .subscribe(responseGetCurrentUser -> {
                    view.startHomeScreen(responseGetCurrentUser.user);
                }, t -> {
                    view.displayError(t.getMessage());
                    Log.d("HTTP", "Error: " + t.getMessage());
                })
        );
    }

    @Override
    public void clearCookies() {
        cookieManager.clearCookie();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
