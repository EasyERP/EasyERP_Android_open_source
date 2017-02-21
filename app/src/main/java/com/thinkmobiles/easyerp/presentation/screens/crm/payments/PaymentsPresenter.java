package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
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
    private int totalItems;
    private ArrayList<Payment> payments = new ArrayList<>();

    private FilterHelper helper;

    public PaymentsPresenter(PaymentsContract.PaymentsView view, PaymentsContract.PaymentsModel model) {
        this.view = view;
        this.model = model;
        this.compositeSubscription = new CompositeSubscription();
        helper = new FilterHelper();

        this.view.setPresenter(this);
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

    private void getFirstPage() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadNextPayments(1);
    }

    @Override
    public void loadNextPage() {
        if (view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadNextPayments(currentPage + 1);
    }

    private void loadFilters() {
        compositeSubscription.add(model.getFilters()
                .flatMap(responseFilters -> Observable.just(FilterHelper.create(responseFilters)))
                .subscribe(filterHelper -> {
                    helper = filterHelper;
                    view.createMenuFilters(helper);
                }, t -> {
                    view.displayErrorToast(t.getMessage());
                }));
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void loadNextPayments(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredPayments(helper, page).subscribe(
                        responseGetPayments -> {
                            currentPage = page;
                            totalItems = responseGetPayments.total;
                            saveData(responseGetPayments.data, needClear);
                            setData(responseGetPayments.data, needClear);
                        },
                        throwable -> {
                            if (payments.isEmpty()) {
                                view.displayErrorState(ErrorManager.getErrorType(throwable));
                            } else {
                                view.displayErrorToast(ErrorManager.getErrorMessage(throwable));
                            }
                        }
                )
        );
    }

    @Override
    public void selectItem(PaymentDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openPaymentDetailsScreen(dh.getPayment());
    }

    private void saveData(final List<Payment> payments, boolean needClear) {
        if (needClear)
            this.payments.clear();
        this.payments.addAll(payments);
    }

    private void setData(final List<Payment> payments, boolean needClear) {
        view.displayPayments(preparePaymentDHs(payments, needClear), needClear);
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
            makeSelectedDHIfNeed(paymentDH, view, position++, needClear);
            result.add(paymentDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    @Override
    public void filterBySearchItem(FilterDH filterDH) {
        helper.filterByItem(filterDH, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterBySearchText(String name) {
        helper.filterByText(name, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void filterByList(ArrayList<FilterDH> filterDHs, int requestCode) {
        helper.filterByList(filterDHs, requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void removeFilter(int requestCode) {
        helper.removeFilter(requestCode, (position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }

    @Override
    public void changeFilter(int position, String filterName) {
        view.showFilterDialog(helper.getFilterList(position), position, filterName);
    }

    @Override
    public void buildOptionMenu() {
        view.createMenuFilters(helper);
        helper.setupMenu((position, isVisible) -> view.selectFilter(position, isVisible));
    }

    @Override
    public void removeAll() {
        helper.removeAllFilters((position, isVisible) -> view.selectFilter(position, isVisible));
        getFirstPage();
    }
}
