package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public class DashboardVH extends MasterFlowSelectableVHHelper<DashboardListDH> {

    private TextView tvDashboardName_VLICD;
    private TextView tvTypeChart_VLICD;

    public DashboardVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvDashboardName_VLICD = findView(R.id.tvDashboardName_VLICD);
        tvTypeChart_VLICD = findView(R.id.tvTypeChart_VLICD);
    }

    @Override
    public void bindData(DashboardListDH data) {
        super.bindData(data);

        tvDashboardName_VLICD.setText(data.getDashboardListItem().name);
        tvTypeChart_VLICD.setText(data.getDashboardListItem().type);
    }

}