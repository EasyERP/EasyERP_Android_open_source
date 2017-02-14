package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseGetFilters;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterTypeQuery;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunitiesPresenter extends MasterFlowSelectablePresenterHelper<String, OpportunityDH> implements OpportunitiesContract.OpportunitiesPresenter {

    private OpportunitiesContract.OpportunitiesView view;
    private OpportunitiesContract.OpportunitiesModel model;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;
    private int totalItems = Integer.MAX_VALUE;
    private ArrayList<OpportunityListItem> opportunityItems = new ArrayList<>();

    private ArrayList<FilterDH> customer = new ArrayList<>();
    private ArrayList<FilterDH> salesPerson = new ArrayList<>();
    private ArrayList<FilterDH> workflow = new ArrayList<>();
    private ArrayList<FilterDH> name = new ArrayList<>();

    private FilterQuery.Builder queryBuilder;

    public OpportunitiesPresenter(OpportunitiesContract.OpportunitiesView view, OpportunitiesContract.OpportunitiesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        queryBuilder = new FilterQuery.Builder();

        view.setPresenter(this);
    }

    private void loadOpportunities(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredOpportunities(queryBuilder.build(), page)
                        .subscribe(responseGetOpportunities -> {
                                    currentPage = page;
                                    totalItems = responseGetOpportunities.total;
                                    saveData(responseGetOpportunities.data, needClear);
                                    if (opportunityItems.isEmpty()) {
                                        view.displayErrorState(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
                                    } else {
                                        view.showProgress(Constants.ProgressType.NONE);
                                        view.displayOpportunities(prepareOpportunitiesDHs(responseGetOpportunities.data, needClear), needClear);
                                    }
                                },
                                t -> {
                                    if (opportunityItems.isEmpty()) {
                                        view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                                    } else {
                                        view.displayErrorToast(t.getMessage());
                                    }
                                }));
    }

    private void loadOpportunitiesFilters() {
        compositeSubscription.add(model.getOpportunityFilters()
                .subscribe(responseGetLeadsFilters -> {
                    prepareFilterDHs(responseGetLeadsFilters);
                    view.showFilters(true);
                    view.setNames(name);
                }, t -> {
                    view.showFilters(false);
                    view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                }));
    }

    private void prepareFilterDHs(ResponseGetFilters responseGetFilters) {
        for (FilterItem leadItem : responseGetFilters.name) {
            FilterDH leadDH = new FilterDH(leadItem);
            name.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.salesPerson) {
            FilterDH leadDH = new FilterDH(leadItem);
            salesPerson.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.workflow) {
            FilterDH leadDH = new FilterDH(leadItem);
            workflow.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.customer) {
            FilterDH leadDH = new FilterDH(leadItem);
            customer.add(leadDH);
        }
    }


    private void saveData(final List<OpportunityListItem> list, boolean needClear) {
        if (needClear)
            this.opportunityItems.clear();
        this.opportunityItems.addAll(list);
    }

    @Override
    public void subscribe() {
        if(opportunityItems.isEmpty() || (name.isEmpty() && salesPerson.isEmpty() && workflow.isEmpty() && customer.isEmpty())) {
            getFirstPage();
            loadOpportunitiesFilters();
        } else {
            view.displayOpportunities(prepareOpportunitiesDHs(opportunityItems, true), true);
            view.showFilters(true);
            view.setNames(name);
        }
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadOpportunities(1);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadOpportunities(currentPage + 1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<OpportunityDH> prepareOpportunitiesDHs(ArrayList<OpportunityListItem> list, boolean needClear) {
        int position = 0;
        final ArrayList<OpportunityDH> result = new ArrayList<>();
        for (OpportunityListItem opportunityItem : list) {
            final OpportunityDH opportunityDH = new OpportunityDH(opportunityItem);
            makeSelectedDHIfNeed(opportunityDH, view, position++, needClear);
            result.add(opportunityDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    @Override
    public void selectItem(OpportunityDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openOpportunityDetailsScreen(dh.getId());
    }

    @Override
    public void filterByContactName(FilterDH filterDH) {
        for (FilterDH dh : name) {
            dh.selected = dh.equals(filterDH);
        }
        queryBuilder.forName().removeAll().add(filterDH.id);
        view.setTextToSearch(filterDH.name);
        view.selectFilter(3, true);
        getFirstPage();
    }

    @Override
    public void filterBySearchContactName(String name) {
        FilterTypeQuery contactQuery = queryBuilder.forName();
        contactQuery.removeAll();
        for (FilterDH dh : this.name) {
            if(dh.name.toLowerCase().contains(name)) {
                contactQuery.add(dh.id);
                dh.selected = true;
            } else {
                dh.selected = false;
            }
        }
        view.selectFilter(3, true);
        getFirstPage();
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_CUSTOMER:
                customer = filterDHs;
                queryBuilder.forCustomer().setList(customer, isVisible -> view.selectFilter(0, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                salesPerson = filterDHs;
                queryBuilder.forAssignedTo().setList(salesPerson, isVisible -> view.selectFilter(1, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                workflow = filterDHs;
                queryBuilder.forWorkflow().setList(workflow, isVisible -> view.selectFilter(2, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_NAME:
                name = filterDHs;
                queryBuilder.forName().setList(name, isVisible -> view.selectFilter(3, isVisible));
                view.setTextToSearch("");
                break;
            default:
                return;
        }
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_NAME:
                clearFilter(name, queryBuilder.forName(), 3);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                clearFilter(workflow, queryBuilder.forWorkflow(), 2);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                clearFilter(salesPerson, queryBuilder.forAssignedTo(), 1);
                break;
            case Constants.REQUEST_CODE_FILTER_CUSTOMER:
                clearFilter(customer, queryBuilder.forCustomer(), 0);
                break;
        }
        getFirstPage();
    }


    private void clearFilter(ArrayList<FilterDH> filterDHs, FilterTypeQuery typeQuery, int position) {
        for (FilterDH dh : filterDHs) {
            dh.selected = false;
        }
        typeQuery.removeAll();
        view.selectFilter(position, false);
    }

    @Override
    public void changeFilter(int requestCode, String filterName) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_NAME:
                view.showFilterDialog(name, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                view.showFilterDialog(workflow, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                view.showFilterDialog(salesPerson, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_CUSTOMER:
                view.showFilterDialog(customer, requestCode, filterName);
                break;
        }
    }

    @Override
    public void refreshOptionMenu() {
        view.selectFilter(0, queryBuilder.forCustomer().getValues() != null);
        view.selectFilter(1, queryBuilder.forAssignedTo().getValues() != null);
        view.selectFilter(2, queryBuilder.forWorkflow().getValues() != null);
        view.selectFilter(3, queryBuilder.forName().getValues() != null);
    }

    @Override
    public void removeAll() {
        clearFilter(name, queryBuilder.forName(), 3);
        clearFilter(workflow, queryBuilder.forWorkflow(), 2);
        clearFilter(salesPerson, queryBuilder.forAssignedTo(), 1);
        clearFilter(customer, queryBuilder.forCustomer(), 0);
        getFirstPage();
    }
}
