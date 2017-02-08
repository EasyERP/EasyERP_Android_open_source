package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.ResponseGetLeadsFilters;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterTypeQuery;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter extends MasterFlowSelectablePresenterHelper<String, LeadDH> implements LeadsContract.LeadsPresenter {

    private LeadsContract.LeadsView view;
    private LeadsContract.LeadsModel model;
    private CompositeSubscription compositeSubscription;

    private ArrayList<FilterDH> contactName = new ArrayList<>();
    private ArrayList<FilterDH> assignedTo = new ArrayList<>();
    private ArrayList<FilterDH> createdBy = new ArrayList<>();
    private ArrayList<FilterDH> workflow = new ArrayList<>();
    private ArrayList<FilterDH> source = new ArrayList<>();

    private FilterQuery.Builder queryBuilder;
    private int totalItems = Integer.MAX_VALUE;

    private ArrayList<LeadItem> leadItems = new ArrayList<>();
    private int currentPage = 1;

    public LeadsPresenter(LeadsContract.LeadsView view, LeadsContract.LeadsModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        queryBuilder = new FilterQuery.Builder();

        view.setPresenter(this);
    }

    @Override
    public void loadNextPage(int page) {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(true);
        loadLeads(page);
    }

    private void getFirstPage() {
        view.showProgress(true);
        refresh();
    }

    private void loadLeads(int page) {
        final boolean isFirst = page == 1;
        compositeSubscription.add(
                model.getFilteredLeads(queryBuilder.build(), page)
                        .subscribe(
                                responseGetLeads -> {
                                    currentPage = page;
                                    if(isFirst) leadItems.clear();
                                    leadItems.addAll(responseGetLeads.data);

                                    totalItems = responseGetLeads.total;
                                    view.showProgress(false);
                                    if(isFirst && responseGetLeads.data.isEmpty()) {
                                        view.showEmptyState();
                                    } else {
                                        view.displayLeads(prepareLeadDHs(responseGetLeads.data), isFirst);
                                    }
                                }, t -> {
                                    view.showProgress(false);
                                    view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                                }
                        ));
    }

    private void loadLeadsFilters() {
        compositeSubscription.add(model.getLeadFilters()
                .subscribe(responseGetLeadsFilters -> {
                    prepareFilterDHs(responseGetLeadsFilters);
                    view.showFilters(true);
                    view.setContactNames(contactName);
                }, t -> {
                    view.showFilters(false);
                    view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                }));
    }

    @Override
    public void selectItem(LeadDH dh, int position) {
        if(super.selectItem(dh, position, view))
            view.openLeadDetailsScreen(dh.getId());
    }

    @Override
    public void subscribe() {
        if(leadItems.isEmpty() || (contactName.isEmpty() && source.isEmpty() && assignedTo.isEmpty() && workflow.isEmpty() && createdBy.isEmpty())) {
            getFirstPage();
            loadLeadsFilters();
        } else {
            view.displayLeads(prepareLeadDHs(leadItems), true);
            view.showFilters(true);
            view.setContactNames(contactName);
        }
    }

    @Override
    public void refresh() {
        loadLeads(1);
    }

    @Override
    public void refreshOptionMenu() {
        filterList(contactName, queryBuilder.forContactName(), isVisible -> view.selectContactNameInFilters(isVisible));
        filterList(workflow, queryBuilder.forWorkflow(), isVisible -> view.selectWorkflowInFilters(isVisible));
        filterList(assignedTo, queryBuilder.forAssignedTo(), isVisible -> view.selectAssignedToInFilters(isVisible));
        filterList(createdBy, queryBuilder.forCreatedBy(), isVisible -> view.selectCreatedByInFilters(isVisible));
        filterList(source, queryBuilder.forSource(), isVisible -> view.selectSourceInFilters(isVisible));
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<LeadDH> prepareLeadDHs(ArrayList<LeadItem> leadItems) {
        int position = 0;
        final ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : leadItems) {
            final LeadDH leadDH = new LeadDH(leadItem);
            makeSelectedDHIfNeed(leadDH, view, position++, true);
            result.add(leadDH);
        }
        return result;
    }

    private void prepareFilterDHs(ResponseGetLeadsFilters responseGetLeadsFilters) {
        for (FilterItem leadItem : responseGetLeadsFilters.contactName) {
            FilterDH leadDH = new FilterDH(leadItem);
            contactName.add(leadDH);
        }
        for (FilterItem leadItem : responseGetLeadsFilters.salesPerson) {
            FilterDH leadDH = new FilterDH(leadItem);
            assignedTo.add(leadDH);
        }
        for (FilterItem leadItem : responseGetLeadsFilters.createdBy) {
            FilterDH leadDH = new FilterDH(leadItem);
            createdBy.add(leadDH);
        }
        for (FilterItem leadItem : responseGetLeadsFilters.workflow) {
            FilterDH leadDH = new FilterDH(leadItem);
            workflow.add(leadDH);
        }
        for (FilterItem leadItem : responseGetLeadsFilters.source) {
            FilterDH leadDH = new FilterDH(leadItem);
            source.add(leadDH);
        }
    }

    @Override
    public void removeAll() {
        clearFilter(contactName, queryBuilder.forContactName(), isVisible -> view.selectContactNameInFilters(isVisible));
        clearFilter(workflow, queryBuilder.forWorkflow(), isVisible -> view.selectWorkflowInFilters(isVisible));
        clearFilter(assignedTo, queryBuilder.forAssignedTo(), isVisible -> view.selectAssignedToInFilters(isVisible));
        clearFilter(createdBy, queryBuilder.forCreatedBy(), isVisible -> view.selectCreatedByInFilters(isVisible));
        clearFilter(source, queryBuilder.forSource(), isVisible -> view.selectSourceInFilters(isVisible));
        view.setTextToSearch("");
        getFirstPage();
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void filterByContactName(FilterDH filterDH) {
        for (FilterDH dh : contactName) {
            dh.selected = dh.equals(filterDH);
        }
        queryBuilder.forContactName().removeAll().add(filterDH.id);
        view.setTextToSearch(filterDH.name);
        view.selectContactNameInFilters(true);
        getFirstPage();
    }

    public void filterBySearchContactName(String name) {
        FilterTypeQuery contactQuery = queryBuilder.forContactName();
        contactQuery.removeAll();
        for (FilterDH dh : contactName) {
            if(dh.name.toLowerCase().contains(name)) {
                contactQuery.add(dh.id);
                dh.selected = true;
            } else {
                dh.selected = false;
            }
        }
        view.selectContactNameInFilters(true);
        getFirstPage();
    }

    @Override
    public void changeFilter(int requestCode, String filterName) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_CONTACT_NAME:
                view.showFilterDialog(contactName, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                view.showFilterDialog(workflow, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                view.showFilterDialog(assignedTo, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_CREATED_BY:
                view.showFilterDialog(createdBy, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_SOURCE:
                view.showFilterDialog(source, requestCode, filterName);
                break;
        }
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_CONTACT_NAME:
                contactName = filterDHs;
                filterList(contactName, queryBuilder.forContactName(), isVisible -> view.selectContactNameInFilters(isVisible));
                view.setTextToSearch("");
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                workflow = filterDHs;
                filterList(workflow, queryBuilder.forWorkflow(), isVisible -> view.selectWorkflowInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                assignedTo = filterDHs;
                filterList(assignedTo, queryBuilder.forAssignedTo(), isVisible -> view.selectAssignedToInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_CREATED_BY:
                createdBy = filterDHs;
                filterList(createdBy, queryBuilder.forCreatedBy(), isVisible -> view.selectCreatedByInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_SOURCE:
                source = filterDHs;
                filterList(source, queryBuilder.forSource(), isVisible -> view.selectSourceInFilters(isVisible));
                break;
            default:
                return;
        }
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_CONTACT_NAME:
                clearFilter(contactName, queryBuilder.forContactName(), isVisible -> view.selectContactNameInFilters(isVisible));
                view.setTextToSearch("");
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                clearFilter(workflow, queryBuilder.forWorkflow(), isVisible -> view.selectWorkflowInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                clearFilter(assignedTo, queryBuilder.forAssignedTo(), isVisible -> view.selectAssignedToInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_CREATED_BY:
                clearFilter(createdBy, queryBuilder.forCreatedBy(), isVisible -> view.selectCreatedByInFilters(isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_SOURCE:
                clearFilter(source, queryBuilder.forSource(), isVisible -> view.selectSourceInFilters(isVisible));
                break;
            default:
                return;
        }
        getFirstPage();
    }

    private void clearFilter(ArrayList<FilterDH> filterDHs, FilterTypeQuery typeQuery, VisibilityCallback callback) {
        unselectAll(filterDHs);
        typeQuery.removeAll();
        callback.setVisibility(false);
    }

    private void unselectAll(ArrayList<FilterDH> list) {
        for (FilterDH dh : list) {
            dh.selected = false;
        }
    }

    private void filterList(ArrayList<FilterDH> filterDHs, FilterTypeQuery typeQuery, VisibilityCallback callback) {
        typeQuery.removeAll();
        for (FilterDH dh : filterDHs) {
            if(dh.selected) {
                typeQuery.add(dh.id);
            }
        }
        callback.setVisibility(typeQuery.getValues() != null);
    }

    private interface VisibilityCallback {
        void setVisibility(boolean isVisible);
    }
}
