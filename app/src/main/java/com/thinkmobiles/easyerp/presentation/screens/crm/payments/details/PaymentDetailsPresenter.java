package com.thinkmobiles.easyerp.presentation.screens.crm.payments.details;

import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.data.model.user.organization.OrganizationSettings;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.text.DecimalFormat;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class PaymentDetailsPresenter implements PaymentDetailsContract.PaymentDetailsPresenter {

    private PaymentDetailsContract.PaymentDetailsView view;
    private PaymentDetailsContract.PaymentDetailsModel model;
    private CompositeSubscription compositeSubscription;

    private Payment currentPayment;
    private OrganizationSettings organizationSettings;
    private DecimalFormat formatter;

    public PaymentDetailsPresenter(PaymentDetailsContract.PaymentDetailsView view, PaymentDetailsContract.PaymentDetailsModel model, Payment currentPayment) {
        this.view = view;
        this.model = model;
        this.currentPayment = currentPayment;
        view.setPresenter(this);

        compositeSubscription = new CompositeSubscription();
        formatter = new DollarFormatter().getFormat();
    }

    @Override
    public void refresh() {
        if (organizationSettings == null) {
            compositeSubscription.add(model.getOrganizationSettings()
                    .subscribe(responseGetOrganizationSettings -> {
                        organizationSettings = responseGetOrganizationSettings.data;
                        view.showProgress(Constants.ProgressType.NONE);
                        setData();
                    }, t -> {
                        view.displayErrorState(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK);
                    }));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            setData();
        }
    }

    @Override
    public void subscribe() {
        view.showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    private void setData() {
        String prefix = currentPayment.currency != null ? currentPayment.currency.symbol : "$";
        if (currentPayment.refund) {
            view.setPaymentStatus("Refunded");
            view.setPaymentName(String.format("%s %s", "Refund" , currentPayment.name));
            view.setAmount(StringUtil.getFormattedPriceFromCent(formatter, -currentPayment.paidAmount, prefix));
        } else {
            view.setPaymentStatus(currentPayment.workflow);
            view.setPaymentName(String.format("%s %s", currentPayment.order == null ? "Payment" : "Prepayment" , currentPayment.name));
            view.setAmount(StringUtil.getFormattedPriceFromCent(formatter, currentPayment.paidAmount, prefix));
        }
        String doc = "";
        if (currentPayment.invoice != null) {
            doc = currentPayment.invoice.name;
        }
        if (currentPayment.order != null) {
            doc = currentPayment.order.name;
        }
        if (doc != null) {
            view.setSourceDocument(String.format("%s %s", "Source Document", doc));
        }
        view.setPaymentDate(DateManager.convert(currentPayment.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString());
        if (currentPayment.paymentMethod != null) {
            view.setBankAccount(currentPayment.paymentMethod.name);
        }
        if (currentPayment.bankAccount != null) {
            view.setAccount(currentPayment.bankAccount.name);
        }
        if (currentPayment.supplier != null) {
            view.setSupplierName(currentPayment.supplier.fullName);
            view.setSupplierAddress(StringUtil.getAddress(currentPayment.supplier.address));
        }

        if (organizationSettings != null) {
            view.setCompanyName(organizationSettings.name);
            if (organizationSettings.address != null)
                view.setCompanyAddress(StringUtil.getAddress(organizationSettings.address));

            view.setOwnerName(organizationSettings.contactName);
            view.setOwnerSite(organizationSettings.website);
            if (organizationSettings.contact != null)
                view.setOwnerEmail(organizationSettings.contact.email);
            view.setAdvice(String.format("Payment should be made by bank transfer or check made payable to %s.", organizationSettings.contactName.toUpperCase()));
        }
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }
}
