package com.thinkmobiles.easyerp.presentation.screens.web;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * Created by Lynx on 2/17/2017.
 */

public interface WebContract {
    interface WebView extends BaseView<WebPresenter> {
        void displayWebUrl(String url);
        void showErrorToast(final String msg);
        void showProgress(boolean isShown);
    }
    interface WebPresenter extends BasePresenter {
    }
    interface WebModel extends BaseModel {
    }
}
