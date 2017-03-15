package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface JobPositionService {

    @GET(Constants.GET_JOB_POSITIONS)
    Observable<ResponseGetTotalItems<JobPosition>> getJobPositions(@Query("viewType") String viewType,
                                                                   @Query("count") int count,
                                                                   @Query("contentType") String contentType,
                                                                   @Query("page") int page);
}
