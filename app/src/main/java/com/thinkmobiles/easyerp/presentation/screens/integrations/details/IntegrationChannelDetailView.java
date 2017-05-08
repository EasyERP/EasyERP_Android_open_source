package com.thinkmobiles.easyerp.presentation.screens.integrations.details;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface IntegrationChannelDetailView extends ContentView {
    void fillHeaderUI(final Channel channel);
    void displayChannelName(final String channelName);

    void displayWarehouse(final String warehouse);
    void displayLocation(final String location);
    void displayPriceList(final String priceList);

    void showProgress(final String msg);
    void dismissProgress();
    void showInfoToast(final String msg);

    void sendBroadcastUpdateChannel(final Channel channel);
}
