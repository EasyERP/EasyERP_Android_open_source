package com.thinkmobiles.easyerp.presentation.screens.crm.orders.details;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.DomainHelper;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.HistoryAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.ProductAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ProductDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@EFragment
public class OrderDetailsFragment extends ContentFragment implements OrderDetailsContract.OrderDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order_details;
    }

    @FragmentArg
    protected String orderId;
    @FragmentArg
    protected int moduleId;

    @ViewById
    protected NestedScrollView nsvContent_FOD;
    @ViewById
    protected TextView tvTitleOrder_FOD;
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
    protected TextView tvEmptyAttachments_FOD;
    @ViewById
    protected RecyclerView rvAttachments_FOD;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvHistory;

    protected OrderRepository orderRepository;
    @Bean
    protected HistoryAdapter historyAdapter;
    @Bean
    protected ProductAdapter productAdapter;
    @Bean
    protected HistoryAnimationHelper animationHelper;
    @Bean
    protected AttachmentAdapter attachmentAdapter;

    private OrderDetailsContract.OrderDetailsPresenter presenter;

    @AfterInject
    @Override
    public void initPresenter() {
        orderRepository = DomainHelper.getOrderRepository(moduleId);
        new OrderDetailsPresenter(this, orderRepository, orderId);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        rvHistory.setAdapter(historyAdapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvAttachments_FOD.setAdapter(attachmentAdapter);
        rvAttachments_FOD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        attachmentAdapter.setOnCardClickListener((view, position, viewType) -> {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_ATTACHMENT, "");
            presenter.startAttachment(position);
        });

        rvProductList_FOD.setAdapter(productAdapter);
        rvProductList_FOD.setLayoutManager(new LinearLayoutManager(getActivity()));

        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeNotesVisibility());

        animationHelper.init(ivIconArrow, rvHistory);
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        animationHelper.cancel();
    }

    public void setPresenter(OrderDetailsContract.OrderDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return String.format("%s Order details screen", MenuConfigs.getModuleLabel(moduleId));
    }

    @Override
    public void showHistory(boolean enable) {
        if (enable && rvHistory.getVisibility() == View.GONE) {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "History");
            animationHelper.forward(nsvContent_FOD.getHeight());
        }
        if (!enable && rvHistory.getVisibility() == View.VISIBLE)
            animationHelper.backward(rvHistory.getHeight());
    }

    @Override
    public void setOrderStatusName(String orderStatus) {
        tvOrderStatus_FOD.setText(orderStatus);
    }

    @Override
    public void setOrderStatus(String orderStatus) {
        tvOrderStatus_FOD.setBackgroundResource(TagHelper.getColorResIdByName(orderStatus));
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
        tvTitleOrder_FOD.setText(orderName);
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
    public void setAttachments(ArrayList<AttachmentDH> attachments) {
        tvEmptyAttachments_FOD.setVisibility(View.GONE);
        attachmentAdapter.setListDH(attachments);
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
    public void setProducts(ArrayList<ProductDH> products) {
        productAdapter.setListDH(products);
    }

    @Override
    public void startUrlIntent(String url) {
        IntentActionHelper.callViewIntent(mActivity, url, null);
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

}
