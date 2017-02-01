package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;

import com.thinkmobiles.easyerp.data.model.crm.orders.detail.ResponseGerOrderDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;


import rx.Observable;


public interface OrderDetailsContract {

    interface OrderDetailsView extends BaseView<OrderDetailsPresenter> {
        void showProgress(boolean enable);
        void showHistory(boolean enable);

        void setOrderStatus(String orderStatus);
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setOrderName(String orderName);
        void setSupplierName(String supplierName);
        void setSupplierAddress(String supplierAddress);
        void setExpectedDate(String expectedDate);
        void setOrderDate(String orderDate);
        void setSubTotal(String subTotal);
        void setDiscount(String discount);
        void setTaxes(String taxes);
        void setTotal(String total);
        void setPrepaid(String prepaid);
        void setNameBeneficiary(String nameBeneficiary);
        void setBank(String bank);
        void setBankAddress(String bankAddress);
        void setBankIBAN(String bankIBAN);
        void setSwiftCode(String swiftCode);
        void setOwnerName(String ownerName);
        void setOwnerSite(String ownerSite);
        void setOwnerEmail(String ownerEmail);
        void setAdvice(String advice);

        void showErrorState(String errorMessage, ErrorViewHelper.ErrorType errorType);
        void showErrorToast(String errorMessage);
    }

    interface OrderDetailsPresenter extends BasePresenter {
        void changeNotesVisibility();
        void refresh();
    }

    interface OrderDetailsModel extends BaseModel {
        Observable<ResponseGerOrderDetails> getOrderDetails(String orderId);
    }
}
