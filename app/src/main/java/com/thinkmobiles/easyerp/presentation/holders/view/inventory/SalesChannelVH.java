package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.SalesChannelDH;

/**
 * Created by samson on 14.03.17.
 */

public final class SalesChannelVH extends RecyclerVH<SalesChannelDH> {

    private final ImageView ivChannelLogo_LIPSC;
    private final TextView tvChannelName_LIPSC;
    private final ImageView ivChannelPublished_LIPSC;

    public SalesChannelVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        ivChannelLogo_LIPSC = findView(R.id.ivChannelLogo_LIPSC);
        tvChannelName_LIPSC = findView(R.id.tvChannelName_LIPSC);
        ivChannelPublished_LIPSC = findView(R.id.ivChannelPublished_LIPSC);
    }

    @Override
    public void bindData(SalesChannelDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.color.color_bg_product_details : android.R.color.white);
        Picasso.with(ivChannelLogo_LIPSC.getContext())
                .load(data.getUrl())
                .into(ivChannelLogo_LIPSC);
        tvChannelName_LIPSC.setText(data.getName());
        ivChannelPublished_LIPSC.setImageResource(data.isPublished() ? R.drawable.ic_published : R.drawable.ic_published_off);
    }
}
