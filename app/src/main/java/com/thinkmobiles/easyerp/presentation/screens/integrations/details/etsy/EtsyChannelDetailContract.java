package com.thinkmobiles.easyerp.presentation.screens.integrations.details.etsy;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.screens.integrations.details.IntegrationChannelDetailView;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface EtsyChannelDetailContract {
    interface EtsyChannelView extends BaseView<EtsyChannelPresenter>, IntegrationChannelDetailView {
        void displayShopName(final String shopName);
        void displayKeyString(final String keyString);
        void displaySharedSecret(final String sharedSecret);

        void displayBankAccount(final String bankAccount);
    }
    interface EtsyChannelPresenter extends ContentPresenter {

    }
}
