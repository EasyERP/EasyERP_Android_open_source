package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.OrderProductAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OrderProductDH;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DrawableRes;
import org.androidannotations.annotations.res.IntegerRes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


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
    protected TextView tvDiscountTitle_FOD;
    @ViewById
    protected TextView tvTaxes_FOD;
    @ViewById
    protected TextView tvTotal_FOD;
    @ViewById
    protected TextView tvPrepaid_FOD;
    @ViewById
    protected TextView tvPrepaidTitle_FOD;
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
    protected TextView tvAttachments_FOD;
    @ViewById
    protected FrameLayout btnHistory_FOD;
    @ViewById
    protected ImageView ivIconArrow_FOD;
    @ViewById
    protected View viewHistoryDivider_FOD;
    @ViewById
    protected RecyclerView rvHistory_FOD;
    @ViewById
    protected LinearLayout llErrorLayout;

    @DrawableRes(R.drawable.ic_arrow_up)
    protected Drawable icArrowUp;
    @DrawableRes(R.drawable.ic_arrow_down)
    protected Drawable icArrowDown;
    @IntegerRes(android.R.integer.config_longAnimTime)
    protected int animDuration;

    @Bean
    protected OrderRepository orderRepository;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected OrderProductAdapter productAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    private OrderDetailsContract.OrderDetailsPresenter presenter;

    private AnimatorSet set;
    private ObjectAnimator rotate;
    private ValueAnimator.AnimatorUpdateListener scaleListener;

    @AfterInject
    @Override
    public void initPresenter() {
        new OrderDetailsPresenter(this, orderRepository, orderId);
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(llErrorLayout, v -> presenter.subscribe());

        rvHistory_FOD.setAdapter(historyAdapter);
        rvHistory_FOD.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvProductList_FOD.setAdapter(productAdapter);
        rvProductList_FOD.setLayoutManager(new LinearLayoutManager(getActivity()));


        RxView.clicks(btnHistory_FOD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        srlRefresh_FOD.setOnRefreshListener(() -> presenter.refresh());

        set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.setDuration(animDuration);

        rotate = ObjectAnimator.ofFloat(ivIconArrow_FOD, View.ROTATION, 0, 0);

        scaleListener = animation -> {
            int height = (int) animation.getAnimatedValue();
            int visibility = height == 0 ? View.GONE : View.VISIBLE;
            rvHistory_FOD.setVisibility(visibility);
            ViewGroup.LayoutParams params = rvHistory_FOD.getLayoutParams();
            params.height = height;
            rvHistory_FOD.setLayoutParams(params);
        };


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
            errorViewHelper.hideError();
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
        ValueAnimator scale = new ValueAnimator();
        scale.addUpdateListener(scaleListener);
        if (enable) {
            scale.setIntValues(0, nsvContent_FOD.getHeight());
            rotate.setFloatValues((float)rotate.getAnimatedValue(), 180);
        } else {
            scale.setIntValues(rvHistory_FOD.getHeight(), 0);
            rotate.setFloatValues((float)rotate.getAnimatedValue(), 0);
        }
        set.playTogether(scale, rotate);
        if (enable && rvHistory_FOD.getVisibility() == View.GONE
                || !enable && rvHistory_FOD.getVisibility() == View.VISIBLE)
            set.start();
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
        tvDiscountTitle_FOD.setVisibility(View.VISIBLE);
        tvDiscount_FOD.setVisibility(View.VISIBLE);
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
        tvPrepaidTitle_FOD.setVisibility(View.VISIBLE);
        tvPrepaid_FOD.setVisibility(View.VISIBLE);
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
    public void setAttachments(String attachments) {
        tvAttachments_FOD.setText(Html.fromHtml(attachments));
    }

    @Override
    public void setAdvice(String advice) {
        tvAdvice_FOD.setText(advice);
    }

    @Override
    public void setHistory(ArrayList<HistoryDH> history) {
        historyAdapter.setListDH(history);
    }

    @Override
    public void setProducts(ArrayList<OrderProductDH> products) {
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
