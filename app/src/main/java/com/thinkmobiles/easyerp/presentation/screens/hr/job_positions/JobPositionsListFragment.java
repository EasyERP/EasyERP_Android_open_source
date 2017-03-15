package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions;

import com.thinkmobiles.easyerp.domain.hr.JobPositionRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.JobPositionsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class JobPositionsListFragment extends MasterSelectableFragment implements JobPositionsListContract.JobPositionsListView {

    private JobPositionsListContract.JobPositionsListPresenter presenter;

    @Bean
    protected JobPositionRepository jobPositionRepository;
    @Bean
    protected JobPositionsAdapter jobPositionsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new JobPositionsPresenter(this, jobPositionRepository);
    }

    @Override
    public void setPresenter(JobPositionsListContract.JobPositionsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Job Positions list screen";
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return jobPositionsAdapter;
    }

    @Override
    public void openJobPositionDetail(String id) {
        if (id != null) {
            //TODO open Job Position detail screen
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
