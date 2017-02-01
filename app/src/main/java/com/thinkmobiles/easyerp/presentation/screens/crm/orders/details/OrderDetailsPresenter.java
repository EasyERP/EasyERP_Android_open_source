package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;


import android.content.res.Resources;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.orders.detail.ResponseGerOrderDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import rx.subscriptions.CompositeSubscription;

public class OrderDetailsPresenter implements OrderDetailsContract.OrderDetailsPresenter {

    private OrderDetailsContract.OrderDetailsView view;
    private OrderDetailsContract.OrderDetailsModel model;
    private String orderId;
    private CompositeSubscription compositeSubscription;

    private ResponseGerOrderDetails currentOrder;
    private OrganizationSettings organizationSettings;
    private boolean isVisibleHistory;
    private String notSpecified;
    private Resources res;

    public OrderDetailsPresenter(OrderDetailsContract.OrderDetailsView view, OrderDetailsContract.OrderDetailsModel model, String orderId) {
        this.view = view;
        this.model = model;
        this.orderId = orderId;
        view.setPresenter(this);

        compositeSubscription = new CompositeSubscription();
        notSpecified = EasyErpApplication.getInstance().getString(R.string.err_not_specified);
        res = EasyErpApplication.getInstance().getResources();
    }

    @Override
    public void changeNotesVisibility() {
        isVisibleHistory = !isVisibleHistory;
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void refresh() {
        compositeSubscription.add(model.getOrderDetails(orderId)
                .subscribe(responseGerOrderDetails -> {
                    view.showProgress(false);
                    setData(responseGerOrderDetails);
                }, t -> {
                    view.showProgress(false);
                    t.printStackTrace();
                    if (currentOrder == null) {
                        view.showErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                    } else {
                        view.showErrorToast(t.getMessage());
                    }
                }));
    }

    private void getOrganizationSettings() {
        compositeSubscription.add(model.getOrganizationSettings()
                .subscribe(responseGetOrganizationSettings -> {
                    organizationSettings = responseGetOrganizationSettings.data;

                }, Throwable::printStackTrace));
    }

    @Override
    public void subscribe() {
        if (currentOrder == null) {
            view.showProgress(true);
            refresh();
            getOrganizationSettings();
        } else {
            setData(currentOrder);
        }
    }

    private void setData(ResponseGerOrderDetails response) {
        currentOrder = response;

        view.setOrderStatus(response.workflow.status);
        view.setOrderName(response.name);
        view.setExpectedDate(DateManager.convert(response.expectedDate).setDstPattern(DateManager.PATTERN_SIMPLE_DATE_SHORT).toString());
        view.setExpectedDate(DateManager.convert(response.orderDate).setDstPattern(DateManager.PATTERN_SIMPLE_DATE_SHORT).toString());
        view.setSupplierName(response.supplier.fullName);
        view.setSupplierAddress(StringUtil.getAddress(response.supplier.address));
        view.setSubTotal("Sub Total" + String.format("%1$20s", "$" + response.paymentInfo.unTaxed.longValue() / 100));
        view.setDiscount("Discount" + String.format("%1$20s", "$(-)" + response.paymentInfo.discount.longValue() / 100));
        view.setTaxes("Taxes" + String.format("%1$20s", "$" + response.paymentInfo.taxes.longValue() / 100));
        view.setTotal("Total" + String.format("%1$20s", "$" + response.paymentInfo.total.longValue() / 100));
        if (response.prepayment != null)
            view.setPrepaid("Prepaid" + String.format("%1$20s", "$" + response.prepayment.sum));
        if (response.paymentMethod != null) {
            view.setNameBeneficiary(response.paymentMethod.owner);
            view.setBank(response.paymentMethod.bank);
            view.setBankAddress(response.paymentMethod.address);
            view.setBankIBAN(response.paymentMethod.account);
            view.setSwiftCode(response.paymentMethod.swiftCode);
        }

        if (organizationSettings != null) {
            view.setCompanyName(organizationSettings.name);
            if (organizationSettings.address != null)
                view.setCompanyAddress(StringUtil.getAddress(organizationSettings.address));

            view.setOwnerName(organizationSettings.contactName);
            view.setOwnerSite(organizationSettings.website);
            if (organizationSettings.contact != null)
                view.setOwnerEmail(organizationSettings.contact.email);
            view.setAdvice(String.format("Payment should be made by bank transfer or check made payable to %s", organizationSettings.contactName.toUpperCase()));
        }

        if (response.attachments != null && !response.attachments.isEmpty()) {
            view.setAttachments(StringUtil.getAttachments(response.attachments));
        }
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }
}
