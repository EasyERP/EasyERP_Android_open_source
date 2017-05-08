package com.thinkmobiles.easyerp.presentation.screens.integrations.details.etsy;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class EtsyChannelDetailPresenter extends ContentPresenterHelper implements EtsyChannelDetailContract.EtsyChannelPresenter {

    private EtsyChannelDetailContract.EtsyChannelView view;

    private final Channel channel;

    public EtsyChannelDetailPresenter(EtsyChannelDetailContract.EtsyChannelView view, Channel channel) {
        this.view = view;
        this.channel = channel;

        this.view.setPresenter(this);
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
        view.displayChannelName(channel.channelName);
        view.displayShopName(channel.username);
        view.displayKeyString(channel.consumerKey);
        view.displaySharedSecret(channel.consumerSecret);

        view.displayWarehouse(channel.warehouseSettings.warehouse.name);
        view.displayLocation(channel.warehouseSettings.location.name);
        if (channel.priceList != null)
            view.displayPriceList(channel.priceList.name);
        if (channel.bankAccount != null)
            view.displayBankAccount(channel.bankAccount.name);
    }
}
