package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.ResponseGetAttendanceDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.Calendar;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public class AttendanceDetailsPresenter extends ContentPresenterHelper implements AttendanceDetailsContract.AttendanceDetailsPresenter {

    private AttendanceDetailsContract.AttendanceDetailsView view;
    private AttendanceDetailsContract.AttendanceDetailsModel model;
    private String id;
    private int year;

    private ResponseGetAttendanceDetails attendanceDetails;

    public AttendanceDetailsPresenter(AttendanceDetailsContract.AttendanceDetailsView view, AttendanceDetailsContract.AttendanceDetailsModel model, String id) {
        this.view = view;
        this.model = model;
        this.id = id;
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getAttendanceDetails(year, id)
                .subscribe(responseGetAttendanceDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGetAttendanceDetails);
                }, t -> error(t)));
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return attendanceDetails != null;
    }

    @Override
    protected void retainInstance() {
        setData(attendanceDetails);
    }

    @Override
    public void selectYear() {

    }

    private void setData(ResponseGetAttendanceDetails response) {
        attendanceDetails = response;
        for (MonthDetail detail : response.details) {
            view.setMonthDetails(detail.month - 1, detail.vacArray);
        }
    }
}
