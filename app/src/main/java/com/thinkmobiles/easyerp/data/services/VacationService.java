package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.VacationStatistic;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface VacationService {

    @GET(Constants.GET_VACATION_BY_STATISTIC)
    Observable<VacationStatistic> getVacationByStatistic(@Query("month") final int month, @Query("year") final int year);

    @GET(Constants.GET_VACATION_YEARS)
    Observable<ArrayList<FilterItem>> getVacationYears();

    @GET(Constants.GET_VACATION)
    Observable<ArrayList<MonthDetail>> getVacationDetails(@Query("viewType") final String viewType,
                                                          @Query("page") final int page,
                                                          @Query("count") final int count,
                                                          @Query("contentType") final String contentType,
                                                          @Query("year") final int year,
                                                          @Query("month") final int month);
}
