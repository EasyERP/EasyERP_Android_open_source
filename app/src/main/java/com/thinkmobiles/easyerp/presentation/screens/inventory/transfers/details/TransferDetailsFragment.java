package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.TransfersRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.AttachmentAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.TransfersRowAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferRowDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.utils.IntentActionHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/9/2017.
 */

@EFragment(R.layout.fragment_transfer_details)
public class TransferDetailsFragment extends ContentFragment implements TransferDetailsContract.TransferDetailsView {

    private TransferDetailsContract.TransferDetailsPresenter presenter;

    @FragmentArg
    protected String id;

    @Bean
    protected AttachmentAdapter attachmentAdapter;
    @Bean
    protected TransfersRowAdapter transfersRowAdapter;
    @Bean
    protected TransfersRepository transfersRepository;

    //region View inject
    @ViewById
    protected TextView tvTitle_FTD;
    @ViewById
    protected TextView tvPrinted_FTD;
    @ViewById
    protected TextView tvPrintedDate_FTD;
    @ViewById
    protected EditText etDate_FTD;
    @ViewById
    protected EditText etTrackingReference_FTD;
    @ViewById
    protected EditText etShippingMethod_FLD;

    @ViewById
    protected TextView tvPacked_FTD;
    @ViewById
    protected TextView tvPackedDate_FTD;
    @ViewById
    protected TextView tvShipped_FTD;
    @ViewById
    protected TextView tvShippedDate_FTD;
    @ViewById
    protected TextView tvReceived_FTD;
    @ViewById
    protected TextView tvReceivedDate_FTD;
    @ViewById
    protected TextView tvCompanyName_FTD;
    @ViewById
    protected TextView tvCompanyAddress_FTD;
    @ViewById
    protected TextView tvName_FTD;
    @ViewById
    protected TextView tvWarehouseTo_FTD;
    @ViewById
    protected TextView tvWarehouseToCountry_FTD;
    @ViewById
    protected RecyclerView rvProductList_FTD;
    @ViewById
    protected TextView tvEmptyAttachments_FLD;
    @ViewById
    protected RecyclerView rvAttachments_FLD;
    //endregion

    @ColorRes(R.color.color_text_gray)
    protected int colorGray;
    @ColorRes(R.color.color_text_black)
    protected int colorBlack;

    @StringRes(R.string.not_printed)
    protected String notPrinted;
    @StringRes(R.string.not_packed)
    protected String notPacked;
    @StringRes(R.string.not_shipped)
    protected String notShipped;
    @StringRes(R.string.not_received)
    protected String notReceived;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_transfer_details;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new TransferDetailsPresenter(this, transfersRepository, id);
    }

    @AfterViews
    protected void initUI() {
        rvAttachments_FLD.setAdapter(attachmentAdapter);
        rvAttachments_FLD.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        attachmentAdapter.setOnCardClickListener((view, position, viewType) -> {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_ATTACHMENT, "");
            presenter.startAttachment(position);
        });
        rvProductList_FTD.setAdapter(transfersRowAdapter);
        rvProductList_FTD.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setPresenter(TransferDetailsContract.TransferDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Transfer Details screen";
    }

    @Override
    public void setTitle(String title) {
        tvTitle_FTD.setText(title);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FTD.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FTD.setText(companyAddress);
    }

    @Override
    public void setName(String name) {
        tvName_FTD.setText(name);
    }

    @Override
    public void setWarehouseTo(String warehouseFrom) {
        tvWarehouseTo_FTD.setText(warehouseFrom);
    }

    @Override
    public void setWarehouseToCountry(String warehouseFromCountry) {
        tvWarehouseToCountry_FTD.setText(warehouseFromCountry);
    }

    @Override
    public void setPrint(String print) {
        fulfillTransferStatusCard(print, notPrinted,
                R.drawable.ic_print, R.drawable.ic_print_off,
                tvPrinted_FTD, tvPrintedDate_FTD);
    }

    @Override
    public void setPack(String pack) {
        fulfillTransferStatusCard(pack, notPacked,
                R.drawable.ic_fulfilled, R.drawable.ic_fulfilled_off,
                tvPacked_FTD, tvPackedDate_FTD);
    }

    @Override
    public void setShip(String ship) {
        fulfillTransferStatusCard(ship, notShipped,
                R.drawable.ic_shipped, R.drawable.ic_shipped_off,
                tvShipped_FTD, tvShippedDate_FTD);
    }

    @Override
    public void setReceive(String receive) {
        fulfillTransferStatusCard(receive, notReceived,
                R.drawable.ic_received, R.drawable.ic_received_off,
                tvReceived_FTD, tvReceivedDate_FTD);
    }

    @Override
    public void setTrackingReference(String reference) {
        etTrackingReference_FTD.setText(reference);
    }

    @Override
    public void setShippingMethod(String shipping) {
        etShippingMethod_FLD.setText(shipping);
    }

    @Override
    public void setDate(String date) {
        etDate_FTD.setText(date);
    }

    @Override
    public void setTransferRows(ArrayList<TransferRowDH> rowDHs) {
        transfersRowAdapter.setListDH(rowDHs);
    }

    @Override
    public void setAttachments(ArrayList<AttachmentDH> attachmentDHs) {
        tvEmptyAttachments_FLD.setVisibility(View.GONE);
        attachmentAdapter.setListDH(attachmentDHs);
    }

    @Override
    public void showAttachments(boolean isShown) {
        if (!isShown) tvEmptyAttachments_FLD.setVisibility(View.VISIBLE);
    }

    @Override
    public void startUrlIntent(String url) {
        IntentActionHelper.callViewIntent(mActivity, url, null);
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    private void fulfillTransferStatusCard(String date, String noDate, int drawableRes, int drawableOffRes, TextView tvTitle, TextView tvDate) {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), TextUtils.isEmpty(date) ? drawableOffRes : drawableRes);
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tvDate.setText(TextUtils.isEmpty(date) ? noDate : date);
        tvDate.setTextColor(TextUtils.isEmpty(date) ? colorGray : colorBlack);
    }

}
