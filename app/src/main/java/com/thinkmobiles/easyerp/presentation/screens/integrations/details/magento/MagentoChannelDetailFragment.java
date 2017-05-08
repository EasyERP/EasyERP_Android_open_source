package com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento;

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
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public class MagentoChannelDetailFragment extends IntegrationChannelDetailFragment implements MagentoChannelDetailContract.MagnetoChannelView {

    private MagentoChannelDetailContract.MagentoChannelPresenter presenter;

    @ViewById
    protected EditText etChannelName_FCMD, etBaseUrl_FCMD, etUserName_FCMD, etApiPassword_FCMD;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_channel_magento_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new MagentoChannelDetailPresenter(this, integrationsRepository, channel);
    }

    @Override
    public void setPresenter(MagentoChannelDetailContract.MagentoChannelPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Integrations | Magento details screen";
    }

    @Override
    public void displayChannelName(String channelName) {
        etChannelName_FCMD.setText(channelName);
    }

    @Override
    public void displayBaseUrl(String url) {
        etBaseUrl_FCMD.setText(url);
    }

    @Override
    public void displayUserName(String userName) {
        etUserName_FCMD.setText(userName);
    }

    @Override
    public void displayPassword(String apiPassword) {
        etApiPassword_FCMD.setText(apiPassword);
    }

    @Override
    protected void changeConnectState() {
        presenter.changeConnectState();
    }
}
