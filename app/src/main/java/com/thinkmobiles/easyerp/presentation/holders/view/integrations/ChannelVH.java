package com.thinkmobiles.easyerp.presentation.holders.view.integrations;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.integrations.ChannelDH;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class ChannelVH extends SelectableVHHelper<ChannelDH> {

    private TextView tvChannelName_VLIC, tvChannelType_VLIC, tvStatusConnected_VLIC;
    private ImageView ivChannelImage_VLIC;

    private int colorConnected, colorDisconnected;

    public ChannelVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvChannelName_VLIC = findView(R.id.tvChannelName_VLIC);
        tvChannelType_VLIC = findView(R.id.tvChannelType_VLIC);
        tvStatusConnected_VLIC = findView(R.id.tvStatusConnected_VLIC);
        ivChannelImage_VLIC = findView(R.id.ivChannelImage_VLIC);

        colorConnected = itemView.getResources().getColor(R.color.color_chips_green);
        colorDisconnected = itemView.getResources().getColor(R.color.color_chips_red);
    }

    @Override
    public void bindData(ChannelDH data) {
        super.bindData(data);

        final Channel channel = data.getChannel();
        tvChannelName_VLIC.setText(channel.channelName);
        tvChannelType_VLIC.setText(channel.type);
        ivChannelImage_VLIC.setImageResource(getChannelImageRes(channel.type));
        tvStatusConnected_VLIC.setText(channel.connected ? R.string.connected : R.string.disconnected);
        tvStatusConnected_VLIC.setBackgroundDrawable(new RoundRectDrawable(channel.connected ? colorConnected : colorDisconnected));
    }

    private @DrawableRes int getChannelImageRes(final String channelSku) {
        switch (channelSku) {
            case "shopify": return R.drawable.ic_shopify;
            case "etsy": return R.drawable.ic_etsy;
            case "magento": return R.drawable.ic_magento;
            case "woo": return R.drawable.ic_woo;
            default: return 0;
        }
    }
}
