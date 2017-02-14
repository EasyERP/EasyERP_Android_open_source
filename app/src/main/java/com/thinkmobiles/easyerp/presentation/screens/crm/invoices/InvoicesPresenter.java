package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseGetFilters;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterTypeQuery;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class InvoicesPresenter extends MasterFlowSelectablePresenterHelper<String, InvoiceDH> implements InvoicesContract.InvoicesPresenter {

    private InvoicesContract.InvoicesView view;
    private InvoicesContract.InvoicesModel model;

    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;
    private int totalItems;
    private ArrayList<Invoice> invoices = new ArrayList<>();

    private ArrayList<FilterDH> supplier = new ArrayList<>();
    private ArrayList<FilterDH> salesPerson = new ArrayList<>();
    private ArrayList<FilterDH> workflow = new ArrayList<>();
    private ArrayList<FilterDH> project = new ArrayList<>();

    private FilterQuery.Builder queryBuilder;

    public InvoicesPresenter(InvoicesContract.InvoicesView view, InvoicesContract.InvoicesModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();
        this.queryBuilder = new FilterQuery.Builder();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if(invoices.isEmpty() || (project.isEmpty() && salesPerson.isEmpty() && workflow.isEmpty() && supplier.isEmpty())) {
            getFirstPage();
            loadInvoiceFilters();
        } else {
            view.displayInvoices(prepareInvoiceDHs(invoices, true), true);
            view.showFilters(true);
            view.setCustomers(supplier);
        }
        if (invoices.size() == 0) {
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
        } else view.displayInvoices(prepareInvoiceDHs(invoices, true), true);
    }

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadInvoices(1);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadInvoices(currentPage + 1);
    }

    private void loadInvoiceFilters() {
        compositeSubscription.add(model.getInvoiceFilters()
                .subscribe(responseGetLeadsFilters -> {
                    prepareFilterDHs(responseGetLeadsFilters);
                    view.showFilters(true);
                    view.setCustomers(supplier);
                }, t -> {
                    view.showFilters(false);
                    view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                }));
    }

    private void prepareFilterDHs(ResponseGetFilters responseGetFilters) {
        for (FilterItem leadItem : responseGetFilters.project) {
            FilterDH leadDH = new FilterDH(leadItem);
            project.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.salesPerson) {
            FilterDH leadDH = new FilterDH(leadItem);
            salesPerson.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.workflow) {
            FilterDH leadDH = new FilterDH(leadItem);
            workflow.add(leadDH);
        }
        for (FilterItem leadItem : responseGetFilters.supplier) {
            FilterDH leadDH = new FilterDH(leadItem);
            supplier.add(leadDH);
        }
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void loadInvoices(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredInvoices(queryBuilder.build(), page).subscribe(
                        responseGetInvoice -> {
                            currentPage = page;
                            totalItems = responseGetInvoice.total;
                            saveData(responseGetInvoice.data, needClear);
                            if (invoices.isEmpty()) {
                                view.displayErrorState(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
                            } else {
                                view.showProgress(Constants.ProgressType.NONE);
                                view.displayInvoices(prepareInvoiceDHs(responseGetInvoice.data, needClear), needClear);
                            }
                        },
                        throwable -> {
                            if (invoices.isEmpty()) {
                                view.displayErrorState(throwable.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                            } else {
                                view.displayErrorToast(throwable.getMessage());
                            }
                        }
                )
        );
    }

    private void saveData(final List<Invoice> invoices, boolean needClear) {
        if (needClear)
            this.invoices.clear();
        this.invoices.addAll(invoices);
    }

    @Override
    public void selectItem(InvoiceDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openInvoiceDetailsScreen(dh.getId());
    }

    private ArrayList<InvoiceDH> prepareInvoiceDHs(final List<Invoice> invoices, boolean needClear) {
        int position = 0;
        final ArrayList<InvoiceDH> result = new ArrayList<>();
        for (Invoice invoice : invoices) {
            final InvoiceDH invoiceDH = new InvoiceDH(invoice);
            makeSelectedDHIfNeed(invoiceDH, view, position++, needClear);
            result.add(invoiceDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    @Override
    public void filterByContactName(FilterDH filterDH) {
        for (FilterDH dh : supplier) {
            dh.selected = dh.equals(filterDH);
        }
        queryBuilder.forSupplier().removeAll().add(filterDH.id);
        view.setTextToSearch(filterDH.name);
        view.selectFilter(3, true);
        getFirstPage();
    }

    @Override
    public void filterBySearchContactName(String name) {
        FilterTypeQuery contactQuery = queryBuilder.forSupplier();
        contactQuery.removeAll();
        for (FilterDH dh : supplier) {
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
                supplier = filterDHs;
                queryBuilder.forSupplier().setList(supplier, isVisible -> view.selectFilter(3, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                salesPerson = filterDHs;
                queryBuilder.forAssignedTo().setList(salesPerson, isVisible -> view.selectFilter(0, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                workflow = filterDHs;
                queryBuilder.forWorkflow().setList(workflow, isVisible -> view.selectFilter(2, isVisible));
                break;
            case Constants.REQUEST_CODE_FILTER_PROJECT:
                project = filterDHs;
                queryBuilder.forProject().setList(project, isVisible -> view.selectFilter(1, isVisible));
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
            case Constants.REQUEST_CODE_FILTER_PROJECT:
                clearFilter(project, queryBuilder.forName(), 1);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                clearFilter(workflow, queryBuilder.forWorkflow(), 2);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                clearFilter(salesPerson, queryBuilder.forAssignedTo(), 0);
                break;
            case Constants.REQUEST_CODE_FILTER_CUSTOMER:
                clearFilter(supplier, queryBuilder.forCustomer(), 3);
                break;
        }
        getFirstPage();
    }

    @Override
    public void changeFilter(int requestCode, String filterName) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_FILTER_PROJECT:
                view.showFilterDialog(project, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_WORKFLOW:
                view.showFilterDialog(workflow, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_ASSIGNED_TO:
                view.showFilterDialog(salesPerson, requestCode, filterName);
                break;
            case Constants.REQUEST_CODE_FILTER_CUSTOMER:
                view.showFilterDialog(supplier, requestCode, filterName);
                break;
        }
    }

    @Override
    public void refreshOptionMenu() {
        view.selectFilter(1, queryBuilder.forProject().getValues() != null);
        view.selectFilter(0, queryBuilder.forAssignedTo().getValues() != null);
        view.selectFilter(2, queryBuilder.forWorkflow().getValues() != null);
        view.selectFilter(3, queryBuilder.forSupplier().getValues() != null);
    }

    @Override
    public void removeAll() {
        clearFilter(supplier, queryBuilder.forSupplier(), 3);
        clearFilter(workflow, queryBuilder.forWorkflow(), 2);
        clearFilter(salesPerson, queryBuilder.forAssignedTo(), 0);
        clearFilter(project, queryBuilder.forProject(), 1);
        getFirstPage();
    }

    private void clearFilter(ArrayList<FilterDH> filterDHs, FilterTypeQuery typeQuery, int position) {
        for (FilterDH dh : filterDHs) {
            dh.selected = false;
        }
        typeQuery.removeAll();
        view.selectFilter(position, false);
    }

}
