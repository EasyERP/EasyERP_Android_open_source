package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.adapters.hr.VacationRangeAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.DayDH;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.MonthView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationRangeDH;
import com.thinkmobiles.easyerp.presentation.listeners.IFragmentInstance;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 29.03.2017.
 */

@EFragment
public class VacationOverviewFragment extends ContentFragment implements VacationOverviewContract.VacationOverviewView {

    @FragmentArg
    protected MonthDetail monthDetail;

    @ViewById
    protected TextView tvTitle_FVO;
    @ViewById
    protected TextView tvPosition_FVO;
    @ViewById
    protected TextView tvCountWorking_FVO;
    @ViewById
    protected TextView tvCountVacation_FVO;
    @ViewById
    protected TextView tvCountPersonal_FVO;
    @ViewById
    protected TextView tvCountSick_FVO;
    @ViewById
    protected TextView tvCountEducation_FVO;
    @ViewById
    protected TextView tvCountLeave_FVO;
    @ViewById
    protected MonthView month;
    @ViewById
    protected RecyclerView rvVacationList_FVO;

    private VacationOverviewContract.VacationOverviewPresenter presenter;
    @Bean
    protected VacationRangeAdapter vacationRangeAdapter;


    public static IFragmentInstance getCreator() {
        return (IFragmentInstance) args -> VacationOverviewFragment_.builder().arg(args).build();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vacation_overview;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new VacationOverviewPresenter(this, monthDetail);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        rvVacationList_FVO.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVacationList_FVO.setAdapter(vacationRangeAdapter);
        getPresenter().subscribe();
    }

    @Override
    public void setPresenter(VacationOverviewContract.VacationOverviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Vacation details of person screen";
    }

    @Override
    public void setTitle(String title) {
        tvTitle_FVO.setText(title);
    }

    @Override
    public void setPosition(String position) {
        tvPosition_FVO.setText(position);
    }

    @Override
    public void setCountWorking(String countWorking) {
        tvCountWorking_FVO.setText(countWorking);
    }

    @Override
    public void setCountVacation(String countVacation) {
        tvCountVacation_FVO.setText(countVacation);
    }

    @Override
    public void setCountPersonal(String countPersonal) {
        tvCountPersonal_FVO.setText(countPersonal);
    }

    @Override
    public void setCountSick(String countSick) {
        tvCountSick_FVO.setText(countSick);
    }

    @Override
    public void setCountEducation(String countEducation) {
        tvCountEducation_FVO.setText(countEducation);
    }

    @Override
    public void setCountLeave(String countLeave) {
        tvCountLeave_FVO.setText(countLeave);
    }

    @Override
    public void initMonth(String title, ArrayList<DayDH> dayDHs) {
        month.setMonthTitle(title);
        month.setDays(dayDHs);
    }

    @Override
    public void setRanges(ArrayList<VacationRangeDH> ranges) {
        vacationRangeAdapter.setListDH(ranges);
    }
}
