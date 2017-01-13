package com.thinkmobiles.easyerp.presentation.screens.login;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface LoginCotract {
    interface LoginView extends BaseView<LoginPresenter> {
        void displayResult(String result);
        void displayError(String error);

        String getLogin();
        String getPassword();
        String getDbID();
        boolean isRememberMe();
    }
    interface LoginPresenter extends BasePresenter {
        void login();
    }
    interface LoginModel extends BaseModel {
        Observable<String> login(String login, String password, String dbId, boolean isRememberMe);
    }
}
