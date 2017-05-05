package com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface MagentoChannelDetailContract {
    interface MagnetoChannelView extends BaseView<MagentoChannelPresenter>, ContentView {
        void displayChannelName(final String channelName);
        void displayBaseUrl(final String url);
        void displayUserName(final String userName);
        void displayPassword(final String apiPassword);

        void displayWarehouse(final String warehouse);
        void displayLocation(final String location);
        void displayPriceList(final String priceList);
    }
    interface MagentoChannelPresenter extends ContentPresenter {

    }
}
