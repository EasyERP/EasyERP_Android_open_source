package com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailView;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface MagentoChannelDetailContract {
    interface MagnetoChannelView extends BaseView<MagentoChannelPresenter>, IntegrationChannelDetailView {
        void displayBaseUrl(final String url);
        void displayUserName(final String userName);
        void displayPassword(final String apiPassword);
    }
    interface MagentoChannelPresenter extends ContentPresenter {

    }
}
