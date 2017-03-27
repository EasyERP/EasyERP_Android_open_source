package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.hr.AttendanceRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.managers.CalendarManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 22.03.2017.
 */
@EFragment
public class AttendanceDetailsFragment extends ContentFragment implements AttendanceDetailsContract.AttendanceDetailsView {

    private AttendanceDetailsContract.AttendanceDetailsPresenter presenter;
    private CalendarManager calendarManager;

    @FragmentArg
    protected String id;
    @Bean
    protected AttendanceRepository attendanceRepository;

    @ViewById
    protected RecyclerView rvYear_FAD;

    @Override
    public AttendanceDetailsContract.AttendanceDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(AttendanceDetailsContract.AttendanceDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_attendance_details;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new AttendanceDetailsPresenter(this, attendanceRepository, id);
    }

    @AfterViews
    protected void initUI() {
        calendarManager = new CalendarManager(rvYear_FAD);
        getPresenter().subscribe();
    }

    @Override
    public void setYears(ArrayList<String> years) {

    }

    @Override
    public void setMonthDetails(int month, ArrayList<String> types) {
        calendarManager.setMonth(month, types);
    }

    @Override
    public String getScreenName() {
        return "Attendance details screen";
    }
}
