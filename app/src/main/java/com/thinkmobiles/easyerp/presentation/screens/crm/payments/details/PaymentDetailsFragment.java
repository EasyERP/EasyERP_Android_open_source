package com.thinkmobiles.easyerp.presentation.screens.crm.payments.details;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import rx.annotations.Beta;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment(R.layout.fragment_payment_details)
public class PaymentDetailsFragment extends BaseFragment<HomeActivity> implements PaymentDetailsContract.PaymentDetailsView {

    @FragmentArg
    protected Payment payment;

    @ViewById
    protected SwipeRefreshLayout srlRefresh_FPD;
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
    @ViewById
    protected LinearLayout llErrorLayout;

    private PaymentDetailsContract.PaymentDetailsPresenter presenter;

    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected PaymentsRepository paymentsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new PaymentDetailsPresenter(this, paymentsRepository, payment);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(llErrorLayout, v -> presenter.subscribe());

        srlRefresh_FPD.setOnRefreshListener(() -> presenter.refresh());

        presenter.subscribe();
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
    public void showProgress(boolean enable) {
        if (enable) {
            errorViewHelper.hideError();
            displayProgress(true);
            srlRefresh_FPD.setVisibility(View.GONE);
            srlRefresh_FPD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FPD.setVisibility(View.VISIBLE);
            srlRefresh_FPD.setRefreshing(false);
        }
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
    public void showError(String errorMessage, ErrorViewHelper.ErrorType errorType) {
        errorViewHelper.showErrorMsg(errorMessage, errorType);
    }

    @Override
    public void showMessage(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
