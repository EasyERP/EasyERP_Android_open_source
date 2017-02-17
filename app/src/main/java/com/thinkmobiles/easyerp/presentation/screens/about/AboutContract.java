package com.thinkmobiles.easyerp.presentation.screens.about;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import rx.Observable;

/**
 * Created by Lynx on 2/17/2017.
 */

public interface AboutContract {
    interface AboutView extends BaseView<AboutPresenter> {
        void displayWebUrl(String url);
        void displayTextInfo(String textInfo);

        void restartApp();
        void showProgress(final String msg);
        void dismissProgress();
        void showErrorToast(final String msg);
    }
    interface AboutPresenter extends BasePresenter {
        void logOut();
    }
    interface AboutModel extends BaseModel {
        Observable<?> logout();
    }
}
