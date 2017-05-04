package com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public class MagentoChannelDetailFragment extends IntegrationChannelDetailFragment implements MagentoChannelDetailContract.MagnetoChannelView {

    private MagentoChannelDetailContract.MagentoChannelPresenter presenter;

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
        new MagentoChannelDetailPresenter(this, channel);
    }

    @Override
    public void setPresenter(MagentoChannelDetailContract.MagentoChannelPresenter presenter) {
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
        return "Integrations | Magento details screen";
    }
}
