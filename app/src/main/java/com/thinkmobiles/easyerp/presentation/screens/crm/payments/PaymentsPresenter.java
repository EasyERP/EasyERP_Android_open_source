package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class PaymentsPresenter extends MasterFilterablePresenterHelper implements PaymentsContract.PaymentsPresenter {

    private PaymentsContract.PaymentsView view;
    private PaymentsContract.PaymentsModel model;

    private ArrayList<Payment> payments = new ArrayList<>();

    public PaymentsPresenter(PaymentsContract.PaymentsView view, PaymentsContract.PaymentsModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
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
                model.getFilteredPayments(helper, page).subscribe(
                        responseGetPayments -> {
                            currentPage = page;
                            totalItems = responseGetPayments.total;
                            saveData(responseGetPayments.data, needClear);
                            setData(responseGetPayments.data, needClear);
                        },  t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return payments.size();
    }

    @Override
    protected boolean hasContent() {
        return !payments.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(payments, true);
    }

    @Override
    public void clickItem(int position) {
        Payment payment = payments.get(position);
        if (super.selectItem(payment.id, position))
            view.openDetailsScreen(payment);
    }

    @Override
    public void subscribe() {
        if (payments.isEmpty() && !helper.isInitialized()) {
            getFirstPage();
            loadFilters();
        } else {
            setData(payments, true);
        }
    }

    private void saveData(final List<Payment> payments, boolean needClear) {
        if (needClear)
            this.payments.clear();
        this.payments.addAll(payments);
    }

    private void setData(final List<Payment> payments, boolean needClear) {
        view.setDataList(preparePaymentDHs(payments, needClear), needClear);
        if (this.payments.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<PaymentDH> preparePaymentDHs(final List<Payment> payments, boolean needClear) {
        int position = 0;
        final ArrayList<PaymentDH> result = new ArrayList<>();
        for (Payment payment : payments) {
            final PaymentDH paymentDH = new PaymentDH(payment);
            makeSelectedDHIfNeed(paymentDH, position++, needClear);
            result.add(paymentDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
