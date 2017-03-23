package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * Created by Lynx on 3/22/2017.
 */

public class ApplicationDetailsPresenter extends ContentPresenterHelper implements ApplicationDetailsContract.ApplicationDetailsPresenter {

    private ApplicationDetailsContract.ApplicationDetailsView view;
    private ApplicationDetailsContract.ApplicationDetailsModel model;
    private String applicationID;

    private ResponseEmployeeDetails currentEApplication;
    private boolean isVisibleHistory;

    public ApplicationDetailsPresenter(ApplicationDetailsContract.ApplicationDetailsView view, ApplicationDetailsContract.ApplicationDetailsModel model, String applicationID) {
        this.view = view;
        this.model = model;
        this.applicationID = applicationID;

        view.setPresenter(this);
    }

    @Override
    protected ContentView getView() {
        return null;
    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void changeNotesVisibility() {

    }

    @Override
    public void startAttachment(int pos) {

    }
}
