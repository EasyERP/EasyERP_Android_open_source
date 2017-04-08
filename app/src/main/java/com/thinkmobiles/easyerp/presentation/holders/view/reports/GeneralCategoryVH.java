package com.thinkmobiles.easyerp.presentation.holders.view.reports;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.GeneralCategoryDH;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class GeneralCategoryVH extends SelectableVHHelper<GeneralCategoryDH> {

    private TextView tvCategoryName_VLIGC;

    public GeneralCategoryVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvCategoryName_VLIGC = findView(R.id.tvCategoryName_VLIGC);
    }

    @Override
    public void bindData(GeneralCategoryDH data) {
        super.bindData(data);
        tvCategoryName_VLIGC.setText(data.getCategory().label);
    }
}
