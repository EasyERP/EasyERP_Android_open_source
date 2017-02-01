package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;


@EFragment(R.layout.fragment_order_details)
public class OrderDetailsFragment extends BaseFragment<HomeActivity> implements OrderDetailsContract.OrderDetailsView {

    @FragmentArg
    protected String orderId;

    @ViewById
    protected SwipeRefreshLayout srlRefresh_FOD;
    @ViewById
    protected NestedScrollView nsvContent_FOD;
    @ViewById
    protected TextView tvOrderStatus_FOD;
    @ViewById
    protected TextView tvCompanyName_FOD;
    @ViewById
    protected TextView tvCompanyAddress_FOD;
    @ViewById
    protected TextView tvOrderName_FOD;
    @ViewById
    protected TextView tvSupplierName_FOD;
    @ViewById
    protected TextView tvSupplierAddress_FOD;
    @ViewById
    protected TextView tvExpectedDate_FOD;
    @ViewById
    protected TextView tvOrderDate_FOD;
    @ViewById
    protected RecyclerView rvProductList_FOD;
    @ViewById
    protected TextView tvSubTotal_FOD;
    @ViewById
    protected TextView tvDiscount_FOD;
    @ViewById
    protected TextView tvTaxes_FOD;
    @ViewById
    protected TextView tvTotal_FOD;
    @ViewById
    protected TextView tvPrepaid_FOD;
    @ViewById
    protected TextView tvNameBeneficiary_FOD;
    @ViewById
    protected TextView tvBank_FOD;
    @ViewById
    protected TextView tvBankAddress_FOD;
    @ViewById
    protected TextView tvBankIBAN_FOD;
    @ViewById
    protected TextView tvSwiftCode_FOD;
    @ViewById
    protected TextView tvOwnerName_FOD;
    @ViewById
    protected TextView tvOwnerSite_FOD;
    @ViewById
    protected TextView tvOwnerEmail_FOD;
    @ViewById
    protected TextView tvAdvice_FOD;
    @ViewById
    protected RelativeLayout btnHistory_FOD;
    @ViewById
    protected ImageView ivIconArrow_FOD;
    @ViewById
    protected View viewHistoryDivider_FOD;
    @ViewById
    protected RecyclerView rvHistory_FOD;

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;

    @Bean
    protected OrderRepository orderRepository;

    private OrderDetailsContract.OrderDetailsPresenter presenter;

    @AfterInject
    @Override
    public void initPresenter() {
        new OrderDetailsPresenter(this, orderRepository, mActivity.getUserInfo(), orderId);
    }

    @AfterViews
    protected void initUI() {

        presenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    public void setPresenter(OrderDetailsContract.OrderDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean enable) {
        if (enable) {
            displayProgress(true);
            srlRefresh_FOD.setVisibility(View.GONE);
            srlRefresh_FOD.setRefreshing(false);
        } else {
            displayProgress(false);
            srlRefresh_FOD.setVisibility(View.VISIBLE);
            srlRefresh_FOD.setRefreshing(false);
        }
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable) {
            nsvContent_FOD.setVisibility(View.GONE);
            rvHistory_FOD.setVisibility(View.VISIBLE);
            btnHistory_FOD.setBackgroundColor(ContextCompat.getColor(getActivity(), (android.R.color.white)));
            viewHistoryDivider_FOD.setVisibility(View.VISIBLE);
            ivIconArrow_FOD.setImageDrawable(icArrowDown);
        } else {
            btnHistory_FOD.setBackgroundColor(ContextCompat.getColor(getActivity(), (R.color.color_grey_transparent)));
            viewHistoryDivider_FOD.setVisibility(View.GONE);
            rvHistory_FOD.setVisibility(View.GONE);
            nsvContent_FOD.setVisibility(View.VISIBLE);
            ivIconArrow_FOD.setImageDrawable(icArrowUp);
        }
    }

    @Override
    public void setOrderStatus(String orderStatus) {
        tvOrderStatus_FOD.setText(orderStatus);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FOD.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FOD.setText(companyAddress);
    }

    @Override
    public void setOrderName(String orderName) {
        tvOrderName_FOD.setText(orderName);
    }

    @Override
    public void setSupplierName(String supplierName) {
        tvSupplierName_FOD.setText(supplierName);
    }

    @Override
    public void setSupplierAddress(String supplierAddress) {
        tvSupplierAddress_FOD.setText(supplierAddress);
    }

    @Override
    public void setExpectedDate(String expectedDate) {
        tvExpectedDate_FOD.setText(expectedDate);
    }

    @Override
    public void setOrderDate(String orderDate) {
        tvOrderDate_FOD.setText(orderDate);
    }

    @Override
    public void setSubTotal(String subTotal) {
        tvSubTotal_FOD.setText(subTotal);
    }

    @Override
    public void setDiscount(String discount) {
        tvDiscount_FOD.setText(discount);
    }

    @Override
    public void setTaxes(String taxes) {
        tvTaxes_FOD.setText(taxes);
    }

    @Override
    public void setTotal(String total) {
        tvTotal_FOD.setText(total);
    }

    @Override
    public void setPrepaid(String prepaid) {
        tvPrepaid_FOD.setText(prepaid);
    }

    @Override
    public void setNameBeneficiary(String nameBeneficiary) {
        tvNameBeneficiary_FOD.setText(nameBeneficiary);
    }

    @Override
    public void setBank(String bank) {
        tvBank_FOD.setText(bank);
    }

    @Override
    public void setBankAddress(String bankAddress) {
        tvBankAddress_FOD.setText(bankAddress);
    }

    @Override
    public void setBankIBAN(String bankIBAN) {
        tvBankIBAN_FOD.setText(bankIBAN);
    }

    @Override
    public void setSwiftCode(String swiftCode) {
        tvSwiftCode_FOD.setText(swiftCode);
    }

    @Override
    public void setOwnerName(String ownerName) {
        tvOwnerName_FOD.setText(ownerName);
    }

    @Override
    public void setOwnerSite(String ownerSite) {
        tvOwnerSite_FOD.setText(ownerSite);
    }

    @Override
    public void setOwnerEmail(String ownerEmail) {
        tvOwnerEmail_FOD.setText(ownerEmail);
    }

    @Override
    public void setAdvice(String advice) {
        tvAdvice_FOD.setText(advice);
    }

    @Override
    public void showErrorState(String errorMessage, ErrorViewHelper.ErrorType errorType) {

    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
