package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunitiesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

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
    public void displayErrorState(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
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

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        super.optionsMenuInitialized(menu);
        presenter.buildOptionMenu();
    }

    @Override
    protected void onClickSearchSuggestion(FilterDH item) {
        presenter.filterBySearchItem(item);
    }

    @Override
    protected void onSubmitSearch(String text) {
        presenter.filterBySearchText(text);
    }

    @Override
    public void createMenuFilters(FilterHelper helper) {
        if (helper.isInitialized()) {
            menuFilters.setVisible(true);
            menuSearch.setVisible(true);
            suggestionAdapter.setItems(helper.getSearchableFilterList());
            helper.buildMenu(menuFilters.getSubMenu());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFilters:
            case R.id.menuSearch:
                return false;
            case R.id.menuFilterRemoveAll:
                presenter.removeAll();
                break;
            default:
                presenter.changeFilter(item.getItemId(), item.getTitle().toString());
                break;
        }
        return true;
    }

    @Override
    public void selectFilter(int id, boolean isSelected) {
        menuFilters.getSubMenu().getItem(id).setChecked(isSelected);
    }

    @Override
    public void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName) {
        showDialogFiltering(filterDHs, requestCode, filterName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<FilterDH> filterDHs = data.getParcelableArrayListExtra(Constants.KEY_FILTER_LIST);
            presenter.filterByList(filterDHs, requestCode);
        } else {
            presenter.removeFilter(requestCode);
        }
    }
}
