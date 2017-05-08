package com.thinkmobiles.easyerp.presentation.screens.integrations.details.woo;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailPresenter;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailView;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface WooChannelDetailContract {
    interface WooChannelView extends BaseView<WooChannelPresenter>, IntegrationChannelDetailView {
        void displayBaseUrl(final String url);
        void displayConsumerKey(final String consumerKey);
        void displayConsumerSecret(final String consumerSecret);
        void displayVersion(final String version);
    }
    interface WooChannelPresenter extends IntegrationChannelDetailPresenter {

    }
}
