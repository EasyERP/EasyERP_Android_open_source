package com.thinkmobiles.easyerp.presentation.screens.home;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;

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
                        t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        }
                )
        );
    }

    @Override
    public void changePassword(String userId, String oldPass, String newPass) {
        view.showProgress("Change password. Please wait a second...");
        subscription.add(
                model.changePassword(userId, oldPass, newPass).subscribe(
                        result -> {
                            view.dismissProgress();
                            view.showInfoToast("Your password has been changed");
                        },
                        t -> {
                            view.dismissProgress();
                            view.showErrorToast(ErrorManager.getErrorMessage(t));
                        }
                )
        );
    }
}
