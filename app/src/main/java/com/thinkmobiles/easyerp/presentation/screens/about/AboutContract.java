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
        void showErrorToast(final String msg);
        void showProgress(boolean isShown);
    }
    interface AboutPresenter extends BasePresenter {
    }
    interface AboutModel extends BaseModel {
    }
}
