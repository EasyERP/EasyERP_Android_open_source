package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.user.ResponseGetCurrentUser;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.rules.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/18/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class UserRepository extends NetworkRepository implements LoginContract.UserModel {

    private UserService userService;

    public UserRepository() {
        userService = Rest.getInstance().getUserService();
    }

    public Observable<ResponseGetCurrentUser> getCurrentUser() {
        return getNetworkObservable(userService.getCurrentUser());
    }

}
