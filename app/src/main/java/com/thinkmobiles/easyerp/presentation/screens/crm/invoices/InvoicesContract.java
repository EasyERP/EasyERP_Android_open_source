package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface InvoicesContract {
    interface InvoicesView extends BaseView<InvoicesPresenter>, FilterableView {
        void openDetailsScreen(String invoiceID);
    }
    interface InvoicesPresenter extends FilterablePresenter {}
    interface InvoicesModel extends FilterableModel {
        Observable<ResponseGetTotalItems<Invoice>> getFilteredInvoices(FilterHelper query, int page);
    }
}

