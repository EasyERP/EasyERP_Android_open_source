package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.details;

import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationPersonDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/29/2017.
 */

public interface VacationDetailsContract {
    interface VacationDetailsView extends BaseView<VacationDetailsPresenter>, ContentView {
        void setTitleMonth(String month);
        void setVacationPersons(ArrayList<VacationPersonDH> vacationPersonDHs);

        void displayDetails(MonthDetail monthDetail);
    }
    interface VacationDetailsPresenter extends ContentPresenter {
        void openDetails(MonthDetail model);
    }
    interface VacationDetailsModel extends BaseModel {
        Observable<ArrayList<MonthDetail>> getVacationDetails(int year, int month);
    }
}
