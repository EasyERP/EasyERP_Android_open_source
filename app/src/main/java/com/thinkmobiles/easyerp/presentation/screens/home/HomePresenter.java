package com.thinkmobiles.easyerp.presentation.screens.home;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/13/2017.
 */

public final class HomePresenter implements HomeContract.HomePresenter {

    private final HomeContract.HomeView view;
    private final HomeContract.HomeModel model;
    private final CookieManager cookieManager;

    private final CompositeSubscription subscription;

    public HomePresenter(HomeContract.HomeView view, HomeContract.HomeModel model, CookieManager cookieManager) {
        this.view = view;
        this.model = model;
        this.cookieManager = cookieManager;

        this.subscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (subscription.hasSubscriptions())
            subscription.clear();
    }

    @Override
    public void logOut() {
        view.showProgress("Logout. Please wait a second...");
        subscription.add(
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
}
