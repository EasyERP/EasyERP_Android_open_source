package com.thinkmobiles.easyerp.presentation.screens.home;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface HomeContract {
    interface HomeView extends BaseView<HomePresenter> {
        void restartApp();
        void showProgress(final String msg);
        void dismissProgress();
        void showErrorToast(final String msg);
        void showInfoToast(final String msg);
    }
    interface HomePresenter extends BasePresenter {
        void logOut();
        void changePassword(final String userId, final String oldPass, final String newPass);
    }
    interface HomeModel extends BaseModel {
        Observable<?> logout();
        Observable<?> changePassword(final String userId, final String oldPass, final String newPass);
    }
}
