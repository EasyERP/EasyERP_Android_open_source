package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.invoice.ResponseGetInvoice;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface InvoicesContract {
    interface InvoicesView extends MasterFlowSelectableBaseView<InvoicesPresenter> {
        void displayInvoices(ArrayList<InvoiceDH> invoiceDHs, boolean needClear);
        void openInvoiceDetailsScreen(String invoiceID);

        void displayErrorState(final ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);
    }
    interface InvoicesPresenter extends MasterFlowSelectableBasePresenter<String, InvoiceDH> {
        void refresh();
        void loadNextPage();
    }
    interface InvoicesModel extends BaseModel {
        Observable<ResponseGetInvoice> getInvoices(final int page);
    }
}

