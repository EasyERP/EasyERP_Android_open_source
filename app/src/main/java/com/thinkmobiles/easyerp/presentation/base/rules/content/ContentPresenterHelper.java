package com.thinkmobiles.easyerp.presentation.base.rules.content;

import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class ContentPresenterHelper implements ContentPresenter {

    protected abstract ContentView getView();
    protected abstract boolean hasContent();
    protected abstract void retainInstance();

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void refresh() {

    }

    @Override
    public void subscribe() {
        if (!hasContent()) {
            request();
        } else {
            retainInstance();
        }
    }

    protected void request() {
        getView().showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    protected void error(Throwable t) {
        t.printStackTrace();
        if (hasContent()) {
            getView().displayErrorToast(ErrorManager.getErrorType(t));
        } else {
            getView().displayErrorState(ErrorManager.getErrorType(t));
        }
    }
}
