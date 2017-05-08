package com.thinkmobiles.easyerp.presentation.screens.integrations;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.data.model.integrations.ChannelType;
import com.thinkmobiles.easyerp.domain.integrations.IntegrationsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.integrations.ChannelsListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.etsy.EtsyChannelDetailFragment_;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento.MagentoChannelDetailFragment_;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.shopify.ShopifyChannelDetailFragment_;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.woo.WooChannelDetailFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public class IntegrationsListFragment extends MasterSelectableFragment implements IntegrationsListContract.IntegrationsListView {

    private IntegrationsListContract.IntegrationsListPresenter presenter;

    @FragmentArg
    protected ChannelType channelType;
    @FragmentArg
    protected String itemLabel;

    @Bean
    protected ChannelsListAdapter channelsListAdapter;
    @Bean
    protected IntegrationsRepository integrationsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new IntegrationsListPresenter(this, integrationsRepository, channelType);
    }

    @Override
    public void setPresenter(IntegrationsListContract.IntegrationsListPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return channelsListAdapter;
    }

    @Override
    public String getScreenName() {
        return String.format("Integrations | %s list screen", itemLabel);
    }

    @Override
    public void openDetailChannel(Channel channel) {
        switch (channel.getChannelType()) {
            case MAGENTO:
                mActivity.replaceFragmentContentDetail(MagentoChannelDetailFragment_.builder().channel(channel).build());
                break;
            case SHOPIFY:
                mActivity.replaceFragmentContentDetail(ShopifyChannelDetailFragment_.builder().channel(channel).build());
                break;
            case ETSY:
                mActivity.replaceFragmentContentDetail(EtsyChannelDetailFragment_.builder().channel(channel).build());
                break;
            case WOO:
                mActivity.replaceFragmentContentDetail(WooChannelDetailFragment_.builder().channel(channel).build());
                break;
        }
    }
}
