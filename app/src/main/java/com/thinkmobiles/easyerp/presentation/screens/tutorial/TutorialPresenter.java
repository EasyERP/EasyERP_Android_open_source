package com.thinkmobiles.easyerp.presentation.screens.tutorial;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.adapter.rxjava.HttpException;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 2/20/2017.
 */

public class TutorialPresenter implements TutorialContract.TutorialPresenter {

    private TutorialContract.TutorialView view;
    private TutorialContract.TutorialModel tutorialModel;
    private TutorialContract.UserModel userModel;
    private CompositeSubscription compositeSubscription;

    private boolean isPreview;

    public TutorialPresenter(TutorialContract.TutorialView view, TutorialContract.TutorialModel tutorialModel, CookieManager cookieManager, TutorialContract.UserModel userModel, boolean isPreview) {
        this.view = view;
        this.tutorialModel = tutorialModel;
        this.userModel = userModel;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);

        if(!isPreview && cookieManager.isCookieExists() && !cookieManager.isCookieExpired()) {
            view.startLoginScreen();
        }
    }

    @Override
    public void login() {
        view.startLoginScreen();
    }

    @Override
    public void demo() {
        view.showProgress("Login. Please wait a second...");
        compositeSubscription.add(
                tutorialModel.login(Constants.DEMO_LOGIN, Constants.DEMO_PASSWORD, Constants.DEMO_DB_ID)
                        .subscribe(s -> {
                            if(s.equalsIgnoreCase("OK")) {
                                getCurrentUser();
                            } else
                                view.dismissProgress();
                        }, t -> {
                            view.dismissProgress();
                            view.showInfoToast(ErrorManager.getErrorMessage(t));
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

    private void getCurrentUser() {
        compositeSubscription.add(
                userModel.getCurrentUser()
                        .subscribe(responseGetCurrentUser -> {
                            view.dismissProgress();
                            view.startHomeScreen(responseGetCurrentUser.user);
                        }, t -> {
                            view.dismissProgress();
                            view.showInfoToast(ErrorManager.getErrorMessage(t));
                        })
        );
    }
}
