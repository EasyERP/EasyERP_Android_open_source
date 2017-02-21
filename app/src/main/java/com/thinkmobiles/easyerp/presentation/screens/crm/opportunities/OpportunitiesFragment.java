package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunitiesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/30/2017.
 */

@EFragment
public class OpportunitiesFragment extends MasterFlowListSelectableFragment implements OpportunitiesContract.OpportunitiesView {

    private OpportunitiesContract.OpportunitiesPresenter presenter;

    @Bean
    protected OpportunitiesRepository opportunitiesRepository;
    @Bean
    protected OpportunitiesAdapter opportunitiesAdapter;


    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunitiesPresenter(this, opportunitiesRepository);
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(opportunitiesAdapter);
        opportunitiesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(opportunitiesAdapter.getItem(position), position));

        presenter.subscribe();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    public void displayOpportunities(ArrayList<OpportunityDH> opportunityDHs, boolean needClear) {
        if (needClear)
            opportunitiesAdapter.setListDH(opportunityDHs);
        else opportunitiesAdapter.addListDH(opportunityDHs);
    }

    @Override
    public void displayErrorState(ErrorType errorType) {
        showErrorState(errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void openOpportunityDetailsScreen(String opportunityID) {
        if (opportunityID != null) {
            mActivity.replaceFragmentContentDetail(OpportunityDetailsFragment_.builder()
                    .opportunityID(opportunityID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public int getCountItemsNow() {
        return opportunitiesAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        opportunitiesAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void setPresenter(OpportunitiesContract.OpportunitiesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
