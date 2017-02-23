package com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details;

import com.thinkmobiles.easyerp.data.model.crm.invoice.detail.ResponseGetInvoiceDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoicePaymentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 06.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface InvoiceDetailsContract {


    interface InvoiceDetailsView extends BaseView<InvoiceDetailsPresenter> {
        void showProgress(Constants.ProgressType type);
        void showHistory(boolean enable);

        void setInvoiceStatusName(String orderStatus);
        void setInvoiceStatus(String orderStatus);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setInvoiceName(String orderName);
        void setSupplierName(String supplierName);
        void setInvoiceDate(String orderDate);
        void setDueDate(String dueDate);
        void setOrderNumber(String orderNumber);
        void setSubTotal(String subTotal);
        void setDiscount(String discount);
        void setTaxes(String taxes);
        void setTotal(String total);
        void setPaymentMade(String paymentMade);
        void setBalanceDue(String balanceDue);
        void setAttachments(ArrayList<AttachmentDH> attachments);
        void setPayments(ArrayList<InvoicePaymentDH> payments);
        void setHistory(ArrayList<HistoryDH> history);
        void setProducts(ArrayList<ProductDH> products);

        void displayErrorState(final ErrorType errorType);
        void displayErrorToast(final String msg);

        void startUrlIntent(String url);
    }

    interface InvoiceDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
        void startAttachment(int pos);
    }

    interface InvoiceDetailsModel extends BaseModel {
        Observable<ResponseGetInvoiceDetails> getInvoiceDetails(String invoiceId);
        Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
    }
}
