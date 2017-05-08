package com.thinkmobiles.easyerp.presentation.screens.integrations.details.etsy;

import android.view.View;
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
public class EtsyChannelDetailFragment extends IntegrationChannelDetailFragment implements EtsyChannelDetailContract.EtsyChannelView {

    private EtsyChannelDetailContract.EtsyChannelPresenter presenter;

    @ViewById
    protected EditText etChannelName_FCED, etShopName_FCED, etKeyString_FCED, etSharedSecret_FCED;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel_etsy_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new EtsyChannelDetailPresenter(this, integrationsRepository, channel);
    }

    @Override
    public void setPresenter(EtsyChannelDetailContract.EtsyChannelPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Integrations | Etsy details screen";
    }

    @Override
    public void displayChannelName(String channelName) {
        etChannelName_FCED.setText(channelName);
    }

    @Override
    public void displayShopName(String shopName) {
        etShopName_FCED.setText(shopName);
    }

    @Override
    public void displayKeyString(String keyString) {
        etKeyString_FCED.setText(keyString);
    }

    @Override
    public void displaySharedSecret(String sharedSecret) {
        etSharedSecret_FCED.setText(sharedSecret);
    }

    @Override
    public void displayBankAccount(String bankAccount) {
        tilContainerBankAccount_VICWS.setVisibility(View.VISIBLE);
        etBankAccount_VICWS.setText(bankAccount);
    }

    @Override
    protected void changeConnectState() {
        presenter.changeConnectState();
    }
}
