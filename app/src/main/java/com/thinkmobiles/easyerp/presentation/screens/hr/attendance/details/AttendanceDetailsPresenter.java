package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.ResponseGetAttendanceDetails;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public class AttendanceDetailsPresenter extends ContentPresenterHelper implements AttendanceDetailsContract.AttendanceDetailsPresenter {

    private AttendanceDetailsContract.AttendanceDetailsView view;
    private AttendanceDetailsContract.AttendanceDetailsModel model;
    private String id;
    private String fullName;
    private int year;

    private ResponseGetAttendanceDetails attendanceDetails;

    public AttendanceDetailsPresenter(AttendanceDetailsContract.AttendanceDetailsView view, AttendanceDetailsContract.AttendanceDetailsModel model, String id, String fullName) {
        this.view = view;
        this.model = model;
        this.id = id;
        this.fullName = fullName;
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
    public void selectYear(int position) {
        int newYear;
        try {
            newYear = Integer.valueOf(attendanceDetails.years.get(position).id);
        } catch (Exception e) {
            newYear = year;
        }
        if (year != newYear) {
            year = newYear;
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
        }
    }

    private void setData(ResponseGetAttendanceDetails response) {
        attendanceDetails = response;
        buildMenu();
        view.setEmployeeName(fullName);
        prepareCalendar(response.details);
    }

    @Override
    public void buildMenu() {
        if (hasContent()) {
            ArrayList<String> years = new ArrayList<>();
            for (FilterItem item : attendanceDetails.years) {
                years.add(item.name);
            }
            view.setYears(years);
            for (String y : years) {
                if (String.valueOf(year).equals(y)) {
                    view.selectYear(years.indexOf(y));
                    break;
                }
            }
        }
    }

    private void prepareCalendar(ArrayList<MonthDetail> months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1);
        int count = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        int weekday = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        int additional = (weekday > 4 ? 1 : 0) + (weekday + count % 52 == 6 ? 1 : 0);
        int working = count - 52 * 2 - additional;
        int v = 0, p = 0, s = 0, e = 0, l = 0;
        for (MonthDetail detail : months) {
            view.setMonthDetails(detail.month - 1, detail.vacArray);
            calendar.set(year, detail.month - 1, 1);
            for (String d : detail.vacArray) {
                if (d != null) {
                    switch (d) {
                        case "V":
                            ++v;
                            break;
                        case "P":
                            ++p;
                            break;
                        case "S":
                            ++s;
                            break;
                        case "E":
                            ++e;
                            break;
                    }
                    l = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? l : l + 1;
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

        }
        working -= l;
        view.setCountWorking(String.valueOf(working));
        view.setCountVacation(String.valueOf(v));
        view.setCountPersonal(String.valueOf(p));
        view.setCountSick(String.valueOf(s));
        view.setCountEducation(String.valueOf(e));
        view.setCountLeave(String.valueOf(l));
    }
}
