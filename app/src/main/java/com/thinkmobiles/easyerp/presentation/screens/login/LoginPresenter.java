package com.thinkmobiles.easyerp.presentation.screens.login;

import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

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
            login(login, pass, dbId);
        }
    }

    @Override
    public void launchDemoMode() {
        if (BuildConfig.PRODUCTION)
            login(null, null, null);
        else login(Constants.DEMO_LOGIN, Constants.DEMO_PASSWORD, Constants.DEMO_DB_ID);
    }

    @Override
    public void getCurrentUser() {
        compositeSubscription.add(
                userModel.getCurrentUser()
                        .subscribe(responseGetCurrentUser -> {
                            view.dismissProgress();
                            view.startHomeScreen(responseGetCurrentUser.user);
                        }, t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        })
        );
    }

    private void login(final String login, final String password, final String databaseId) {
        view.showProgress("Login. Please wait a second...");
        compositeSubscription.add(
                loginModel.login(login, password, databaseId)
                        .subscribe(s -> {
                            getCurrentUser();
                        }, t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        })
        );
    }

    @Override
    public void clearCookies() {
        cookieManager.clearCookie();
    }

    @Override
    public void forgotPassword(final String login, final String dbId) {
        view.showProgress("Forgot Password. Please wait a second...");
        compositeSubscription.add(
                loginModel.forgotPassword(login, dbId)
                        .subscribe(s -> {
                            view.dismissProgress();
                            view.showInfoToast("The new password was sent to your email. Please check it");
                        }, t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        })
        );
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
