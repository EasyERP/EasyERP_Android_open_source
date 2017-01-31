package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LoginRepository extends NetworkRepository implements LoginContract.LoginModel {

    private LoginService loginService;

    public LoginRepository() {
        loginService = Rest.getInstance().getLoginService();
    }

    public Observable<String> login(String login, String pass, String dbId) {
        return getNetworkObservable(loginService.login(login, pass, dbId, true));
    }
}
