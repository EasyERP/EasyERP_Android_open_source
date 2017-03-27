package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.ResponseGetAttendanceDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public interface AttendanceDetailsContract {

    interface AttendanceDetailsView extends ContentView, BaseView<AttendanceDetailsPresenter> {
        void setYears(ArrayList<String> years);
        void setMonthDetails(int month, ArrayList<String> types);
    }

    interface AttendanceDetailsPresenter extends ContentPresenter {
        void selectYear();
    }

    interface AttendanceDetailsModel extends BaseModel {
        Observable<ResponseGetAttendanceDetails> getAttendanceDetails(int year, String id);
    }
}
