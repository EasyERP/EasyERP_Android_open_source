package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.view.View;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.TransferDH;

/**
 * Created by Lynx on 3/7/2017.
 */

public class TransferVH extends SelectableVHHelper<TransferDH> {

    public TransferVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
    }
}
