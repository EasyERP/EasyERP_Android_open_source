package com.thinkmobiles.easyerp.domain.integrations;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetResultItems;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.data.services.IntegrationsService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.integrations.IntegrationsListContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EBean
public class IntegrationsRepository extends NetworkRepository implements IntegrationsListContract.IntegrationsListModel {

    private IntegrationsService integrationsService;

    public IntegrationsRepository() {
        this.integrationsService = Rest.getInstance().getIntegrationsService();
    }

    @Override
    public Observable<ResponseGetResultItems<Channel>> getChannels(String channelName) {
        return getNetworkObservable(
                channelName == null ?
                        integrationsService.getChannels() :
                        integrationsService.getChannels(channelName));
    }

    @Override
    public Observable<Channel> changeConnectedStatus(String channelId, boolean connected) {
        return getNetworkObservable(integrationsService.changeConnectStatus(channelId, connected));
    }
}
