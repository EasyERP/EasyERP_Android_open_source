package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunitiesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/30/2017.
 */

@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class OpportunitiesFragment extends MasterFlowListFragment implements OpportunitiesContract.OpportunitiesView {

    private OpportunitiesContract.OpportunitiesPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Bean
    protected OpportunitiesRepository opportunitiesRepository;
    @Bean
    protected OpportunitiesAdapter opportunitiesAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;


    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunitiesPresenter(this, opportunitiesRepository);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadOpportunities(page);
            }
        };

        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(opportunitiesAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        opportunitiesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(opportunitiesAdapter.getItem(position), position));

        loadWithProgressBar();
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.subscribe();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void displayOpportunities(ArrayList<OpportunityDH> opportunityDHs, boolean needClear) {
        errorViewHelper.hideError();
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            opportunitiesAdapter.setListDH(opportunityDHs);
        else opportunitiesAdapter.addListDH(opportunityDHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else
            Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openOpportunityDetailsScreen(String opportunityID) {
        Log.d("myLogs", "Open opportunity details. ID = " + opportunityID);
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

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) presenter.unsubscribe();
    }
}
