package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class UserRepository implements LoginContract.LoginModel {

    private LoginService loginService;

    public UserRepository() {
        loginService = Rest.getInstance().getLoginService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<String> login(String login, String pass, String dbId) {
        return getNetworkObservable(loginService.login(login, pass, dbId, true));
    }
}
