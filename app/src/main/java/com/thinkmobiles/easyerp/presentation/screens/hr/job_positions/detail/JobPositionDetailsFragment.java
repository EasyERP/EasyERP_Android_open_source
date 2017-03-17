package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions.detail;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.hr.JobPositionRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * @author Michael Soyma (Created on 3/15/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class JobPositionDetailsFragment extends ContentFragment implements JobPositionDetailsContract.JobPositionDetailsView {

    private JobPositionDetailsContract.JobPositionDetailsPresenter presenter;

    @ViewById
    protected TextView tvTitle_FJPD;
    @ViewById
    protected EditText etJobName_FJPD;
    @ViewById
    protected EditText etExpectedInRecruitment_FJPD;
    @ViewById
    protected EditText etDepartment_FJPD;
    @ViewById
    protected EditText etStage_FJPD;
    @ViewById
    protected EditText etRequirements_FJPD;
    @ViewById
    protected EditText etOwner_FJPD;
    @ViewById
    protected RadioButton rbEveryone_FJPD;
    @ViewById
    protected RadioButton rbOwner_FJPD;
    @ViewById
    protected RadioButton rbOwnerUsers_FJPD;

    @FragmentArg
    protected String jobPositionID;

    @Bean
    protected JobPositionRepository jobPositionRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new JobPositionDetailsPresenter(this, jobPositionRepository, jobPositionID);
    }

    @Override
    public void setPresenter(JobPositionDetailsContract.JobPositionDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        getPresenter().subscribe();
    }

    @Override
    public String getScreenName() {
        return "Job Position details screen";
    }

    @Override
    public void displayJobName(String jobName) {
        tvTitle_FJPD.setText(jobName);
        etJobName_FJPD.setText(jobName);
    }

    @Override
    public void displayDepartment(String department) {
        etDepartment_FJPD.setText(department);
    }

    @Override
    public void displayExpectedInRecruitment(int value) {
        etExpectedInRecruitment_FJPD.setText(String.valueOf(value));
    }

    @Override
    public void displayStage(String stage) {
        etStage_FJPD.setText(stage);
    }

    @Override
    public void displayRequirements(String requirements) {
        etRequirements_FJPD.setText(requirements);
    }

    @Override
    public void displayAssignType(String whoCanRW) {
        switch (whoCanRW) {
            case "everyOne":
                rbEveryone_FJPD.setChecked(true);
                break;
            case "owner":
                rbOwner_FJPD.setChecked(true);
                break;
            case "group":
                rbOwnerUsers_FJPD.setChecked(true);
                break;
        }
    }

    @Override
    public void displayTheOwner(String ownerLogin) {
        etOwner_FJPD.setText(ownerLogin);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_job_position_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }
}
