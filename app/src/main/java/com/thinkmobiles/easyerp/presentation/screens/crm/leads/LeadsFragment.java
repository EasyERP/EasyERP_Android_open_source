package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.LeadsAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.details.LeadDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class LeadsFragment extends SimpleListWithRefreshFragment implements LeadsContract.LeadsView {

    private LeadsContract.LeadsPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Bean
    protected LeadsRepository leadsRepository;
    @Bean
    protected LeadsAdapter leadsAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

    @ViewById
    protected AppCompatAutoCompleteTextView actSearch;

    @Bean
    protected SearchAdapter searchAdapter;

    protected MenuItem menuFilter;
    protected MenuItem menuContactName;
    protected MenuItem menuAssignedTo;
    protected MenuItem menuCreatedBy;
    protected MenuItem menuSource;
    protected MenuItem menuWorkflow;

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
        errorViewHelper.init(errorLayout, view -> presenter.subscribe());

        LinearLayoutManager llm = new LinearLayoutManager(mActivity);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadNextPage(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(leadsAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        leadsAdapter.setOnCardClickListener((view, position, viewType) ->
                presenter.selectItem(leadsAdapter.getItem(position), position)
        );

        actSearch.setAdapter(searchAdapter);
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

    @AfterTextChange(R.id.actSearch)
    protected void afterSearchChanged(Editable editable) {
        if (editable.length() > 1) {
            searchAdapter.getFilter().filter(editable.toString());
        }
    }

    @Override
    public void displayLeads(ArrayList<LeadDH> leadDHs, boolean needClear) {
        if (needClear)
            leadsAdapter.setListDH(leadDHs);
        else
            leadsAdapter.addListDH(leadDHs);
    }

    @Override
    public void showProgress(boolean isShow) {
        if (isShow) {
            errorViewHelper.hideError();
            displayProgress(true);
            swipeContainer.setRefreshing(false);
        } else {
            errorViewHelper.hideError();
            displayProgress(false);
            swipeContainer.setRefreshing(false);
        }
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        leadsAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void showEmptyState() {
        leadsAdapter.setListDH(new ArrayList<>());
        errorViewHelper.showErrorMsg(string_list_is_empty, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
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
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void onRefresh() {
        scrollListener.resetState();
        presenter.subscribe();
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

    @OptionsItem(R.id.menuFilterContactName)
    protected void clickContactName() {
        presenter.changeFilter(Constants.REQUEST_CODE_FILTER_CONTACT_NAME, getString(R.string.menu_filter_contact_name));
    }

    @OptionsItem(R.id.menuFilterStage)
    protected void clickStage() {
        presenter.changeFilter(Constants.REQUEST_CODE_FILTER_WORKFLOW, getString(R.string.menu_filter_stage));
    }

    @OptionsItem(R.id.menuFilterCreatedBy)
    protected void clickCreatedBy() {
        presenter.changeFilter(Constants.REQUEST_CODE_FILTER_CREATED_BY, getString(R.string.menu_filter_created_by));
    }

    @OptionsItem(R.id.menuFilterAssignedTo)
    protected void clickAssignedTo() {
        presenter.changeFilter(Constants.REQUEST_CODE_FILTER_ASSIGNED_TO, getString(R.string.menu_filter_assigned_to));
    }

    @OptionsItem(R.id.menuFilterSource)
    protected void clickSource() {
        presenter.changeFilter(Constants.REQUEST_CODE_FILTER_SOURCE, getString(R.string.menu_filter_source));
    }

    @OptionsItem(R.id.menuFilterRemoveAll)
    protected void clickRemoveAll() {
        presenter.removeAll();
    }

    @Override
    public void setContactNames(ArrayList<FilterDH> contactNames) {
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
        actSearch.setVisibility(isShow ? View.VISIBLE : View.GONE);
        menuFilter.setVisible(isShow);
    }

    @Override
    public void selectContactNameInFilters(boolean isSelected) {
        menuContactName.setChecked(isSelected);
    }

    @Override
    public void selectWorkflowInFilters(boolean isSelected) {
        menuWorkflow.setChecked(isSelected);
    }

    @Override
    public void selectAssignedToInFilters(boolean isSelected) {
        menuAssignedTo.setChecked(isSelected);
    }

    @Override
    public void selectCreatedByInFilters(boolean isSelected) {
        menuCreatedBy.setChecked(isSelected);
    }

    @Override
    public void selectSourceInFilters(boolean isSelected) {
        menuSource.setChecked(isSelected);
    }

    @Override
    public void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName) {
        actSearch.clearFocus();
        listRecycler.requestFocus();
        FilterDialogFragment dialogFragment = new FilterDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.KEY_FILTER_LIST, filterDHs);
        bundle.putString(Constants.KEY_FILTER_NAME, filterName);
        dialogFragment.setArguments(bundle);
        dialogFragment.setTargetFragment(this, requestCode);
        dialogFragment.show(getFragmentManager(), getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<FilterDH> filterDHs = data.getParcelableArrayListExtra(Constants.KEY_FILTER_LIST);
            presenter.filterByList(filterDHs, requestCode);
        } else {
            presenter.removeFilter(requestCode);
        }
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        this.menuFilter = menu.findItem(R.id.menuFilter_MB);
        this.menuContactName = menu.findItem(R.id.menuFilterContactName);
        this.menuAssignedTo = menu.findItem(R.id.menuFilterAssignedTo);
        this.menuCreatedBy = menu.findItem(R.id.menuFilterCreatedBy);
        this.menuSource = menu.findItem(R.id.menuFilterSource);
        this.menuWorkflow = menu.findItem(R.id.menuFilterStage);
    }

}
