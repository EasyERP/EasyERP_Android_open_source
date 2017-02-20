package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

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

        void displayErrorState(final String msg, final ErrorViewHelper.ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);

        void createMenuFilters(FilterHelper helper);
        void selectFilter(int id, boolean isSelected);

        void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName);
    }
    interface PaymentsPresenter extends MasterFlowSelectableBasePresenter<String, PaymentDH> {
        void refresh();
        void loadNextPage();

        void filterBySearchItem(FilterDH filterDH);
        void filterBySearchText(String name);
        void filterByList(ArrayList<FilterDH> filterDHs, int requestCode);
        void removeFilter(int requestCode);

        void changeFilter(int position, String filterName);
        void buildOptionMenu();
        void removeAll();
    }
    interface PaymentsModel extends BaseModel {
        Observable<ResponseGetPayments> getFilteredPayments(FilterHelper helper, int page);
        Observable<ResponseFilters> getFilters();
    }
}
