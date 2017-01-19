package com.thinkmobiles.easyerp.presentation.screens.login;

import com.thinkmobiles.easyerp.data.model.user.ResponseGetCurrentUser;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface LoginContract {
    interface LoginView extends BaseView<LoginPresenter> {
        void displayError(String error);

        String getLogin();
        String getPassword();
        String getDbID();

        void displayLoginError(Constants.ErrorCodes code);
        void displayPasswordError(Constants.ErrorCodes code);
        void displayDbIdError(Constants.ErrorCodes code);

        void startHomeScreen(UserInfo userInfo);
    }
    interface LoginPresenter extends BasePresenter {
        void login();
        void getCurrentUser();

        void clearCookies();
    }
    interface LoginModel extends BaseModel {
        Observable<String> login(String login, String password, String dbId);
    }
    interface UserModel extends BaseModel {
        Observable<ResponseGetCurrentUser> getCurrentUser();
    }
}
