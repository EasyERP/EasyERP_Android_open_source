package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;


import android.content.res.Resources;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderProduct;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.ResponseGetOrderDetails;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

public class OrderDetailsPresenter implements OrderDetailsContract.OrderDetailsPresenter {

    private OrderDetailsContract.OrderDetailsView view;
    private OrderDetailsContract.OrderDetailsModel model;
    private String orderId;
    private CompositeSubscription compositeSubscription;

    private ResponseGetOrderDetails currentOrder;
    private OrganizationSettings organizationSettings;
    private boolean isVisibleHistory;
    private String notSpecified;
    private Resources res;
    private DecimalFormat formatter;

    public OrderDetailsPresenter(OrderDetailsContract.OrderDetailsView view, OrderDetailsContract.OrderDetailsModel model, String orderId) {
        this.view = view;
        this.model = model;
        this.orderId = orderId;
        view.setPresenter(this);

        compositeSubscription = new CompositeSubscription();
        notSpecified = EasyErpApplication.getInstance().getString(R.string.err_not_specified);
        res = EasyErpApplication.getInstance().getResources();
        formatter = new DollarFormatter().getFormat();
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
                    view.showProgress(Constants.ProgressType.NONE);
                    setData(responseGerOrderDetails);
                }, t -> {
                    t.printStackTrace();
                    if (currentOrder == null) {
                        view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                    } else {
                        view.displayErrorToast(t.getMessage());
                    }
                }));
    }

    private void getOrganizationSettings() {
        compositeSubscription.add(model.getOrganizationSettings()
                .subscribe(responseGetOrganizationSettings -> {
                    setOrgData(responseGetOrganizationSettings.data);
                }, t -> {
                    t.printStackTrace();
                    view.displayErrorToast(t.getMessage());
                }));
    }

    @Override
    public void subscribe() {
        if (currentOrder == null) {
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
            getOrganizationSettings();
        } else {
            setData(currentOrder);
            setOrgData(organizationSettings);
        }
    }

    private void setOrgData(OrganizationSettings response) {
        organizationSettings = response;

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
    }

    private void setData(ResponseGetOrderDetails response) {
        currentOrder = response;

        view.setOrderStatusName(response.workflow.name);
        view.setOrderStatus(response.workflow.status);
        view.setOrderName(response.name);
        view.setExpectedDate(DateManager.convert(response.expectedDate).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        view.setOrderDate(DateManager.convert(response.orderDate).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        view.setSupplierName(response.supplier.fullName);
        view.setSupplierAddress(StringUtil.getAddress(response.supplier.address));
        String symbol = response.currency.id != null ? response.currency.id.symbol : "$";
        if (response.paymentInfo != null) {
            view.setSubTotal(StringUtil.getFormattedPriceFromCent(formatter, response.paymentInfo.unTaxed, symbol));
            if (response.paymentInfo.discount > 0)
                view.setDiscount(StringUtil.getFormattedPriceFromCent(formatter, -response.paymentInfo.discount, symbol));
            view.setTaxes(StringUtil.getFormattedPriceFromCent(formatter, response.paymentInfo.taxes, symbol));
            view.setTotal(StringUtil.getFormattedPriceFromCent(formatter, response.paymentInfo.total, symbol));
        }
        if (response.prepayment != null && response.prepayment.sum != null)
            view.setPrepaid(StringUtil.getFormattedPriceFromCent(formatter, response.prepayment.sum, symbol));
        if (response.paymentMethod != null) {
            view.setNameBeneficiary(response.paymentMethod.owner);
            view.setBank(response.paymentMethod.bank);
            view.setBankAddress(response.paymentMethod.address);
            view.setBankIBAN(response.paymentMethod.account);
            view.setSwiftCode(response.paymentMethod.swiftCode);
        }

        if (response.attachments != null && !response.attachments.isEmpty()) {
            ArrayList<AttachmentDH> result = new ArrayList<>();
            for (AttachmentItem item : response.attachments)
                result.add(new AttachmentDH(item));
            view.setAttachments(result);
        }

        view.setProducts(prepareProductList(response.products, symbol));
        view.setHistory(HistoryDH.convert(response.notes));

        view.showHistory(isVisibleHistory);
    }

    private ArrayList<ProductDH> prepareProductList(ArrayList<OrderProduct> products, String symbol) {
        ArrayList<ProductDH> list = new ArrayList<>();
        for (OrderProduct product : products) {
            list.add(new ProductDH(product, symbol));
        }
        return list;
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }
}
