package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.GoodsOutNotesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.OrderRowAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by samson on 06.03.17.
 */

@EFragment
public class GoodsOutNotesDetailsFragment extends ContentFragment implements GoodsOutNotesDetailsContract.GoodsOutNotesDetailsView {

    private GoodsOutNotesDetailsContract.GoodsOutNotesDetailsPresenter presenter;

    @ViewById
    protected TextView tvTitle_FGOND;
    @ViewById
    protected TextView tvCompanyName_FGOND;
    @ViewById
    protected TextView tvCompanyAddress_FGOND;
    @ViewById
    protected TextView tvName_FGOND;
    @ViewById
    protected TextView tvSupplierName_FGOND;
    @ViewById
    protected TextView tvSupplierAddress_FGOND;
    @ViewById
    protected TextView tvPrint_FGOND;
    @ViewById
    protected TextView tvPick_FGOND;
    @ViewById
    protected TextView tvPack_FGOND;
    @ViewById
    protected TextView tvShip_FGOND;
    @ViewById
    protected TextView tvReference_FGOND;
    @ViewById
    protected TextView tvShipping_FGOND;
    @ViewById
    protected TextView tvDate_FGOND;
    @ViewById
    protected RecyclerView rvProductList_FGOND;

    @FragmentArg
    protected String id;

    @Bean
    protected GoodsOutNotesRepository notesRepository;
    @Bean
    protected OrderRowAdapter rowAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_goods_out_notes_details;
    }

    @Override
    public GoodsOutNotesDetailsContract.GoodsOutNotesDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(GoodsOutNotesDetailsContract.GoodsOutNotesDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new GoodsOutNotesDetailsPresenter(this, notesRepository, id);
    }

    @AfterViews
    protected void initUI() {
        rvProductList_FGOND.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProductList_FGOND.setAdapter(rowAdapter);
    }

    @Override
    public String getScreenName() {
        return "Goods-Out Notes Details Screen";
    }

    @Override
    public void setTitle(String title) {
                tvTitle_FGOND.setText(title);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FGOND.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FGOND.setText(companyAddress);
    }

    @Override
    public void setName(String name) {
        tvName_FGOND.setText(name);
    }

    @Override
    public void setSupplierName(String supplierName) {
        tvSupplierName_FGOND.setText(supplierName);
    }

    @Override
    public void setSupplierAddress(String supplierAddress) {
        tvSupplierAddress_FGOND.setText(supplierAddress);
    }

    @Override
    public void setPrint(String print) {
        tvPrint_FGOND.setText(print);
    }

    @Override
    public void setPick(String pick) {
        tvPick_FGOND.setText(pick);
    }

    @Override
    public void setPack(String pack) {
        tvPack_FGOND.setText(pack);
    }

    @Override
    public void setShip(String ship) {
        tvShip_FGOND.setText(ship);
    }

    @Override
    public void setReference(String reference) {
        tvReference_FGOND.setText(reference);
    }

    @Override
    public void setShipping(String shipping) {
        tvShipping_FGOND.setText(shipping);
    }

    @Override
    public void setDate(String date) {
        tvDate_FGOND.setText(date);
    }

    @Override
    public void setProducts(ArrayList<OrderRowDH> rowDHs) {
        rowAdapter.setListDH(rowDHs);
    }
}
