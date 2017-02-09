package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;

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
            view.showProgress(true);
            loadInvoices(1);
        } else view.displayInvoices(prepareInvoiceDHs(invoices, true), true);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void loadInvoices(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getInvoices(page).subscribe(
                        responseGetInvoice -> {
                            currentPage = page;
                            if (needClear)
                                invoices.clear();
                            invoices.addAll(responseGetInvoice.data);
                            view.displayInvoices(prepareInvoiceDHs(responseGetInvoice.data, needClear), needClear);
                        },
                        throwable -> view.displayError(throwable.getMessage(), ErrorViewHelper.ErrorType.NETWORK)
                )
        );
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
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
        return result;
    }

}
