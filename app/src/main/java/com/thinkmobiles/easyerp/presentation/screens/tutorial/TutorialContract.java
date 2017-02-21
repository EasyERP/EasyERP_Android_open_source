package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.data.model.user.ResponseGetCurrentUser;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import rx.Observable;

/**
 * Created by Lynx on 2/20/2017.
 */

public interface TutorialContract {
    interface TutorialView extends BaseView<TutorialPresenter> {
        void showProgress(final String msg);
        void dismissProgress();
        void showInfoToast(final String msg);

        void startLoginScreen();
        void startHomeScreen(UserInfo userInfo);
    }
    interface TutorialPresenter extends BasePresenter {
        void login();
        void demo();
    }
    interface TutorialModel extends BaseModel {
        Observable<String> login(String login, String password, String dbId);
    }
    interface UserModel extends BaseModel {
        Observable<ResponseGetCurrentUser> getCurrentUser();
    }
}
