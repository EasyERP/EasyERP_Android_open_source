package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public class DashboardVH extends RecyclerVH<DashboardListDH> {

    private TextView tvDashboardName_VLICD;
    private FrameLayout flDashboardItemContainer_VLICD;

    public DashboardVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        flDashboardItemContainer_VLICD = findView(R.id.flDashboardItemContainer_VLICD);
        tvDashboardName_VLICD = findView(R.id.tvDashboardName_VLICD);
    }

    @Override
    public void bindData(DashboardListDH data) {
        tvDashboardName_VLICD.setText(data.getLabel());
        flDashboardItemContainer_VLICD.setSelected(data.isSelected());
    }

}
