package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.hr.AttendanceRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
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

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_attendance_details;
    }

    @FragmentArg
    protected String id;
    @FragmentArg
    protected String fullName;

    private AttendanceDetailsContract.AttendanceDetailsPresenter presenter;
    private CalendarManager calendarManager;
    private ArrayAdapter<String> spinnerAdapter;

    @ViewById
    protected RecyclerView rvYear_FAD;
    @ViewById
    protected Spinner spYears_FAD;
    @ViewById
    protected TextView tvEmployeeName_FAD;
    @ViewById
    protected TextView tvCountWorking_FAD;
    @ViewById
    protected TextView tvCountVacation_FAD;
    @ViewById
    protected TextView tvCountPersonal_FAD;
    @ViewById
    protected TextView tvCountSick_FAD;
    @ViewById
    protected TextView tvCountEducation_FAD;
    @ViewById
    protected TextView tvCountLeave_FAD;

    @Bean
    protected AttendanceRepository attendanceRepository;


    @Override
    public AttendanceDetailsContract.AttendanceDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(AttendanceDetailsContract.AttendanceDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new AttendanceDetailsPresenter(this, attendanceRepository, id, fullName);
        spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.view_list_item_year);
    }

    @AfterViews
    protected void initUI() {
        calendarManager = new CalendarManager(rvYear_FAD);
        spYears_FAD.setAdapter(spinnerAdapter);
        spYears_FAD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.selectYear(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getPresenter().subscribe();
    }

    @Override
    public void setYears(ArrayList<String> years) {
        spinnerAdapter.clear();
        spinnerAdapter.addAll(years);
    }

    @Override
    public void setMonthDetails(int month, ArrayList<String> types) {
        calendarManager.setMonth(month, types);
    }

    @Override
    public void setEmployeeName(String name) {
        tvEmployeeName_FAD.setText(name);
    }

    @Override
    public void selectYear(int position) {
        spYears_FAD.setSelection(position, false);
        calendarManager.setYear(Integer.valueOf(spinnerAdapter.getItem(position)));
    }

    @Override
    public void setCountWorking(String countWorking) {
        tvCountWorking_FAD.setText(countWorking);
    }

    @Override
    public void setCountVacation(String countVacation) {
        tvCountVacation_FAD.setText(countVacation);
    }

    @Override
    public void setCountPersonal(String countPersonal) {
        tvCountPersonal_FAD.setText(countPersonal);
    }

    @Override
    public void setCountSick(String countSick) {
        tvCountSick_FAD.setText(countSick);
    }

    @Override
    public void setCountEducation(String countEducation) {
        tvCountEducation_FAD.setText(countEducation);
    }

    @Override
    public void setCountLeave(String countLeave) {
        tvCountLeave_FAD.setText(countLeave);
    }

    @Override
    public String getScreenName() {
        return "Attendance details screen";
    }
}
