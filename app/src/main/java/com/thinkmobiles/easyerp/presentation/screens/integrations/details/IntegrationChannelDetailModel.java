package com.thinkmobiles.easyerp.presentation.screens.integrations.details;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface IntegrationChannelDetailModel extends BaseModel {
    Observable<Channel> changeConnectStateInChannel(final String channelId, final boolean connect);
}
