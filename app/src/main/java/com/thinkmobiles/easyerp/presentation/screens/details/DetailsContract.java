package com.thinkmobiles.easyerp.presentation.screens.details;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 28.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface DetailsContract {

    interface DetailsView extends BaseView<DetailsPresenter> {
        void restartApp();
        void showProgress();
        void dismissProgress();
        void showToast(final String msg);
    }

    interface DetailsPresenter extends BasePresenter {
        void logOut();
    }

    interface DetailsModel extends BaseModel {
        Observable<?> logout();
    }
}
