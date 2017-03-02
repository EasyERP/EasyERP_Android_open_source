package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.PaymentsService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.PaymentsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class PaymentsRepository extends NetworkRepository implements PaymentsContract.PaymentsModel, PaymentDetailsContract.PaymentDetailsModel {

    private PaymentsService paymentsService;
    private UserService userService;
    private FilterService filterService;

    private String path;
    private String contentType;

    public PaymentsRepository(String path, String contentType) {
        this.path = path;
        this.contentType = contentType;
        paymentsService = Rest.getInstance().getPaymentService();
        userService = Rest.getInstance().getUserService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetPayments> getFilteredPayments(FilterHelper helper, int page) {
        return getNetworkObservable(paymentsService.getFilteredPayments(helper
                .createUrl(path, contentType, page)
                .build()
                .toString()
        ));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters(contentType));
    }

    @Override
    public Observable<ResponseGetOrganizationSettings> getOrganizationSettings() {
        return getNetworkObservable(userService.getOrganizationSettings());
    }
}
