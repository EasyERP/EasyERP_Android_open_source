package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationListDH;

/**
 * Created by Lynx on 3/29/2017.
 */

public class VacationListVH extends SelectableVHHelper<VacationListDH> {

    private TextView tvVacationMonth_VLIV;

    public VacationListVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvVacationMonth_VLIV = findView(R.id.tvVacationMonth_VLIV);
    }

    @Override
    public void bindData(VacationListDH data) {
        super.bindData(data);
        tvVacationMonth_VLIV.setText(data.getData());
    }
}
