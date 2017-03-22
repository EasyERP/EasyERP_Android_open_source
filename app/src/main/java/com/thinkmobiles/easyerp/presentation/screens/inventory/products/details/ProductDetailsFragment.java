package com.thinkmobiles.easyerp.presentation.screens.inventory.products.details;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ismaeltoe.FlowLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.Price;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.PriceList;
import com.thinkmobiles.easyerp.domain.inventory.ProductRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.ProductImageAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.ProductVariantAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.SalesChannelAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.StockInventoryAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductImageDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.ProductVariantDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.SalesChannelDH;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.StockInventoryDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.HistoryAnimationHelper;
import com.thinkmobiles.easyerp.presentation.screens.gallery.GalleryActivity_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by samson on 13.03.17.
 */

@EFragment
public class ProductDetailsFragment extends ContentFragment implements ProductDetailsContract.ProductDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_product_details;
    }

    private ProductDetailsContract.ProductDetailsPresenter presenter;
    private LinearLayoutManager imageLLM;

    @FragmentArg
    protected String id;

    @ViewById
    protected NestedScrollView nsvContent_FPD;
    @ViewById
    protected ImageView ivProductImage_FPD;
    @ViewById
    protected TextView tvProductName_FPD;
    @ViewById
    protected TextView tvActions_FPD;
    @ViewById
    protected TextView tvProductType_FPD;
    @ViewById
    protected FlowLayout flCategories_FPD;
    @ViewById
    protected TextView tvDescription_FPD;
    @ViewById
    protected EditText etSKU_FPD;
    @ViewById
    protected EditText etUPC_FPD;
    @ViewById
    protected EditText etISBN_FPD;
    @ViewById
    protected EditText etEAN_FPD;
    @ViewById
    protected EditText etWeight_FPD;
    @ViewById
    protected LinearLayout llStockInventoryHeader_FPD;
    @ViewById
    protected RecyclerView rvStockInventory_FPD;
    @ViewById
    protected TextView tvEmptyStockInventory_FPD;
    @ViewById
    protected Spinner spPrices_FPD;
    @ViewById
    protected LinearLayout llSalesChannelHeader_FPD;
    @ViewById
    protected RecyclerView rvSalesChannel_FPD;
    @ViewById
    protected TextView tvEmptySalesChannel_FPD;
    @ViewById
    protected RecyclerView rvImages_FPD;
    @ViewById
    protected TextView tvEmptyImages_FPD;
    @ViewById
    protected EditText etCount1_FPD;
    @ViewById
    protected EditText etPrice1_FPD;
    @ViewById
    protected EditText etCount2_FPD;
    @ViewById
    protected EditText etPrice2_FPD;
    @ViewById
    protected EditText etCount3_FPD;
    @ViewById
    protected EditText etPrice3_FPD;
    @ViewById
    protected EditText etCount4_FPD;
    @ViewById
    protected EditText etPrice4_FPD;
    @ViewById
    protected EditText etCount5_FPD;
    @ViewById
    protected EditText etPrice5_FPD;

    @ViewById
    protected TextView tvTitleHistory;
    @ViewById
    protected ImageView ivIconArrow;
    @ViewById
    protected RecyclerView rvHistory;
    @ViewById
    protected View btnHistory;

    @Bean
    protected ProductRepository productRepository;
    @Bean
    protected HistoryAnimationHelper animationHelper;
    @Bean
    protected ProductImageAdapter imageAdapter;
    @Bean
    protected StockInventoryAdapter inventoryAdapter;
    @Bean
    protected SalesChannelAdapter channelAdapter;
    @Bean
    protected ProductVariantAdapter variantAdapter;
    private ArrayAdapter<String> priceAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new ProductDetailsPresenter(this, productRepository, id);
        priceAdapter = new ArrayAdapter<>(getActivity(), R.layout.view_list_item_product_price, R.id.tvPriceListName_LIPP);
        variantAdapter.setOnCardClickListener((view, position, viewType) -> presenter.changeProductVariant(position));
        imageAdapter.setOnCardClickListener((view, position, viewType) -> presenter.openGallery(view, position));
    }

    @AfterViews
    protected void initUI() {
        animationHelper.init(ivIconArrow, rvHistory, nsvContent_FPD);
        tvTitleHistory.setText(R.string.product_details_variants);
        imageLLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvImages_FPD.setLayoutManager(imageLLM);
        rvImages_FPD.setAdapter(imageAdapter);
        rvStockInventory_FPD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStockInventory_FPD.setAdapter(inventoryAdapter);
        rvSalesChannel_FPD.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSalesChannel_FPD.setAdapter(channelAdapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setAdapter(variantAdapter);

        spPrices_FPD.setAdapter(priceAdapter);
        spPrices_FPD.setSelection(0);
        spPrices_FPD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.changePriceList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RxView.clicks(btnHistory)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.changeVisibilityVariants());

        getPresenter().subscribe();
    }

    @Override
    public ProductDetailsContract.ProductDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(ProductDetailsContract.ProductDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Product details screen";
    }

    @Override
    public void setImages(ArrayList<ProductImageDH> images) {
        if (images.isEmpty()) {
            tvEmptyImages_FPD.setVisibility(View.VISIBLE);
        } else {
            tvEmptyImages_FPD.setVisibility(View.GONE);
            rvImages_FPD.setVisibility(View.VISIBLE);
        }
        imageAdapter.setListDH(images);
    }

    @Override
    public void setPriceLists(ArrayList<PriceList> prices) {
        priceAdapter.clear();
        for (PriceList item : prices) {
            priceAdapter.add(item.priceLists.name);
        }
    }

    @Override
    public void setPrices(ArrayList<Price> prices) {
        etCount1_FPD.setText(String.valueOf(prices.get(0).count));
        etPrice1_FPD.setText(String.valueOf(prices.get(0).price));
        etCount2_FPD.setText(String.valueOf(prices.get(1).count));
        etPrice2_FPD.setText(String.valueOf(prices.get(1).price));
        etCount3_FPD.setText(String.valueOf(prices.get(2).count));
        etPrice3_FPD.setText(String.valueOf(prices.get(2).price));
        etCount4_FPD.setText(String.valueOf(prices.get(3).count));
        etPrice4_FPD.setText(String.valueOf(prices.get(3).price));
        etCount5_FPD.setText(String.valueOf(prices.get(4).count));
        etPrice5_FPD.setText(String.valueOf(prices.get(4).price));
    }

    @Override
    public void setVariants(ArrayList<ProductVariantDH> variants) {
        variantAdapter.setListDH(variants);
    }

    @Override
    public void setStockInventory(ArrayList<StockInventoryDH> inventory) {
        if (inventory.isEmpty()) {
            tvEmptyStockInventory_FPD.setVisibility(View.VISIBLE);
            llStockInventoryHeader_FPD.setVisibility(View.GONE);
        } else {
            tvEmptyStockInventory_FPD.setVisibility(View.GONE);
            llStockInventoryHeader_FPD.setVisibility(View.VISIBLE);
            rvStockInventory_FPD.setVisibility(View.VISIBLE);
        }
        inventoryAdapter.setListDH(inventory);
    }

    @Override
    public void setSalesChannels(ArrayList<SalesChannelDH> channels) {
        if (channels.isEmpty()) {
            tvEmptySalesChannel_FPD.setVisibility(View.VISIBLE);
            llSalesChannelHeader_FPD.setVisibility(View.GONE);
        } else {
            tvEmptySalesChannel_FPD.setVisibility(View.GONE);
            llSalesChannelHeader_FPD.setVisibility(View.VISIBLE);
            rvSalesChannel_FPD.setVisibility(View.VISIBLE);
        }
        channelAdapter.setListDH(channels);
    }

    @Override
    public void setProductImage(String url) {
        Picasso.with(getActivity())
                .load(url)
                .into(ivProductImage_FPD);
    }

    @Override
    public void setProductName(String productName) {
        tvProductName_FPD.setText(productName);
    }

    @Override
    public void setActions(String actions) {
        tvActions_FPD.setText(actions);
    }

    @Override
    public void setCategories(ArrayList<FilterItem> categories) {
        flCategories_FPD.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (FilterItem item : categories) {
            TextView tvTag = (TextView) inflater.inflate(R.layout.view_text_tag, flCategories_FPD, false);
            tvTag.setBackground(new RoundRectDrawable(ColorUtils.setAlphaComponent(ContextCompat.getColor(getActivity(), R.color.color_status_in_progress), 150)));
            tvTag.setText(item.name);
            flCategories_FPD.addView(tvTag);
        }
    }

    @Override
    public void setProductType(String productType) {
        tvProductType_FPD.setText(productType);
    }

    @Override
    public void setDescription(String description) {
        tvDescription_FPD.setText(description);
    }

    @Override
    public void setSKU(String SKU) {
        etSKU_FPD.setText(SKU);
    }

    @Override
    public void setUPC(String UPC) {
        etUPC_FPD.setText(UPC);
    }

    @Override
    public void setISBN(String ISBN) {
        etISBN_FPD.setText(ISBN);
    }

    @Override
    public void setEAN(String EAN) {
        etEAN_FPD.setText(EAN);
    }

    @Override
    public void setWeight(String weight) {
        etWeight_FPD.setText(weight);
    }

    @Override
    public void showVariants(boolean enable) {
        if (enable) {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Variants");
            animationHelper.open();
        } else {
            animationHelper.close();
        }
    }

    @Override
    public void openGallery(View view, int position, String title, ArrayList<ImageItem> images) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                imageLLM.findContainingItemView(view).findViewById(R.id.ivProductImage_LISI), "gallery" + position
        );

        GalleryActivity_.intent(getActivity())
                .imageItems(images)
                .position(position)
                .title(title)
                .withOptions(options.toBundle())
                .start();
    }
}
