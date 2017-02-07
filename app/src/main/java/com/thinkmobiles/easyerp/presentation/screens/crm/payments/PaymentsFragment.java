package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PaymentsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class PaymentsFragment extends MasterFlowListFragment implements PaymentsContract.PaymentsView {

    private PaymentsContract.PaymentsPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Bean
    protected PaymentsRepository paymentsRepository;
    @Bean
    protected PaymentsAdapter paymentsAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

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
        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadPayments(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(paymentsAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        paymentsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(paymentsAdapter.getItem(position), position));

        loadWithProgressBar();
    }

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.subscribe();
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
        errorViewHelper.hideError();
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            paymentsAdapter.setListDH(paymentDHs);
        else paymentsAdapter.addListDH(paymentDHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
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
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}
