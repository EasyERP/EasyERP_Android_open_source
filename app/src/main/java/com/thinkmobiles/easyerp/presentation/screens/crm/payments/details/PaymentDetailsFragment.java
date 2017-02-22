package com.thinkmobiles.easyerp.presentation.screens.crm.payments.details;

import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.RefreshFragment;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public class PaymentDetailsFragment extends RefreshFragment implements PaymentDetailsContract.PaymentDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_payment_details;
    }

    @FragmentArg
    protected Payment payment;

    @ViewById
    protected TextView tvTitlePayment_FPD;
    @ViewById
    protected TextView tvPaymentStatus_FPD;
    @ViewById
    protected TextView tvCompanyName_FPD;
    @ViewById
    protected TextView tvCompanyAddress_FPD;
    @ViewById
    protected TextView tvPaymentName_FPD;
    @ViewById
    protected TextView tvSourceDocument_FPD;
    @ViewById
    protected TextView tvSupplierName_FPD;
    @ViewById
    protected TextView tvSupplierAddress_FPD;
    @ViewById
    protected TextView tvPaymentDate_FPD;
    @ViewById
    protected TextView tvBankAccount_FPD;
    @ViewById
    protected TextView tvAccount_FPD;
    @ViewById
    protected TextView tvAmount_FPD;
    @ViewById
    protected TextView tvOwnerName_FPD;
    @ViewById
    protected TextView tvOwnerSite_FPD;
    @ViewById
    protected TextView tvOwnerEmail_FPD;
    @ViewById
    protected TextView tvAdvice_FPD;

    private PaymentDetailsContract.PaymentDetailsPresenter presenter;

    @Bean
    protected PaymentsRepository paymentsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new PaymentDetailsPresenter(this, paymentsRepository, payment);
    }

    @AfterViews
    protected void initUI() {
        presenter.subscribe();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        presenter.refresh();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(PaymentDetailsContract.PaymentDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void setPaymentStatus(String paymentStatus) {
        tvPaymentStatus_FPD.setText(paymentStatus);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FPD.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FPD.setText(companyAddress);
    }

    @Override
    public void setPaymentName(String paymentName) {
        tvPaymentName_FPD.setText(paymentName);
        tvTitlePayment_FPD.setText(paymentName);
    }

    @Override
    public void setSourceDocument(String sourceDocument) {
        tvSourceDocument_FPD.setText(sourceDocument);
    }

    @Override
    public void setSupplierName(String supplierName) {
        tvSupplierName_FPD.setText(supplierName);
    }

    @Override
    public void setSupplierAddress(String supplierAddress) {
        tvSupplierAddress_FPD.setText(supplierAddress);
    }

    @Override
    public void setPaymentDate(String paymentDate) {
        tvPaymentDate_FPD.setText(paymentDate);
    }

    @Override
    public void setBankAccount(String bankAccount) {
        tvBankAccount_FPD.setText(bankAccount);
    }

    @Override
    public void setAccount(String account) {
        tvAccount_FPD.setText(account);
    }

    @Override
    public void setAmount(String amount) {
        tvAmount_FPD.setText(amount);
    }

    @Override
    public void setOwnerName(String ownerName) {
        tvOwnerName_FPD.setText(ownerName);
    }

    @Override
    public void setOwnerSite(String ownerSite) {
        tvOwnerSite_FPD.setText(ownerSite);
    }

    @Override
    public void setOwnerEmail(String ownerEmail) {
        tvOwnerEmail_FPD.setText(ownerEmail);
    }

    @Override
    public void setAdvice(String advice) {
        tvAdvice_FPD.setText(advice);
    }

    @Override
    public void displayErrorState(ErrorType errorType) {
        showErrorState(errorType);
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }
}
