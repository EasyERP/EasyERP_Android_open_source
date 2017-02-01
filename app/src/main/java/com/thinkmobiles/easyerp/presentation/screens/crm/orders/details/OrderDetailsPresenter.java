package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;


import android.content.res.Resources;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.orders.detail.ResponseGerOrderDetails;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import rx.subscriptions.CompositeSubscription;

public class OrderDetailsPresenter implements OrderDetailsContract.OrderDetailsPresenter {

    private OrderDetailsContract.OrderDetailsView view;
    private OrderDetailsContract.OrderDetailsModel model;
    private String orderId;
    private UserInfo userInfo;
    private CompositeSubscription compositeSubscription;

    private ResponseGerOrderDetails currentOrder;
    private boolean isVisibleHistory;
    private String notSpecified;
    private Resources res;

    public OrderDetailsPresenter(OrderDetailsContract.OrderDetailsView view, OrderDetailsContract.OrderDetailsModel model, UserInfo userInfo, String orderId) {
        this.view = view;
        this.model = model;
        this.userInfo = userInfo;
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

    @Override
    public void subscribe() {
        if (currentOrder == null) {
            view.showProgress(true);
            refresh();
        } else {
            setData(currentOrder);
        }
    }

    private void setData(ResponseGerOrderDetails response) {
        currentOrder = response;

        view.setOrderStatus(currentOrder.workflow.status);
        view.setOrderName(currentOrder.name);
        view.setExpectedDate(DateManager.convert(currentOrder.expectedDate).toString());
        view.setExpectedDate(DateManager.convert(currentOrder.orderDate).toString());
        view.setCompanyName(userInfo.profile.profileName);
        view.setCompanyAddress(userInfo.email);
        view.setSupplierName(currentOrder.supplier.fullName);
        view.setSupplierAddress(StringUtil.getAddress(currentOrder.supplier.address));
        view.setSubTotal("Sub Total" + String.format("%1$20s", "$" + currentOrder.paymentInfo.unTaxed.longValue() / 100));
        view.setDiscount("Discount" + String.format("%1$20s", "$(-)" + currentOrder.paymentInfo.discount.longValue() / 100));
        view.setTaxes("Taxes" + String.format("%1$20s", "$" + currentOrder.paymentInfo.taxes.longValue() / 100));
        view.setTotal("Total" + String.format("%1$20s", "$" + currentOrder.paymentInfo.total.longValue() / 100));
        if (currentOrder.prepayment != null)
            view.setPrepaid("Prepaid" + String.format("%1$20s", "$" + currentOrder.prepayment.sum));
        view.setNameBeneficiary(currentOrder.paymentMethod.owner);
        view.setBank(currentOrder.paymentMethod.bank);
        view.setBankAddress(currentOrder.paymentMethod.address);
        view.setBankIBAN(currentOrder.paymentMethod.account);
        view.setSwiftCode(currentOrder.paymentMethod.swiftCode);
        view.setOwnerName(userInfo.profile.profileName);
        view.setOwnerSite(userInfo.id);
        view.setOwnerEmail(userInfo.email);
        view.setAdvice(String.format("Payment should be made by bank transfer or check made payable to %s", userInfo.profile.profileName.toUpperCase()));
        view.showHistory(isVisibleHistory);
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }
}
