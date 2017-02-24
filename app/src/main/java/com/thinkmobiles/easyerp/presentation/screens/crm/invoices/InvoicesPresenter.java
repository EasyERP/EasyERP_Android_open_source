package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class InvoicesPresenter extends MasterFilterablePresenterHelper implements InvoicesContract.InvoicesPresenter {

    private InvoicesContract.InvoicesView view;
    private InvoicesContract.InvoicesModel model;

    private ArrayList<Invoice> invoices = new ArrayList<>();

    public InvoicesPresenter(InvoicesContract.InvoicesView view, InvoicesContract.InvoicesModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = invoices.get(position).id;
        if(super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredInvoices(helper, page).subscribe(
                        responseGetInvoice -> {
                            currentPage = page;
                            totalItems = responseGetInvoice.total;
                            saveData(responseGetInvoice.data, needClear);
                            setData(responseGetInvoice.data, needClear);
                        },  t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return invoices.size();
    }

    @Override
    protected boolean hasContent() {
        return !invoices.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(invoices, true);
    }

    private void saveData(final List<Invoice> invoices, boolean needClear) {
        if (needClear)
            this.invoices.clear();
        this.invoices.addAll(invoices);
    }

    private void setData(final List<Invoice> invoices, boolean needClear) {
        view.setDataList(prepareInvoiceDHs(invoices, needClear), needClear);
        if (this.invoices.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<InvoiceDH> prepareInvoiceDHs(final List<Invoice> invoices, boolean needClear) {
        int position = 0;
        final ArrayList<InvoiceDH> result = new ArrayList<>();
        for (Invoice invoice : invoices) {
            final InvoiceDH invoiceDH = new InvoiceDH(invoice);
            makeSelectedDHIfNeed(invoiceDH, position++, needClear);
            result.add(invoiceDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
