package com.thinkmobiles.easyerp.presentation.screens.integrations.details.magento;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class MagentoChannelDetailPresenter extends ContentPresenterHelper implements MagentoChannelDetailContract.MagentoChannelPresenter {

    private MagentoChannelDetailContract.MagnetoChannelView view;

    private final Channel channel;

    public MagentoChannelDetailPresenter(MagentoChannelDetailContract.MagnetoChannelView view, Channel channel) {
        this.view = view;
        this.channel = channel;

        this.view.setPresenter(this);
    }

    @Override
    public void refresh() {

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

    }
}
