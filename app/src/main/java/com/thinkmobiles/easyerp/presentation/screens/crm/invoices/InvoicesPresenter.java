package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
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
    private FilterHelper helper;

    public InvoicesPresenter(InvoicesContract.InvoicesView view, InvoicesContract.InvoicesModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();
        this.helper = new FilterHelper();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (invoices.isEmpty() && !helper.isInitialized()) {
            getFirstPage();
            loadFilters();
        } else {
            setData(invoices, true);
        }
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

    private void loadFilters() {
        compositeSubscription.add(model.getInvoiceFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorToast(t.getMessage());
                }));
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void loadInvoices(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredInvoices(helper, page).subscribe(
                        responseGetInvoice -> {
                            currentPage = page;
                            totalItems = responseGetInvoice.total;
                            saveData(responseGetInvoice.data, needClear);
                            setData(responseGetInvoice.data, needClear);
                        },
                        throwable -> {
                            if (invoices.isEmpty()) {
                                view.displayErrorState(ErrorManager.getErrorType(throwable));
                            } else {
                                view.displayErrorToast(ErrorManager.getErrorMessage(throwable));
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

    private void setData(final List<Invoice> invoices, boolean needClear) {
        view.displayInvoices(prepareInvoiceDHs(invoices, needClear), needClear);
        if (this.invoices.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
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
    public void filterBySearchItem(FilterDH filterDH) {
        helper.filterByItem(filterDH, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterBySearchText(String name) {
        helper.filterByText(name, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        helper.filterByList(filterDHs, requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        helper.removeFilter(requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void changeFilter(int position, String filterName) {
        view.showFilterDialog(helper.getFilterList(position), position, filterName);
    }

    @Override
    public void buildOptionMenu() {
        view.createMenuFilters(helper);
        helper.setupMenu((position, isVisible) -> view.selectFilter(position, isVisible));
    }

    @Override
    public void removeAll() {
        helper.removeAllFilters((position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

}
