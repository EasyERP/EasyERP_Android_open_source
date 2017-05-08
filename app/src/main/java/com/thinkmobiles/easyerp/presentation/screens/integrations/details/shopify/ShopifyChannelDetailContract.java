package com.thinkmobiles.easyerp.presentation.screens.integrations.details.shopify;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailPresenter;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailView;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface ShopifyChannelDetailContract {
    interface ShopifyChannelView extends BaseView<ShopifyChannelPresenter>, IntegrationChannelDetailView {
        void displayBaseUrl(final String url);
        void displayApiKey(final String apiKey);
        void displayApiSecret(final String apiSecret);
    }
    interface ShopifyChannelPresenter extends IntegrationChannelDetailPresenter {

    }
}
