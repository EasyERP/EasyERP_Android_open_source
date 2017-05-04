package com.thinkmobiles.easyerp.presentation.screens.integrations;

import com.thinkmobiles.easyerp.data.model.ResponseGetResultItems;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface IntegrationsListContract {
    interface IntegrationsListView extends BaseView<IntegrationsListPresenter>, SelectableView {
        void openDetailChannel(final Channel channel);
    }
    interface IntegrationsListPresenter extends SelectablePresenter {

    }
    interface IntegrationsListModel extends BaseModel {
        Observable<ResponseGetResultItems<Channel>> getChannels(final String channelName);
        Observable<Channel> changeConnectedStatus(final String channelId, final boolean connected);
    }
}
