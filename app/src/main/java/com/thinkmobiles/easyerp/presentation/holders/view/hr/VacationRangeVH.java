package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationRangeDH;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

public final class VacationRangeVH extends RecyclerVH<VacationRangeDH> {

    private final View vTypeColor_LIVT;
    private final TextView tvTypeName_LIVT;
    private final TextView tvDateRange_LIVT;

    public VacationRangeVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        vTypeColor_LIVT = findView(R.id.vTypeColor_LIVT);
        tvTypeName_LIVT = findView(R.id.tvTypeName_LIVT);
        tvDateRange_LIVT = findView(R.id.tvDateRange_LIVT);
    }

    @Override
    public void bindData(VacationRangeDH data) {
        vTypeColor_LIVT.setBackgroundColor(data.getColor());
        tvTypeName_LIVT.setText(data.getType());
        tvDateRange_LIVT.setText(data.getRange());
    }
}
