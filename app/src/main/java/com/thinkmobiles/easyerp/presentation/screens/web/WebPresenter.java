package com.thinkmobiles.easyerp.presentation.screens.web;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/17/2017.
 */

public class WebPresenter implements WebContract.WebPresenter {

    private WebContract.WebView view;
    private final WebContract.WebModel model;
    private CompositeSubscription compositeSubscription;

    private final String url;

    public WebPresenter(WebContract.WebView view, WebContract.WebModel model, String url) {
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
