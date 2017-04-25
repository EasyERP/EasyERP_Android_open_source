package com.thinkmobiles.easyerp.presentation.screens.login;

import android.content.Intent;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.linkedin.platform.AccessToken;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.thinkmobiles.easyerp.data.model.social.SocialRegisterProfile;
import com.thinkmobiles.easyerp.data.model.social.SocialType;
import com.thinkmobiles.easyerp.data.model.user.ResponseGetCurrentUser;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface LoginContract {
    interface LoginView extends BaseView<LoginPresenter> {
        void loginWithFacebook(final List<String> readPermissions);
        void loginWithLinkedIn(final Scope scope, final AuthListener authListener);
        void loginWithGoogle(final GoogleSignInOptions gso, final GoogleApiClient.OnConnectionFailedListener connectionFailedListener);

        void showProgress(final String msg);
        void dismissProgress();
        void showErrorToast(final String msg);
        void showInfoToast(final String msg);

        String getLogin();
        String getPassword();

        void displayLoginError(Constants.ErrorCodes code);
        void displayPasswordError(Constants.ErrorCodes code);

        void startHomeScreen(UserInfo userInfo);
    }
    interface LoginPresenter extends BasePresenter {
        void login();
        void loginSocial(final SocialType socialType);
        void getCurrentUser();
        void clearCookies();
        void forgotPassword(final String login);
        boolean onActivityResult(int requestCode, int resultCode, Intent data);
    }
    interface LoginModel extends BaseModel {
        Observable<?> login(String login, String password);
        Observable<?> login(SocialRegisterProfile socialRegisterProfile);
        Observable<?> forgotPassword(String login);
    }
    interface UserModel extends BaseModel {
        Observable<ResponseGetCurrentUser> getCurrentUser();
    }
    interface SocialModel extends BaseModel {
        Observable<SocialRegisterProfile> loginWithFacebook(final LoginResult loginResult);
        Observable<SocialRegisterProfile> loginWithLinkedIn(final AccessToken accessToken);
    }
}
