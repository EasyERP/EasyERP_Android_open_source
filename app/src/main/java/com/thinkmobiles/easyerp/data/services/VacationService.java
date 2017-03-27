package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.hr.dashboard.VacationStatistic;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

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
}
