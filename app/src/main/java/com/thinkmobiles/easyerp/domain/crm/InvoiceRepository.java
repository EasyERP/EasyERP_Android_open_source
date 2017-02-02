package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.invoice.ResponseGetInvoice;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.InvoicesContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class InvoiceRepository extends NetworkRepository implements InvoicesContract.InvoicesModel{

    private InvoiceService invoiceService;

    public InvoiceRepository() {
        invoiceService = Rest.getInstance().getInvoiceService();
    }

    @Override
    public Observable<ResponseGetInvoice> getInvoices(final int page) {
        return getNetworkObservable(invoiceService.getInvoice(null, null, true, "list", 50, null, "invoice"));
    }

}