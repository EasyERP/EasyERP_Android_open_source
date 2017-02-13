package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

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

    public InvoicesPresenter(InvoicesContract.InvoicesView view, InvoicesContract.InvoicesModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (invoices.size() == 0) {
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
        } else view.displayInvoices(prepareInvoiceDHs(invoices, true), true);
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

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void loadInvoices(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getInvoices(page).subscribe(
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

}
