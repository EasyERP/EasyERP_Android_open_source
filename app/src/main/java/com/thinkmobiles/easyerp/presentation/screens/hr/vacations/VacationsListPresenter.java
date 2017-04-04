package com.thinkmobiles.easyerp.presentation.screens.hr.vacations;

import android.util.Log;

import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalPresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationListDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lynx on 3/29/2017.
 */

public class VacationsListPresenter extends MasterAlphabeticalPresenterHelper implements VacationsListContract.VacationsListPresenter {

    private VacationsListContract.VacationsListView view;
    private VacationsListContract.VacationsListModel model;
    private ArrayList<String> months = new ArrayList<>();

    public VacationsListPresenter(VacationsListContract.VacationsListView view, VacationsListContract.VacationsListModel model) {
        this.view = view;
        this.model = model;

        selectedLetter = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        if (super.selectItem(months.get(position), position))
            view.openDetailsScreen(Integer.valueOf(selectedLetter), position + 1);
    }

    @Override
    protected boolean hasContent() {
        return !months.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(months);
    }

    @Override
    protected void loadPage(int page) {
        Log.d("myLogs", "Load page with selected letter " + selectedLetter);
        months = model.getMonths();
        totalItems = months.size();
        setData(months);
        view.showProgress(Constants.ProgressType.NONE);
    }

    @Override
    protected int getCountItems() {
        return months.size();
    }

    @Override
    protected AlphabeticalView getView() {
        return view;
    }

    @Override
    protected AlphabeticalModel getModel() {
        return model;
    }

    private void setData(ArrayList<String> data) {
        view.displaySelectedLetter(selectedLetter);
        ArrayList<VacationListDH> vacationListDHs = new ArrayList<>();
        if(data != null && !data.isEmpty()) {
           for(String m : data)  {
               VacationListDH dh = new VacationListDH(m);
               makeSelectedDHIfNeed(dh, data.indexOf(m));
               vacationListDHs.add(dh);
           }
        }
        selectFirstElementIfNeed(vacationListDHs);
        view.setDataList(vacationListDHs, true);
    }

    @Override
    protected void loadFilters() {

    }

    @Override
    public void setLetter(String letter) {
        super.setLetter(letter);
        view.openDetailsScreen(Integer.valueOf(selectedLetter), getSelectedItemPosition() + 1);
    }
}
