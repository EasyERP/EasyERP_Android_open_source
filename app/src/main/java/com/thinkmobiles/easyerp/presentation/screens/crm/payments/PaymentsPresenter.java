package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class PaymentsPresenter extends MasterFlowSelectablePresenterHelper<String, PaymentDH> implements PaymentsContract.PaymentsPresenter {

    private PaymentsContract.PaymentsView view;
    private PaymentsContract.PaymentsModel model;

    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;
    private ArrayList<Payment> payments = new ArrayList<>();

    public PaymentsPresenter(PaymentsContract.PaymentsView view, PaymentsContract.PaymentsModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (payments.size() == 0) {
            view.showProgress(true);
            loadPayments(1);
        } else view.displayPayments(prepareOrderDHs(payments), true);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void loadPayments(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getPayments(page).subscribe(
                        responseGetPayments -> {
                            currentPage = page;
                            if (needClear)
                                payments.clear();
                            payments.addAll(responseGetPayments.data);
                            view.displayPayments(prepareOrderDHs(responseGetPayments.data), needClear);
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
    public void selectItem(PaymentDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openPaymentDetailsScreen(dh.getPayment());
    }

    private ArrayList<PaymentDH> prepareOrderDHs(final List<Payment> payments) {
        int position = 0;
        final ArrayList<PaymentDH> result = new ArrayList<>();
        for (Payment payment : payments) {
            final PaymentDH paymentDH = new PaymentDH(payment);
            makeSelectedDHIfNeed(paymentDH, view, position++, true);
            result.add(paymentDH);
        }
        return result;
    }

}
