package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * Created by Lynx on 2/20/2017.
 */

public interface TutorialContract {
    interface TutorialView extends BaseView<TutorialPresenter> {
        void showProgress(final String msg);
        void dismissProgress();
        void showInfoToast(final String msg);

        void startLoginScreen(final boolean loginActivated);
        void startHomeScreen(UserInfo userInfo);
    }
    interface TutorialPresenter extends BasePresenter {
        void logIn();
        void signUp();
    }
    interface TutorialModel extends BaseModel {
    }
}
