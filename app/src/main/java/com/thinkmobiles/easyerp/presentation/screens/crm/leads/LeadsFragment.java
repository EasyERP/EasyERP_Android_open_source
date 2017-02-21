package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.LeadsAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.details.LeadDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */
@EFragment
public class LeadsFragment extends MasterFlowListSelectableFragment implements LeadsContract.LeadsView {

    private LeadsContract.LeadsPresenter presenter;

    @Bean
    protected LeadsRepository leadsRepository;
    @Bean
    protected LeadsAdapter leadsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new LeadsPresenter(this, leadsRepository);
    }

    @Override
    public void setPresenter(LeadsContract.LeadsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(leadsAdapter);
        leadsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(leadsAdapter.getItem(position), position));
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
    public void displayLeads(ArrayList<LeadDH> leadDHs, boolean needClear) {
        if (needClear)
            leadsAdapter.setListDH(leadDHs);
        else
            leadsAdapter.addListDH(leadDHs);
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        leadsAdapter.replaceSelectedItem(oldPosition, newPosition);
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
    public void openLeadDetailsScreen(String leadId) {
        if (leadId != null) {
            mActivity.replaceFragmentContentDetail(LeadDetailsFragment_.builder()
                    .leadId(leadId)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    public int getCountItemsNow() {
        return leadsAdapter.getItemCount();
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
