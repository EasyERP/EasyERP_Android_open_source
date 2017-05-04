package com.thinkmobiles.easyerp.presentation.holders.data.integrations;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public class ChannelDH extends SelectableDHHelper {

    private final Channel channel;

    public ChannelDH(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String getId() {
        return channel.id;
    }
}
