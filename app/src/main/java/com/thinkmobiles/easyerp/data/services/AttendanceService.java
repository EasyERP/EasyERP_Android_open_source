package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alex Michenko on 27.03.2017.
 */

public interface AttendanceService {

    @GET(Constants.GET_VACATION)
    Observable<ResponseGetTotalItems<MonthDetail>> getAttendanceDetails(@Query("year") int year, @Query("employee") String employee);

    @GET(Constants.GET_VACATION_YEARS)
    Observable<ArrayList<FilterItem>> getEnableYears();

}
