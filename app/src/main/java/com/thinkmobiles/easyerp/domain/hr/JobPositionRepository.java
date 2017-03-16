package com.thinkmobiles.easyerp.domain.hr;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.detail.JobPositionDetail;
import com.thinkmobiles.easyerp.data.services.JobPositionService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.job_positions.JobPositionsListContract;
import com.thinkmobiles.easyerp.presentation.screens.hr.job_positions.detail.JobPositionDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class JobPositionRepository extends NetworkRepository implements JobPositionsListContract.JobPositionsListModel, JobPositionDetailsContract.JobPositionDetailsModel {

    private JobPositionService jobPositionService;

    public JobPositionRepository() {
        this.jobPositionService = Rest.getInstance().getJobPositionService();
    }

    @Override
    public Observable<ResponseGetTotalItems<JobPosition>> getJobPositions(int page) {
        return getNetworkObservable(jobPositionService.getJobPositions("list", Constants.COUNT_LIST_ITEMS, "JobPositions", page));
    }

    @Override
    public Observable<JobPositionDetail> getJobPositionDetails(String jobPositionID) {
        return getNetworkObservable(jobPositionService.getJobPositionDetails(jobPositionID, "form"));
    }
}
