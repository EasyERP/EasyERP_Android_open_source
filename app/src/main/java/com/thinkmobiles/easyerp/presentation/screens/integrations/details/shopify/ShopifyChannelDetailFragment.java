package com.thinkmobiles.easyerp.presentation.screens.integrations.details.shopify;

import android.widget.EditText;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public class ShopifyChannelDetailFragment extends IntegrationChannelDetailFragment implements ShopifyChannelDetailContract.ShopifyChannelView {

    private ShopifyChannelDetailContract.ShopifyChannelPresenter presenter;

    @ViewById
    protected EditText etChannelName_FCSD, etBaseUrl_FCSD, etApiKey_FCSD, etApiSecret_FCSD;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel_shopify_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new ShopifyChannelDetailPresenter(this, channel);
    }

    @Override
    public void setPresenter(ShopifyChannelDetailContract.ShopifyChannelPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @AfterViews
    protected void initUI() {
        getPresenter().subscribe();
    }

    @Override
    public String getScreenName() {
        return "Integrations | Shopify details screen";
    }

    @Override
    public void displayChannelName(String channelName) {
        etChannelName_FCSD.setText(channelName);
    }

    @Override
    public void displayBaseUrl(String url) {
        etBaseUrl_FCSD.setText(url);
    }

    @Override
    public void displayApiKey(String apiKey) {
        etApiKey_FCSD.setText(apiKey);
    }

    @Override
    public void displayApiSecret(String apiSecret) {
        etApiSecret_FCSD.setText(apiSecret);
    }
}
