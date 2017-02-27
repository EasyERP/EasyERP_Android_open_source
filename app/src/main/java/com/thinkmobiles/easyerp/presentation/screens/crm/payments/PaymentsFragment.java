package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PaymentsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */

@EFragment
public class PaymentsFragment extends MasterFilterableFragment implements PaymentsContract.PaymentsView {

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

    @Override
    public String getScreenName() {
        return "Payment list screen";
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return paymentsAdapter;
    }

    @Override
    public void openDetailsScreen(Payment payment) {
        if (payment != null) {
            mActivity.replaceFragmentContentDetail(PaymentDetailsFragment_.builder()
                    .payment(payment)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
