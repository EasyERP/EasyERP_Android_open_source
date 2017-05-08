package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.data.model.integrations.ResponseGetChannels;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface IntegrationsService {

    @GET(Constants.GET_CHANNELS)
    Observable<ResponseGetChannels> getChannels();

    @GET(Constants.GET_CHANNELS_BY_NAME)
    Observable<ResponseGetChannels> getChannels(@Path("channelName") final String channelName);

    @FormUrlEncoded
    @PATCH(Constants.PATCH_CHANNEL_CONNECT_STATE)
    Observable<Channel> changeConnectStatus(@Path("channelId") final String channelId, @Field("connected") final boolean connected);
}
