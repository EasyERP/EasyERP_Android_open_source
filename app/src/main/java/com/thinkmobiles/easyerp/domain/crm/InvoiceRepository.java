package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.data.model.crm.invoice.detail.ResponseGetInvoiceDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.InvoicesContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details.InvoiceDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */

public class InvoiceRepository extends NetworkRepository implements InvoicesContract.InvoicesModel, InvoiceDetailsContract.InvoiceDetailsModel{

    private InvoiceService invoiceService;
    private UserService userService;
    private FilterService filterService;

    private String path;
    private String contentType;

    public InvoiceRepository(String path, String contentType) {
        this.path = path;
        this.contentType = contentType;
        invoiceService = Rest.getInstance().getInvoiceService();
        userService = Rest.getInstance().getUserService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetTotalItems<Invoice>> getFilteredInvoices(FilterHelper query, int page) {
        return getNetworkObservable(invoiceService.getInvoices(query
                .createUrl(path, contentType, page)
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
        return getNetworkObservable(filterService.getListFilters(contentType));
    }
}