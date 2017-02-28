package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;
import com.thinkmobiles.easyerp.presentation.screens.tutorial.TutorialContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LoginRepository extends NetworkRepository implements LoginContract.LoginModel, TutorialContract.TutorialModel {

    private LoginService loginService;

    public LoginRepository() {
        loginService = Rest.getInstance().getLoginService();
    }

    @Override
    public Observable<?> login(String login, String pass, String dbId) {
        return getNetworkObservable(loginService.login(login, pass, dbId, true));
    }

    @Override
    public Observable<?> forgotPassword(String login, String dbId) {
        return getNetworkObservable(loginService.forgotPassword(login, dbId));
    }
}
