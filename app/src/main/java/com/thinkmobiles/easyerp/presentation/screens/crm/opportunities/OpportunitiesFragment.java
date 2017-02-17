package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OpportunitiesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OpportunitiesAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.details.OpportunityDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

    protected MenuItem menuFilter;

    @AfterInject
    @Override
    public void initPresenter() {
        new OpportunitiesPresenter(this, opportunitiesRepository);
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(opportunitiesAdapter);
        opportunitiesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(opportunitiesAdapter.getItem(position), position));

        actSearch.setOnItemClickListener((adapterView, view, i, l) ->
                presenter.filterByContactName(searchAdapter.getItem(i))
        );

        actSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN) {

                String name = actSearch.getText().toString();
                if (!name.trim().isEmpty())
                    presenter.filterBySearchContactName(name);

                hideKeyboard();
                actSearch.dismissDropDown();
                listRecycler.requestFocus();
                return true;
            }
            return false;
        });

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
        return R.menu.menu_opportunity_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        actSearch.dismissDropDown();
        this.menuFilter = menu.findItem(R.id.menuFilter_MB);
        presenter.refreshOptionMenu();
    }

    @OptionsItem({R.id.menuFilterCustomer, R.id.menuFilterSalesPerson, R.id.menuFilterName, R.id.menuFilterStage, R.id.menuFilterRemoveAll})
    void clickMenu(MenuItem item) {
        String filterName = item.getTitle().toString();
        switch (item.getItemId()) {
            case R.id.menuFilterCustomer:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_CUSTOMER, filterName);
                break;
            case R.id.menuFilterSalesPerson:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_ASSIGNED_TO, filterName);
                break;
            case R.id.menuFilterName:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_NAME, filterName);
                break;
            case R.id.menuFilterStage:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_WORKFLOW, filterName);
                break;
            case R.id.menuFilterRemoveAll:
                presenter.removeAll();
                break;
        }
    }

    @Override
    public void setNames(ArrayList<FilterDH> contactNames) {
        searchAdapter.setItems(contactNames);
    }

    @Override
    public void setTextToSearch(String text) {
        actSearch.setText(text);
        actSearch.setSelection(text.length());
        hideKeyboard();
    }

    @Override
    public void showFilters(boolean isShow) {
        actSearch.setVisibility(View.VISIBLE);
        menuFilter.setVisible(true);
    }

    @Override
    public void selectFilter(int pos, boolean isSelected) {
        menuFilter.getSubMenu().getItem(pos).setChecked(isSelected);
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
