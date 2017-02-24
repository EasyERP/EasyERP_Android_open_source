package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PaymentsContract {
    interface PaymentsView extends BaseView<PaymentsPresenter>, FilterableView {
        void openDetailsScreen(final Payment payment);
    }
    interface PaymentsPresenter extends FilterablePresenter {}
    interface PaymentsModel extends FilterableModel {
        Observable<ResponseGetPayments> getFilteredPayments(FilterHelper helper, int page);
    }
}
