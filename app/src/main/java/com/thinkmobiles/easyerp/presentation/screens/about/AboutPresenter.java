package com.thinkmobiles.easyerp.presentation.screens.about;

import com.thinkmobiles.easyerp.presentation.managers.CookieManager;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/17/2017.
 */

public class AboutPresenter implements AboutContract.AboutPresenter {

    private AboutContract.AboutView view;
    private final AboutContract.AboutModel model;
    private CompositeSubscription compositeSubscription;

    private final String url;

    public AboutPresenter(AboutContract.AboutView view, AboutContract.AboutModel model, CookieManager cookieManager, String url) {
        this.view = view;
        this.model = model;
        this.url = url;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.showProgress(true);
        view.displayWebUrl(url);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
