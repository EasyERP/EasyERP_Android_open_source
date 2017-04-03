package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.details;

import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationPersonDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

/**
 * Created by Lynx on 3/29/2017.
 */

public class VacationDetailsPresenter extends ContentPresenterHelper implements VacationDetailsContract.VacationDetailsPresenter {

    private VacationDetailsContract.VacationDetailsView view;
    private VacationDetailsContract.VacationDetailsModel model;
    private int year;
    private int month;

    private ArrayList<MonthDetail> monthDetailsList = new ArrayList<>();

    public VacationDetailsPresenter(VacationDetailsContract.VacationDetailsView view, VacationDetailsContract.VacationDetailsModel model, int year, int month) {
        this.view = view;
        this.model = model;
        this.year = year;
        this.month = month;

        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        model.getVacationDetails(year, month)
                .subscribe(monthDetails -> {
                    view.showProgress(Constants.ProgressType.NONE);
                    monthDetailsList = monthDetails;
                    setData(monthDetailsList);
                }, t -> error(t));
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return !monthDetailsList.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(monthDetailsList);
    }

    private void setData(ArrayList<MonthDetail> data) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String strMonth = dfs.getMonths()[month - 1];
        view.setTitleMonth(strMonth);
        view.setVacationPersons(prepareVacationPersonDHs(data));
    }

    private ArrayList<VacationPersonDH> prepareVacationPersonDHs(ArrayList<MonthDetail> data) {
        ArrayList<VacationPersonDH> result = new ArrayList<>();
        if(data != null && !data.isEmpty()) {
            for(MonthDetail monthDetail : data) {
                result.add(new VacationPersonDH(monthDetail));
            }
        }
        return result;
    }
}
