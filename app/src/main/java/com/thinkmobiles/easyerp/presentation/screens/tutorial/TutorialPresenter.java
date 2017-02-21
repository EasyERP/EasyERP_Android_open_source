package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.presentation.utils.Constants;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/20/2017.
 */

public class TutorialPresenter implements TutorialContract.TutorialPresenter {

    private TutorialContract.TutorialView view;
    private TutorialContract.TutorialModel tutorialModel;
    private CompositeSubscription compositeSubscription;

    public TutorialPresenter(TutorialContract.TutorialView view, TutorialContract.TutorialModel tutorialModel) {
        this.view = view;
        this.tutorialModel = tutorialModel;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void login() {
        view.startLoginScreen();
    }

    @Override
    public void demo() {
        compositeSubscription.add(
                tutorialModel.login(Constants.DEMO_LOGIN, Constants.DEMO_PASSWORD, Constants.DEMO_DB_ID)
                .subscribe(s -> {

                }, t -> {

                })
        );
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }
}
