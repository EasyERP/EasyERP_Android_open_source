package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public class AttendanceDetailsPresenter extends ContentPresenterHelper implements AttendanceDetailsContract.AttendanceDetailsPresenter {

    private AttendanceDetailsContract.AttendanceDetailsView view;
    private AttendanceDetailsContract.AttendanceDetailsModel model;

    public AttendanceDetailsPresenter(AttendanceDetailsContract.AttendanceDetailsView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        view.showProgress(Constants.ProgressType.NONE);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return false;
    }

    @Override
    protected void retainInstance() {

    }
}
