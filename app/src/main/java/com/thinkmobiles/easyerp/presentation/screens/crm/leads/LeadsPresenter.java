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
import com.thinkmobiles.easyerp.presentation.utils.FilterQuery;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter extends MasterFlowSelectablePresenterHelper<String> implements LeadsContract.LeadsPresenter {

    private LeadsContract.LeadsView view;
    private LeadsContract.LeadsModel model;
    private CompositeSubscription compositeSubscription;

    private ArrayList<FilterDH> contactName = new ArrayList<>();
    private ArrayList<FilterDH> assignedTo = new ArrayList<>();
    private ArrayList<FilterDH> createdBy = new ArrayList<>();
    private ArrayList<FilterDH> workflow = new ArrayList<>();
    private ArrayList<FilterDH> source = new ArrayList<>();

    private FilterQuery.Builder queryBuilder;
    private boolean isEnabledFilters;
    private int totalItems = Integer.MAX_VALUE;

    public LeadsPresenter(LeadsContract.LeadsView view, LeadsContract.LeadsModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();
        queryBuilder = new FilterQuery.Builder();

        view.setPresenter(this);
    }

    @Override
    public void loadNextPage(int page) {
        if (view.getCountItemsNow() == totalItems) {
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
                                    totalItems = responseGetLeads.total;
                                    view.showProgress(false);
                                    if (isFirst && responseGetLeads.data.isEmpty()) {
                                        view.showEmptyState();
                                    } else {
                                        view.displayLeads(prepareLeadDHs(responseGetLeads, isFirst), isFirst);
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
                    isEnabledFilters = true;
                    view.showFilters();
                    view.setContactNames(contactName);
                }, Throwable::printStackTrace));
    }

    @Override
    public boolean isEnabledFilters() {
        return isEnabledFilters;
    }

    @Override
    public void selectItemLead(LeadDH leadDh, int position) {
        if (position != getSelectedItemPosition()) {
            view.changeSelectedItem(getSelectedItemPosition(), position);
            setSelectedInfo(position, leadDh.getId());
            view.openLeadDetailsScreen(leadDh.getId());
        }
    }

    @Override
    public void subscribe() {
        getFirstPage();
        loadLeadsFilters();
    }

    @Override
    public void refresh() {
        setSelectedInfo(-1, getSelectedItemId());
        loadLeads(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    private ArrayList<LeadDH> prepareLeadDHs(ResponseGetLeads responseGetLeads, boolean isFirstPage) {
        int position = 0;
        final ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : responseGetLeads.data) {
            final LeadDH leadDH = new LeadDH(leadItem);
            makeSelectedDHIfNeed(leadDH, view, position++, isFirstPage);
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
        unselectAll(contactName);
        unselectAll(workflow);
        unselectAll(createdBy);
        unselectAll(assignedTo);
        unselectAll(source);
        view.setTextToSearch("");
        view.selectContactNameInFilters(false);
        view.selectWorkflowInFilters(false);
        view.selectAssignedToInFilters(false);
        view.selectSourceInFilters(false);
        view.selectCreatedByInFilters(false);
        queryBuilder.removeAllContactNames()
                .removeAllWorkflows()
                .removeAllAssignedTo()
                .removeAllCreatedBy()
                .removeAllSource();
        getFirstPage();
    }

    @Override
    public void filterByContactName(FilterDH filterDH) {
        for (FilterDH dh : contactName) {
            dh.selected = dh.equals(filterDH);
        }
        queryBuilder.setSingleContactName(filterDH.id);
        view.setTextToSearch(filterDH.name);
        view.selectContactNameInFilters(true);
        getFirstPage();
    }

    public void filterBySearchContactName(String name) {
        queryBuilder.removeAllContactNames()
                .createContactNameKey();
        for (FilterDH dh : contactName) {
            if (dh.name.toLowerCase().contains(name)) {
                queryBuilder.addContactName(dh.id);
                dh.selected = true;
            }
        }
        view.selectContactNameInFilters(true);
        getFirstPage();
    }

    @Override
    public void filterByListContactNames(ArrayList<FilterDH> filterDHs) {
        contactName = filterDHs;
        queryBuilder.removeAllContactNames();
        for (FilterDH dh : contactName) {
            if (dh.selected) {
                queryBuilder.addContactName(dh.id);
            }
        }
        view.setTextToSearch("");
        boolean needCheckFilterContactName = queryBuilder.build().contactNames != null;
        view.selectContactNameInFilters(needCheckFilterContactName);
        getFirstPage();
    }

    private void unselectAll(ArrayList<FilterDH> list) {
        for (FilterDH dh : list) {
            dh.selected = false;
        }
    }

    @Override
    public void removeFilterContactName() {
        unselectAll(contactName);
        queryBuilder.removeAllContactNames();
        view.setTextToSearch("");
        view.selectContactNameInFilters(false);
        getFirstPage();
    }

    @Override
    public void changeFilter(int requestCode) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_CONTACT_NAME:
                view.showFilterDialog(contactName, requestCode);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                view.showFilterDialog(workflow, requestCode);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                view.showFilterDialog(assignedTo, requestCode);
                break;
            case Constants.REQUEST_CODE_FILTER_CREATED_BY:
                view.showFilterDialog(createdBy, requestCode);
                break;
            case Constants.REQUEST_CODE_FILTER_SOURCE:
                view.showFilterDialog(source, requestCode);
                break;
        }
    }

    @Override
    public void filterByListWorkflow(ArrayList<FilterDH> filterDHs) {
        workflow = filterDHs;
        queryBuilder.removeAllWorkflows();
        for (FilterDH dh : workflow) {
            if (dh.selected) {
                queryBuilder.addWorkflow(dh.id);
            }
        }
        boolean needCheckFilter = queryBuilder.build().workflow != null;
        view.selectWorkflowInFilters(needCheckFilter);
        getFirstPage();
    }

    @Override
    public void removeFilterWorkflow() {
        unselectAll(workflow);
        queryBuilder.removeAllWorkflows();
        view.selectWorkflowInFilters(false);
        getFirstPage();
    }

    @Override
    public void filterByListAssignedTo(ArrayList<FilterDH> filterDHs) {
        assignedTo = filterDHs;
        queryBuilder.removeAllAssignedTo();
        for (FilterDH dh : assignedTo) {
            if (dh.selected) {
                queryBuilder.addAssignedTo(dh.id);
            }
        }
        boolean needCheckFilter = queryBuilder.build().assignedTo != null;
        view.selectAssignedToInFilters(needCheckFilter);
        getFirstPage();
    }

    @Override
    public void removeFilterAssignedTo() {
        unselectAll(assignedTo);
        queryBuilder.removeAllAssignedTo();
        view.selectAssignedToInFilters(false);
        getFirstPage();

    }

    @Override
    public void filterByListCreatedBy(ArrayList<FilterDH> filterDHs) {
        createdBy = filterDHs;
        queryBuilder.removeAllCreatedBy();
        for (FilterDH dh : createdBy) {
            if (dh.selected) {
                queryBuilder.addCreatedBy(dh.id);
            }
        }
        boolean needCheckFilter = queryBuilder.build().createdBy != null;
        view.selectCreatedByInFilters(needCheckFilter);
        getFirstPage();
    }

    @Override
    public void removeFilterCreatedBy() {
        unselectAll(createdBy);
        queryBuilder.removeAllCreatedBy();
        view.selectCreatedByInFilters(false);
        getFirstPage();

    }

    @Override
    public void filterByListSource(ArrayList<FilterDH> filterDHs) {
        source = filterDHs;
        queryBuilder.removeAllSource();
        for (FilterDH dh : source) {
            if (dh.selected) {
                queryBuilder.addSource(dh.id);
            }
        }
        boolean needCheckFilter = queryBuilder.build().source != null;
        view.selectSourceInFilters(needCheckFilter);
        getFirstPage();
    }

    @Override
    public void removeFilterSource() {
        unselectAll(source);
        queryBuilder.removeAllSource();
        view.selectSourceInFilters(false);
        getFirstPage();
    }
}
