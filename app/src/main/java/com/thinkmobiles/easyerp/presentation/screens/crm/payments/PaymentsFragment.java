package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import android.support.v7.widget.LinearLayoutManager;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PaymentsAdapter;
import com.thinkmobiles.easyerp.presentation.base.ListRefreshFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class PaymentsFragment extends ListRefreshFragment implements PaymentsContract.PaymentsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_with_search;
    }

    private PaymentsContract.PaymentsPresenter presenter;

    @Bean
    protected PaymentsRepository paymentsRepository;
    @Bean
    protected PaymentsAdapter paymentsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new PaymentsPresenter(this, paymentsRepository);
    }

    @Override
    public void setPresenter(PaymentsContract.PaymentsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(mActivity);
        initEndlessScrollListener(recyclerLayoutManager);

        listRecycler.setLayoutManager(recyclerLayoutManager);
        listRecycler.setAdapter(paymentsAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        paymentsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(paymentsAdapter.getItem(position), position));

        presenter.subscribe();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    protected void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    public int getCountItemsNow() {
        return paymentsAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        paymentsAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void displayPayments(ArrayList<PaymentDH> paymentDHs, boolean needClear) {
        if (needClear) {
            paymentsAdapter.setListDH(paymentDHs);
        } else {
            paymentsAdapter.addListDH(paymentDHs);
        }

    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
    }

    @Override
    public void displayErrorMessage(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void openPaymentDetailsScreen(Payment payment) {
        if (payment != null) {
            mActivity.replaceFragmentContentDetail(PaymentDetailsFragment_.builder()
                    .payment(payment)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }
}
