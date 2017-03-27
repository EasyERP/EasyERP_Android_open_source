package com.thinkmobiles.easyerp.presentation.screens.hr.applications;

import com.thinkmobiles.easyerp.domain.hr.ApplicationRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.ApplicationsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.hr.applications.details.ApplicationDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class ApplicationsListFragment extends MasterFilterableFragment implements ApplicationsListContract.ApplicationsListView {

    private ApplicationsListContract.ApplicationsListPresenter presenter;

    @Bean
    protected ApplicationRepository applicationRepository;
    @Bean
    protected ApplicationsAdapter applicationsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new ApplicationsListPresenter(this, applicationRepository);
    }

    @Override
    public void setPresenter(ApplicationsListContract.ApplicationsListPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Applications list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return applicationsAdapter;
    }

    @Override
    public void openApplicationDetail(String id) {
        if (id != null) {
            mActivity.replaceFragmentContentDetail(ApplicationDetailsFragment_.builder()
                    .applicationID(id)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }
}
