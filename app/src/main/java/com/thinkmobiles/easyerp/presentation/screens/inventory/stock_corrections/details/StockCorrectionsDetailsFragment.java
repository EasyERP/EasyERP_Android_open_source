package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.StockCorrectionRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.ProductAdjustedAdapter;
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
 * Created by samson on 10.03.17.
 */

@EFragment
public class StockCorrectionsDetailsFragment extends ContentFragment implements StockCorrectionsDetailsContract.StockCorrectionsDetailsView {

    private StockCorrectionsDetailsContract.StockCorrectionsDetailsPresenter presenter;

    @FragmentArg
    protected String id;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_stock_correction_details;
    }

    @ViewById
    protected TextView tvTitle_FSCD;
    @ViewById
    protected TextView tvDate_FSCD;
    @ViewById
    protected TextView tvWarehouse_FSCD;
    @ViewById
    protected TextView tvDescription_FSCD;
    @ViewById
    protected TextView tvAdjustedBy_FSCD;
    @ViewById
    protected RecyclerView rvProducts_FSCD;

    @Bean
    protected StockCorrectionRepository stockCorrectionRepository;
    @Bean
    protected ProductAdjustedAdapter productAdjustedAdapter;


    @AfterInject
    @Override
    public void initPresenter() {
        new StockCorrectionsDetailsPresenter(this, stockCorrectionRepository, id);
    }

    @AfterViews
    protected void initUI() {
        rvProducts_FSCD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProducts_FSCD.setAdapter(productAdjustedAdapter);
    }

    @Override
    public StockCorrectionsDetailsContract.StockCorrectionsDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(StockCorrectionsDetailsContract.StockCorrectionsDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Stock Corrections details screen";
    }

    @Override
    public void setTitle(String title) {
        tvTitle_FSCD.setText(title);
    }

    @Override
    public void setDate(String date) {
        tvDate_FSCD.setText(date);
    }

    @Override
    public void setWarehouse(String warehouse) {
        tvWarehouse_FSCD.setText(warehouse);
    }

    @Override
    public void setDescription(String description) {
        tvDescription_FSCD.setText(description);
    }

    @Override
    public void setAdjustedBy(String adjustedBy) {
        tvAdjustedBy_FSCD.setText(adjustedBy);
    }

    @Override
    public void setProducts(ArrayList<OrderRowDH> list) {
        productAdjustedAdapter.setListDH(list);
    }
}
