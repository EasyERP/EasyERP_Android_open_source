package com.thinkmobiles.easyerp.presentation.screens.about;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/17/2017.
 */

public class AboutPresenter implements AboutContract.AboutPresenter {

    private AboutContract.AboutView view;
    private final AboutContract.AboutModel model;
    private final CookieManager cookieManager;
    private CompositeSubscription compositeSubscription;

    private final boolean isUrlMode;
    private final String data;

    public AboutPresenter(AboutContract.AboutView view, AboutContract.AboutModel model, CookieManager cookieManager, String data, boolean isUrlMode) {
        this.view = view;
        this.model = model;
        this.data = data;
        this.isUrlMode = isUrlMode;
        this.cookieManager = cookieManager;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void logOut() {
        view.showProgress("Logout. Please wait a second...");
        compositeSubscription.add(
                model.logout().subscribe(
                        result -> {
                            cookieManager.clearCookie();
                            view.dismissProgress();
                            view.restartApp();
                        },
                        error -> {
                            view.dismissProgress();
                            view.showErrorToast(error.getMessage());
                        }
                )
        );
    }

    @Override
    public void subscribe() {
        if(isUrlMode) {
            view.displayWebUrl(data);
        } else {
            view.displayTextInfo(data);
        }
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
