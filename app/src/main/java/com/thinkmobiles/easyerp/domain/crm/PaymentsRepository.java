package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.data.services.PaymentsService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.PaymentsContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class PaymentsRepository extends NetworkRepository implements PaymentsContract.PaymentsModel {

    private PaymentsService paymentsService;

    public PaymentsRepository() {
        paymentsService = Rest.getInstance().getPaymentService();
    }

    @Override
    public Observable<ResponseGetPayments> getPayments(int page) {
        return getNetworkObservable(paymentsService.getPayments("list", page, 50, "customerPayments"));
    }
}
