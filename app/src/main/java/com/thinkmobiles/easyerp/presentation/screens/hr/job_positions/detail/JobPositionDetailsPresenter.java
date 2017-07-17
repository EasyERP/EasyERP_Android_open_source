package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions.detail;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.model.hr.job_positions.detail.JobPositionDetail;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Michael Soyma (Created on 3/15/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class JobPositionDetailsPresenter extends ContentPresenterHelper implements JobPositionDetailsContract.JobPositionDetailsPresenter {

    private JobPositionDetailsContract.JobPositionDetailsView view;
    private JobPositionDetailsContract.JobPositionDetailsModel model;

    private final String jobPositionId;
    private JobPositionDetail jobPositionDetail;

    public JobPositionDetailsPresenter(JobPositionDetailsContract.JobPositionDetailsView view, JobPositionDetailsContract.JobPositionDetailsModel model, String jobPositionId) {
        this.view = view;
        this.model = model;
        this.jobPositionId = jobPositionId;

        this.view.setPresenter(this);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(
                model.getJobPositionDetails(jobPositionId).subscribe(
                        jobPositionResponse -> {
                            view.showProgress(Constants.ProgressType.NONE);
                            setData(jobPositionDetail = jobPositionResponse);
                        }, t -> error(t)
                )
        );
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return jobPositionDetail != null;
    }

    @Override
    protected void retainInstance() {
        setData(jobPositionDetail);
    }

    private void setData(JobPositionDetail data) {
        setMain(data);
        setAssignees(data);
    }

    private void setMain(JobPositionDetail data) {
        view.displayJobName(data.name);
        view.displayExpectedInRecruitment(data.expectedRecruitment);
        //TODO: no department message
        view.displayDepartment(data.department != null ? data.department.name : "");
        view.displayStage(data.workflow.name);
        if (!TextUtils.isEmpty(data.requirements))
            view.displayRequirements(data.requirements);
    }

    private void setAssignees(JobPositionDetail data) {
        view.displayAssignType(data.whoCanRW);
        if (data.groups.owner != null && !TextUtils.isEmpty(data.groups.owner.login))
            view.displayTheOwner(data.groups.owner.login);
    }
}