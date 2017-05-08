package com.thinkmobiles.easyerp.presentation.screens.integrations.details.shopify;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailModel;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class ShopifyChannelDetailPresenter extends ContentPresenterHelper implements ShopifyChannelDetailContract.ShopifyChannelPresenter {

    private ShopifyChannelDetailContract.ShopifyChannelView view;
    private IntegrationChannelDetailModel model;

    private Channel channel;

    public ShopifyChannelDetailPresenter(ShopifyChannelDetailContract.ShopifyChannelView view, IntegrationChannelDetailModel model, Channel channel) {
        this.view = view;
        this.model = model;
        this.channel = channel;

        this.view.setPresenter(this);
    }

    @Override
    public void changeConnectState() {
        final boolean connect = !channel.connected;
        view.showProgress(String.format("%s. Please wait a second...", connect ? "Connecting" : "Disconnecting"));
        compositeSubscription.add(
                model.changeConnectStateInChannel(channel.id, connect).subscribe(
                        channelNew -> {
                            channel = channel.createWith(channelNew);
                            view.dismissProgress();
                            view.showInfoToast(String.format("Channel %s.", channel.connected ? "connected" : "disconnected"));
                            view.sendBroadcastUpdateChannel(channel);
                            setData();
                        }, t -> {
                            view.dismissProgress();
                            error(t);
                        }
                )
        );
    }

    @Override
    public void refresh() {
        setData();
        view.showProgress(Constants.ProgressType.NONE);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return channel != null;
    }

    @Override
    protected void retainInstance() {
        setData();
    }

    private void setData() {
        view.fillHeaderUI(channel);
        view.displayChannelName(channel.channelName);
        view.displayBaseUrl(channel.baseUrl);
        view.displayApiKey(channel.username);
        view.displayApiSecret(channel.password);

        view.displayWarehouse(channel.warehouseSettings.warehouse.name);
        view.displayLocation(channel.warehouseSettings.location.name);
        if (channel.priceList != null)
            view.displayPriceList(channel.priceList.name);
    }
}
