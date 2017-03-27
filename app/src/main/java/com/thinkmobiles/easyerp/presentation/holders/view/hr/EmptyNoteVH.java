package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;

/**
 * Created by Lynx on 3/21/2017.
 */

public class EmptyNoteVH extends RecyclerVH<SimpleNoteDH> {

    public EmptyNoteVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
    }

    @Override
    public void bindData(SimpleNoteDH data) {

    }
}
