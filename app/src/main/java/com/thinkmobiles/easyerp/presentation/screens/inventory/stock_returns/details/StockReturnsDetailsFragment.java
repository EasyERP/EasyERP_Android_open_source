package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.StockReturnRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.StockReturnsOrderRowAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by samson on 09.03.17.
 */

@EFragment
public class StockReturnsDetailsFragment extends ContentFragment implements StockReturnsDetailsContract.StockReturnsDetailsView {

    private StockReturnsDetailsContract.StockReturnsDetailsPresenter presenter;

    @FragmentArg
    protected String id;

    @ViewById
    protected TextView tvTitle_FSRD;
    @ViewById
    protected TextView tvReceivedBy_FSRD;
    @ViewById
    protected TextView tvDescription_FSRD;
    @ViewById
    protected TextView tvCompanyName_FSRD;
    @ViewById
    protected TextView tvCompanyAddress_FSRD;
    @ViewById
    protected TextView tvName_FSRD;
    @ViewById
    protected TextView tvDate_FSRD;
    @ViewById
    protected RecyclerView rvProductList_FSRD;

    @Bean
    protected StockReturnsOrderRowAdapter orderRowAdapter;
    @Bean
    protected StockReturnRepository stockReturnRepository;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_stock_returns_details;
    }

    @Override
    public StockReturnsDetailsContract.StockReturnsDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(StockReturnsDetailsContract.StockReturnsDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new StockReturnsDetailsPresenter(this, stockReturnRepository, id);
    }

    @AfterViews
    protected void initUI() {
        rvProductList_FSRD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProductList_FSRD.setAdapter(orderRowAdapter);
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Stock Returns details screen";
    }

    @Override
    public void setTitle(String title) {
        tvTitle_FSRD.setText(title);
    }

    @Override
    public void setReceivedBy(String receivedBy) {
        tvReceivedBy_FSRD.setText(receivedBy);
    }

    @Override
    public void setDescription(String description) {
        tvDescription_FSRD.setText(description);
    }

    @Override
    public void setCompanyName(String companyName) {
        tvCompanyName_FSRD.setText(companyName);
    }

    @Override
    public void setCompanyAddress(String companyAddress) {
        tvCompanyAddress_FSRD.setText(companyAddress);
    }

    @Override
    public void setName(String name) {
        tvName_FSRD.setText(name);
    }

    @Override
    public void setDate(String date) {
        tvDate_FSRD.setText(date);
    }

    @Override
    public void setProducts(ArrayList<OrderRowDH> rowDHs) {
        orderRowAdapter.setListDH(rowDHs);
    }
}
