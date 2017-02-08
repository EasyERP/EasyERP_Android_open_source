package com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.InvoicePaymentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.ProductAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoicePaymentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex Michenko (Created on 06.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment(R.layout.fragment_invoice_details)
public class InvoiceDetailsFragment extends BaseFragment<HomeActivity> implements InvoiceDetailsContract.InvoiceDetailsView {


    @FragmentArg
    protected String invoiceId;

    @ViewById
    protected SwipeRefreshLayout srlRefresh_FID;
    @ViewById
    protected NestedScrollView nsvContent_FID;
    @ViewById
    protected TextView tvInvoiceStatus_FID;
    @ViewById
    protected TextView tvCompanyName_FID;
    @ViewById
    protected TextView tvCompanyAddress_FID;
    @ViewById
    protected TextView tvInvoiceName_FID;
    @ViewById
    protected TextView tvBalanceHead_FID;
    @ViewById
    protected TextView tvSupplierName_FID;
    @ViewById
    protected TextView tvInvoiceDate_FID;
    @ViewById
    protected TextView tvDueDateTitle_FID;
    @ViewById
    protected TextView tvDueDate_FID;
    @ViewById
    protected TextView tvOrderNumber_FID;
    @ViewById
    protected RecyclerView rvProductList_FID;
    @ViewById
    protected TextView tvSubTotal_FID;
    @ViewById
    protected TextView tvDiscount_FID;
    @ViewById
    protected TextView tvDiscountTitle_FID;
    @ViewById
    protected TextView tvTaxes_FID;
    @ViewById
    protected TextView tvTotal_FID;
    @ViewById
    protected TextView tvPaymentMade_FID;
    @ViewById
    protected TextView tvPaymentMadeTitle_FID;
    @ViewById
    protected TextView tvBalanceDue_FID;
    @ViewById
    protected TextView tvAttachments_FID;
    @ViewById
    protected TextView tvPaymentsTitle_FID;
    @ViewById
    protected RecyclerView rvPayments_FID;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvHistory;
    @ViewById
    protected LinearLayout llErrorLayout;

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @Bean
    protected InvoiceRepository invoiceRepository;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected ProductAdapter productAdapter;
    @Bean
    protected InvoicePaymentAdapter paymentAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;
    @Bean
    protected HistoryAnimationHelper animationHelper;

    private InvoiceDetailsContract.InvoiceDetailsPresenter presenter;

    @AfterInject
    @Override
    public void initPresenter() {
        new InvoiceDetailsPresenter(this, invoiceRepository, invoiceId);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(llErrorLayout, v -> presenter.subscribe());

        rvHistory.setAdapter(historyAdapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvProductList_FID.setAdapter(productAdapter);
        rvProductList_FID.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvPayments_FID.setAdapter(paymentAdapter);
        rvPayments_FID.setLayoutManager(new LinearLayoutManager(getActivity()));


        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        srlRefresh_FID.setOnRefreshListener(() -> presenter.refresh());

        animationHelper.init(ivIconArrow, rvHistory);

        presenter.subscribe();
    }

    @Override
    public void setPresenter(InvoiceDetailsContract.InvoiceDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        animationHelper.cancel();
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void showProgress(boolean enable) {
        if (enable) {
            errorViewHelper.hideError();
            displayProgress(true);
            srlRefresh_FID.setVisibility(View.GONE);
            srlRefresh_FID.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FID.setVisibility(View.VISIBLE);
            srlRefresh_FID.setRefreshing(false);
        }
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable && rvHistory.getVisibility() == View.GONE) {
            animationHelper.forward(nsvContent_FID.getHeight());
        }
        if (!enable && rvHistory.getVisibility() == View.VISIBLE)
            animationHelper.backward(rvHistory.getHeight());
    }

    @Override
    public void setInvoiceStatusName(String orderStatus) {
        tvInvoiceStatus_FID.setText(orderStatus);
    }

    @Override
    public void setInvoiceStatus(String orderStatus) {
        tvInvoiceStatus_FID.setBackgroundResource(TagHelper.getColorResIdByName(orderStatus));
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FID.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FID.setText(companyAddress);
    }

    @Override
    public void setInvoiceName(String orderName) {
        tvInvoiceName_FID.setText(orderName);
    }

    @Override
    public void setSupplierName(String supplierName) {
        tvSupplierName_FID.setText(supplierName);
    }

    @Override
    public void setInvoiceDate(String orderDate) {
        tvInvoiceDate_FID.setText(orderDate);
    }

    @Override
    public void setDueDate(String dueDate) {
        tvDueDate_FID.setVisibility(View.VISIBLE);
        tvDueDateTitle_FID.setVisibility(View.VISIBLE);
        tvDueDate_FID.setText(dueDate);
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        tvOrderNumber_FID.setText(orderNumber);
    }

    @Override
    public void setSubTotal(String subTotal) {
        tvSubTotal_FID.setText(subTotal);
    }

    @Override
    public void setDiscount(String discount) {
        tvDiscount_FID.setVisibility(View.VISIBLE);
        tvDiscountTitle_FID.setVisibility(View.VISIBLE);
        tvDiscount_FID.setText(discount);
    }

    @Override
    public void setTaxes(String taxes) {
        tvTaxes_FID.setText(taxes);
    }

    @Override
    public void setTotal(String total) {
        tvTotal_FID.setText(total);
    }

    @Override
    public void setPaymentMade(String paymentMade) {
        tvPaymentMade_FID.setVisibility(View.VISIBLE);
        tvPaymentMadeTitle_FID.setVisibility(View.VISIBLE);
        tvPaymentMade_FID.setText(paymentMade);
    }

    @Override
    public void setBalanceDue(String balanceDue) {
        tvBalanceDue_FID.setText(balanceDue);
        tvBalanceHead_FID.setText(balanceDue);
    }

    @Override
    public void setAttachments(String attachments) {
        tvAttachments_FID.setText(Html.fromHtml(attachments));
    }

    @Override
    public void setPayments(ArrayList<InvoicePaymentDH> payments) {
        tvPaymentsTitle_FID.setVisibility(View.VISIBLE);
        rvPayments_FID.setVisibility(View.VISIBLE);
        paymentAdapter.setListDH(payments);
    }

    @Override
    public void setHistory(ArrayList<HistoryDH> history) {
        historyAdapter.setListDH(history);
    }

    @Override
    public void setProducts(ArrayList<ProductDH> products) {
        productAdapter.setListDH(products);
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
