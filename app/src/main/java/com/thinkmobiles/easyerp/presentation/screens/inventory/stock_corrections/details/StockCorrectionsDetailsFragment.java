package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.StockCorrectionRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.ProductAdjustedAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.OrderRowDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

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
    protected EditText etWarehouse_FSCD;
    @ViewById
    protected EditText etDate_FSCD;
    @ViewById
    protected EditText etAdjustedBy_FSCD;
    @ViewById
    protected TextView tvDescription_FSCD;
    @ViewById
    protected RecyclerView rvProducts_FSCD;

    @StringRes(R.string.description)
    protected String strDescription;

    @ColorRes(R.color.color_text_black)
    protected int textColorBlack;
    @ColorRes(R.color.color_text_gray)
    protected int textColorGrey;

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

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
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
        etDate_FSCD.setText(date);
    }

    @Override
    public void setWarehouse(String warehouse) {
        etWarehouse_FSCD.setText(warehouse);
    }

    @Override
    public void setDescription(String description) {
        tvDescription_FSCD.setMovementMethod(new ScrollingMovementMethod());
        tvDescription_FSCD.setText(TextUtils.isEmpty(description) ? strDescription : description);
        tvDescription_FSCD.setTextColor(TextUtils.isEmpty(description) ? textColorGrey : textColorBlack);
    }

    @Override
    public void setAdjustedBy(String adjustedBy) {
        etAdjustedBy_FSCD.setText(adjustedBy);
    }

    @Override
    public void setProducts(ArrayList<OrderRowDH> list) {
        productAdjustedAdapter.setListDH(list);
    }
}
