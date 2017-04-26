package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/20/2017.
 */

public class TutorialPresenter implements TutorialContract.TutorialPresenter {

    private TutorialContract.TutorialView view;
    private TutorialContract.TutorialModel tutorialModel;
    private CompositeSubscription compositeSubscription;

    public TutorialPresenter(TutorialContract.TutorialView view, TutorialContract.TutorialModel tutorialModel, CookieManager cookieManager, boolean isPreview) {
        this.view = view;
        this.tutorialModel = tutorialModel;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);

        if(!isPreview && cookieManager.isCookieExists() && !cookieManager.isCookieExpired()) {
            logIn();
        }
    }

    @Override
    public void logIn() {
        view.startLoginScreen(true);
    }

    @Override
    public void signUp() {
        view.startLoginScreen(false);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }
}
