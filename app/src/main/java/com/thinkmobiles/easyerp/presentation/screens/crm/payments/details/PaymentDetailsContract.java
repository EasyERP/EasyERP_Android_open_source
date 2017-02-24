package com.thinkmobiles.easyerp.presentation.screens.crm.payments.details;

import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface PaymentDetailsContract {

    interface PaymentDetailsView extends BaseView<PaymentDetailsPresenter>, ContentView {

        void setPaymentStatus(String paymentStatus);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setPaymentName(String paymentName);
        void setSourceDocument(String sourceDocument);
        void setSupplierName(String supplierName);
        void setSupplierAddress(String supplierAddress);
        void setPaymentDate(String paymentDate);
        void setBankAccount(String bankAccount);
        void setAccount(String account);
        void setAmount(String amount);
        void setOwnerName(String ownerName);
        void setOwnerSite(String ownerSite);
        void setOwnerEmail(String ownerEmail);
        void setAdvice(String advice);
    }

    interface PaymentDetailsPresenter extends ContentPresenter {
    }

    interface PaymentDetailsModel extends BaseModel {
        Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
    }
}
