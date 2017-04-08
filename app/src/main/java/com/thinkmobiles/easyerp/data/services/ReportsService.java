package com.thinkmobiles.easyerp.data.services;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface ReportsService {

    @GET
    Observable<JsonElement> getReports(@Url String url);
}
