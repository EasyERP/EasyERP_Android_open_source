package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.inventory.GoodsOutNotesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.OrderRowAdapter;
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
 * Created by samson on 06.03.17.
 */

@EFragment
public class GoodsOutNotesDetailsFragment extends ContentFragment implements GoodsOutNotesDetailsContract.GoodsOutNotesDetailsView {

    private GoodsOutNotesDetailsContract.GoodsOutNotesDetailsPresenter presenter;

    @FragmentArg
    protected String id;

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
    protected TextView tvPrinted_FGOND;
    @ViewById
    protected TextView tvPrintedDate_FGOND;
    @ViewById
    protected TextView tvPicked_FGOND;
    @ViewById
    protected TextView tvPickedDate_FGOND;
    @ViewById
    protected TextView tvPacked_FGOND;
    @ViewById
    protected TextView tvPackedDate_FGOND;
    @ViewById
    protected TextView tvShipped_FGOND;
    @ViewById
    protected TextView tvShippedDate_FGOND;
    @ViewById
    protected EditText etDate_FGOND;
    @ViewById
    protected EditText etTrackingReference_FGOND;
    @ViewById
    protected EditText etShippingMethod_FGOND;

    @ViewById
    protected RecyclerView rvProductList_FGOND;

    @ColorRes(R.color.color_text_gray)
    protected int colorGray;
    @ColorRes(R.color.color_text_black)
    protected int colorBlack;

    @StringRes(R.string.not_printed)
    protected String notPrinted;
    @StringRes(R.string.not_picked)
    protected String notPicked;
    @StringRes(R.string.not_packed)
    protected String notPacked;
    @StringRes(R.string.not_shipped)
    protected String notShipped;

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

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Goods-Out Notes details screen";
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
        fulfillStatusCard(print, notPrinted,
                R.drawable.ic_print, R.drawable.ic_print_off,
                tvPrinted_FGOND, tvPrintedDate_FGOND);
    }

    @Override
    public void setPick(String pick) {
        fulfillStatusCard(pick, notPicked,
                R.drawable.ic_allocated, R.drawable.ic_allocated_off,
                tvPicked_FGOND, tvPickedDate_FGOND);
    }

    @Override
    public void setPack(String pack) {
        fulfillStatusCard(pack, notPacked,
                R.drawable.ic_fulfilled, R.drawable.ic_fulfilled_off,
                tvPacked_FGOND, tvPackedDate_FGOND);
    }

    @Override
    public void setShip(String ship) {
        fulfillStatusCard(ship, notShipped,
                R.drawable.ic_shipped, R.drawable.ic_shipped_off,
                tvShipped_FGOND, tvShippedDate_FGOND);
    }

    @Override
    public void setReference(String reference) {
        etTrackingReference_FGOND.setText(reference);
    }

    @Override
    public void setShipping(String shipping) {
        etShippingMethod_FGOND.setText(shipping);
    }

    @Override
    public void setDate(String date) {
        etDate_FGOND.setText(date);
    }

    @Override
    public void setProducts(ArrayList<OrderRowDH> rowDHs) {
        rowAdapter.setListDH(rowDHs);
    }

    private void fulfillStatusCard(String date, String noDate, int drawableRes, int drawableOffRes, TextView tvTitle, TextView tvDate) {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), TextUtils.isEmpty(date) ? drawableOffRes : drawableRes);
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tvDate.setText(TextUtils.isEmpty(date) ? noDate : date);
        tvDate.setTextColor(TextUtils.isEmpty(date) ? colorGray : colorBlack);
    }
}
