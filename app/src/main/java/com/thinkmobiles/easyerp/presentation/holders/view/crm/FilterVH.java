package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;

public final class FilterVH extends RecyclerVH<FilterDH> {

    private final CheckBox cbItem;

    public FilterVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        cbItem = findView(R.id.cbItem);

        cbItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onClick(cbItem, getAdapterPosition(), viewType);
            }
        });
    }

    @Override
    public void bindData(FilterDH data) {
        cbItem.setText(data.name);
        cbItem.setChecked(data.selected);
    }
}
