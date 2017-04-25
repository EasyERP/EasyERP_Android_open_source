package com.thinkmobiles.easyerp.presentation.screens.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.model.social.SocialRegisterProfile;
import com.thinkmobiles.easyerp.data.model.social.SocialType;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication_;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.Arrays;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/13/2017.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;
    private LoginContract.LoginModel loginModel;
    private LoginContract.UserModel userModel;
    private LoginContract.SocialModel socialModel;

    private CookieManager cookieManager;
    private CallbackManager callbackManager = CallbackManager.Factory.create();

    private CompositeSubscription compositeSubscription;

    public LoginPresenter(LoginContract.LoginView view,
                          LoginContract.LoginModel loginModel,
                          LoginContract.UserModel userModel,
                          LoginContract.SocialModel socialModel,
                          CookieManager cookieManager) {
        this.view = view;
        this.loginModel = loginModel;
        this.userModel = userModel;
        this.socialModel = socialModel;
        this.cookieManager = cookieManager;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void login() {
        String login = view.getLogin();
        String pass = view.getPassword();

        Constants.ErrorCodes errCodeLogin = ValidationManager.isLoginValid(login);
        Constants.ErrorCodes errCodePassword = ValidationManager.isPasswordValid(pass);

        view.displayLoginError(errCodeLogin);
        view.displayPasswordError(errCodePassword);

        if(errCodeLogin == Constants.ErrorCodes.OK && errCodePassword == Constants.ErrorCodes.OK) {
            login(login, pass);
        }
    }

    @Override
    public void loginSocial(SocialType socialType) {
        switch (socialType) {
            case FACEBOOK:
                view.loginWithFacebook(Arrays.asList("public_profile", "email"));
                break;
            case LIKENDIN:
                view.loginWithLinkedIn(Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS), linkedInAuthCallback);
                break;
            case GPLUS:
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
//                        If need token, serverClientId must be registered on the same google account and the same the application
//                        .requestIdToken("")
                        .build();
                view.loginWithGoogle(gso, googleConnectionFailedListener);
                break;
        }
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

    private void login(final String login, final String password) {
        view.showProgress("Login. Please wait a second...");
        compositeSubscription.add(
                loginModel.login(login, password)
                        .subscribe(s -> {
                            getCurrentUser();
                        }, t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        })
        );
    }

    private void login(final SocialRegisterProfile socialRegisterProfile) {
        compositeSubscription.add(
                loginModel.login(socialRegisterProfile)
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
    public void forgotPassword(final String login) {
        view.showProgress("Forgot Password. Please wait a second...");
        compositeSubscription.add(
                loginModel.forgotPassword(login)
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
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RequestCodes.RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                view.showProgress("Login via Google+. Please wait a second...");
                login(SocialRegisterProfile.withGoogle(result.getSignInAccount()));
            } else {
                view.showInfoToast("An error occurred. Please try again");
            }
            return true;
        } else return callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void subscribe() {
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private final FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            view.showProgress("Login via Facebook. Please wait a second...");
            compositeSubscription.add(socialModel.loginWithFacebook(loginResult)
                    .subscribe(socialRegisterProfile -> {
                        view.dismissProgress();
                        login(socialRegisterProfile);
                    }, t -> {
                        t.printStackTrace();
                        view.dismissProgress();
                        view.showErrorToast(ErrorManager.getErrorMessage(t));
                    }));
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            view.showErrorToast(ErrorManager.getErrorMessage(error));
        }
    };

    private final AuthListener linkedInAuthCallback = new AuthListener() {
        @Override
        public void onAuthSuccess() {
            view.showProgress("Login via LinkedIn. Please wait a second...");
            compositeSubscription.add(socialModel.loginWithLinkedIn(LISessionManager.getInstance(EasyErpApplication_.getInstance().getApplicationContext()).getSession().getAccessToken())
                    .subscribe(socialRegisterProfile -> {
                        view.dismissProgress();
                        login(socialRegisterProfile);
                    }, t -> {
                        t.printStackTrace();
                        view.dismissProgress();
                        view.showErrorToast(ErrorManager.getErrorMessage(t));
                    }));
        }

        @Override
        public void onAuthError(LIAuthError error) {
            Log.e("LinkedIn", error.toString());
            view.showErrorToast(error.getErrorMsg());
        }
    };

    private final GoogleApiClient.OnConnectionFailedListener googleConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            view.showErrorToast(connectionResult.getErrorMessage());
        }
    };
}
