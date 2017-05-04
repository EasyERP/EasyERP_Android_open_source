package com.thinkmobiles.easyerp.presentation.adapters.integrations;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.integrations.ChannelDH;
import com.thinkmobiles.easyerp.presentation.holders.view.integrations.ChannelVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EBean
public class ChannelsListAdapter extends SelectableAdapter<ChannelDH, ChannelVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_channel;
    }
}
