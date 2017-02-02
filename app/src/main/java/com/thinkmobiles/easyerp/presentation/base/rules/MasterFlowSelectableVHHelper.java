package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public abstract class MasterFlowSelectableVHHelper<DH extends MasterFlowSelectableDHHelper> extends RecyclerVH<DH> {

    private final View rootView;

    public MasterFlowSelectableVHHelper(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        rootView = itemView;
    }

    @Override
    public void bindData(DH data) {
        rootView.setSelected(data.isSelected());
    }
}
