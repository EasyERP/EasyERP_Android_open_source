package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * Created by Lynx on 2/20/2017.
 */

public interface TutorialContract {
    interface TutorialView extends BaseView<TutorialPresenter> {
        void startLoginScreen();
        void startDemoMode();
    }
    interface TutorialPresenter extends BasePresenter {
        void login();
        void demo();
    }
}
