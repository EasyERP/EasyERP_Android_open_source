package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ReportsService {

    @GET
    Observable<ResponseGetTotalItems<Report>> getReports(@Url String url);

    @GET(Constants.GET_REPORTS_FAVORITE)
    Observable<Void> favorite(@Path("reportId") final String reportId);

    @GET(Constants.GET_REPORTS_UNFAVORITE)
    Observable<Void> unfavorite(@Path("reportId") final String reportId);
}
