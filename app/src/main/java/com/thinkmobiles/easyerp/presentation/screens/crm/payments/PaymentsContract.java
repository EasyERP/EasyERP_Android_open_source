package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface PaymentsContract {
    interface PaymentsView extends MasterFlowSelectableBaseView<PaymentsPresenter> {
        void displayPayments(ArrayList<PaymentDH> paymentDHs, boolean needClear);
        void openPaymentDetailsScreen(final Payment payment);

        void displayErrorState(final ErrorViewHelper.ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);
    }
    interface PaymentsPresenter extends MasterFlowSelectableBasePresenter<String, PaymentDH> {
        void refresh();
        void loadNextPage();
    }
    interface PaymentsModel extends BaseModel {
        Observable<ResponseGetPayments> getPayments(final int page);
    }
}
