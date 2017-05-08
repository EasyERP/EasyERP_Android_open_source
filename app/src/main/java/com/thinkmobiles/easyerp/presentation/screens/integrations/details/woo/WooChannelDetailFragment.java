package com.thinkmobiles.easyerp.presentation.screens.integrations.details.woo;

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
public class WooChannelDetailFragment extends IntegrationChannelDetailFragment implements WooChannelDetailContract.WooChannelView {

    private WooChannelDetailContract.WooChannelPresenter presenter;

    @ViewById
    protected EditText etChannelName_FCWD, etBaseUrl_FCWD, etConsumerKey_FCWD, etConsumerSecret_FCWD, etVersion_FCWD;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel_woo_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new WooChannelDetailPresenter(this, integrationsRepository, channel);
    }

    @Override
    public void setPresenter(WooChannelDetailContract.WooChannelPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Integrations | Woo details screen";
    }

    @Override
    public void displayChannelName(String channelName) {
        etChannelName_FCWD.setText(channelName);
    }

    @Override
    public void displayBaseUrl(String url) {
        etBaseUrl_FCWD.setText(url);
    }

    @Override
    public void displayConsumerKey(String consumerKey) {
        etConsumerKey_FCWD.setText(consumerKey);
    }

    @Override
    public void displayConsumerSecret(String consumerSecret) {
        etConsumerSecret_FCWD.setText(consumerSecret);
    }

    @Override
    public void displayVersion(String version) {
        etVersion_FCWD.setText(version);
    }

    @Override
    protected void changeConnectState() {
        presenter.changeConnectState();
    }
}
