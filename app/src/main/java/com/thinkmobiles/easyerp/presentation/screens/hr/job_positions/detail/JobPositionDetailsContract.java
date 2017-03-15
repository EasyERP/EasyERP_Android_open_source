package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions.detail;

import com.thinkmobiles.easyerp.data.model.hr.job_positions.detail.JobPositionDetail;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/15/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface JobPositionDetailsContract {
    interface JobPositionDetailsView extends BaseView<JobPositionDetailsPresenter>, ContentView {
        void displayJobName(final String jobName);
        void displayDepartment(final String department);
        void displayExpectedInRecruitment(final int value);
        void displayStage(final String stage);
        void displayRequirements(final String requirements);
        void displayAssignType(final String whoCanRW);
        void displayTheOwner(final String ownerLogin);
    }
    interface JobPositionDetailsPresenter extends ContentPresenter {

    }
    interface JobPositionDetailsModel extends BaseModel {
        Observable<JobPositionDetail> getJobPositionDetails(final String jobPositionID);
    }
}
