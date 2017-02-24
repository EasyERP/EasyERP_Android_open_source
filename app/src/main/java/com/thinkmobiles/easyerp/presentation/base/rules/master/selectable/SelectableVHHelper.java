package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class SelectableVHHelper<DH extends SelectableDHHelper> extends RecyclerVH<DH> {

    public SelectableVHHelper(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
    }

    @Override
    @CallSuper
    public void bindData(DH data) {
        itemView.setSelected(data.isSelected());
    }
}
