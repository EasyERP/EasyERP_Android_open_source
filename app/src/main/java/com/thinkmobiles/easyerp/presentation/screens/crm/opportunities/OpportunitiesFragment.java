package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunitiesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 1/30/2017.
 */

@EFragment
public class OpportunitiesFragment extends MasterFilterableFragment implements OpportunitiesContract.OpportunitiesView {

    private OpportunitiesContract.OpportunitiesPresenter presenter;

    @Bean
    protected OpportunitiesRepository opportunitiesRepository;
    @Bean
    protected OpportunitiesAdapter opportunitiesAdapter;

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunitiesPresenter(this, opportunitiesRepository);
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return opportunitiesAdapter;
    }

    @Override
    public void openDetailsScreen(String opportunityID) {
        if (opportunityID != null) {
            mActivity.replaceFragmentContentDetail(OpportunityDetailsFragment_.builder()
                    .opportunityID(opportunityID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void setPresenter(OpportunitiesContract.OpportunitiesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Opportunity list screen";
    }
}
