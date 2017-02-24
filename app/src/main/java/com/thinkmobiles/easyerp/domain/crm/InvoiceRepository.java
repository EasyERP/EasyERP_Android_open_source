package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.invoice.ResponseGetInvoice;
import com.thinkmobiles.easyerp.data.model.crm.invoice.detail.ResponseGetInvoiceDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.InvoicesContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details.InvoiceDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class InvoiceRepository extends NetworkRepository implements InvoicesContract.InvoicesModel, InvoiceDetailsContract.InvoiceDetailsModel{

    private InvoiceService invoiceService;
    private UserService userService;
    private FilterService filterService;

    public InvoiceRepository() {
        invoiceService = Rest.getInstance().getInvoiceService();
        userService = Rest.getInstance().getUserService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetInvoice> getFilteredInvoices(FilterHelper query, int page) {
        return getNetworkObservable(invoiceService.getInvoices(query
                .createUrl(Constants.GET_INVOICE, "invoice", page)
                .build()
                .toString()
        ));
    }

    @Override
    public Observable<ResponseGetInvoiceDetails> getInvoiceDetails(String invoiceId) {
        return getNetworkObservable(invoiceService.getInvoiceDetails(invoiceId, "form", true));
    }

    @Override
    public Observable<ResponseGetOrganizationSettings> getOrganizationSettings() {
        return getNetworkObservable(userService.getOrganizationSettings());
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("invoice"));
    }
}