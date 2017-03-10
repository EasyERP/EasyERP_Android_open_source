package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    protected TextView tvReference_FTD;
    @ViewById
    protected TextView tvShipping_FTD;
    @ViewById
    protected TextView tvPrint_FTD;
    @ViewById
    protected TextView tvPack_FTD;
    @ViewById
    protected TextView tvShip_FTD;
    @ViewById
    protected TextView tvReceive_FTD;
    @ViewById
    protected TextView tvCompanyName_FTD;
    @ViewById
    protected TextView tvCompanyAddress_FTD;
    @ViewById
    protected TextView tvName_FTD;
    @ViewById
    protected TextView tvDate_FTD;
    @ViewById
    protected TextView tvWarehouseFrom_FTD;
    @ViewById
    protected TextView tvWarehouseFromCountry_FTD;
    @ViewById
    protected RecyclerView rvProductList_FTD;
    @ViewById
    protected TextView tvEmptyAttachments_FLD;
    @ViewById
    protected RecyclerView rvAttachments_FLD;
    //endregion


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
    public void setWarehouseFrom(String warehouseFrom) {
        tvWarehouseFrom_FTD.setText(warehouseFrom);
    }

    @Override
    public void setWarehouseFromCountry(String warehouseFromCountry) {
        tvWarehouseFromCountry_FTD.setText(warehouseFromCountry);
    }

    @Override
    public void setPrint(String print) {
        tvPrint_FTD.setText(print);
    }

    @Override
    public void setPack(String pack) {
        tvPack_FTD.setText(pack);
    }

    @Override
    public void setShip(String ship) {
        tvShip_FTD.setText(ship);
    }

    @Override
    public void setReceive(String receive) {
        tvReceive_FTD.setText(receive);
    }

    @Override
    public void setReference(String reference) {
        tvReference_FTD.setText(reference);
    }

    @Override
    public void setShipping(String shipping) {
        tvShipping_FTD.setText(shipping);
    }

    @Override
    public void setDate(String date) {
        tvDate_FTD.setText(date);
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
        if(!isShown) tvEmptyAttachments_FLD.setVisibility(View.VISIBLE);
    }

    @Override
    public void startUrlIntent(String url) {
        IntentActionHelper.callViewIntent(mActivity, url, null);
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

}
