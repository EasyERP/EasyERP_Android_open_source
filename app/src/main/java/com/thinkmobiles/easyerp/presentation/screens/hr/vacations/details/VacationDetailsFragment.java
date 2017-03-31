package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.domain.hr.VacationsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.VacationPersonAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationPersonDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/29/2017.
 */

@EFragment
public class VacationDetailsFragment extends ContentFragment implements VacationDetailsContract.VacationDetailsView {

    private VacationDetailsContract.VacationDetailsPresenter presenter;

    @FragmentArg
    protected int year;
    @FragmentArg
    protected int month;

    @ViewById
    protected TextView tvTitleMonth_FVD;
    @ViewById
    protected RecyclerView rvVacationDetailsList_FVD;

    @Bean
    protected VacationsRepository vacationsRepository;
    @Bean
    protected VacationPersonAdapter vacationPersonAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vacations_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new VacationDetailsPresenter(this, vacationsRepository, year, month);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvVacationDetailsList_FVD.setLayoutManager(llm);
        rvVacationDetailsList_FVD.setAdapter(vacationPersonAdapter);

        getPresenter().subscribe();
    }

    @Override
    public void setPresenter(VacationDetailsContract.VacationDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Vacation Details screen";
    }

    @Override
    public void setTitleMonth(String month) {
        tvTitleMonth_FVD.setText(month);
    }

    @Override
    public void setVacationPersons(ArrayList<VacationPersonDH> vacationPersonDHs) {
        rvVacationDetailsList_FVD.setVisibility(vacationPersonDHs.isEmpty() ? View.GONE : View.VISIBLE);
        vacationPersonAdapter.setListDH(vacationPersonDHs);
    }

    @Override
    public void displayDetails(MonthDetail monthDetail) {
        Toast.makeText(getActivity(), "Details with ID = " + monthDetail.employee.id, Toast.LENGTH_SHORT).show();
    }
}
