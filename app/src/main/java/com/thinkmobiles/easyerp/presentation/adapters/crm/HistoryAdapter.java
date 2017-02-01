package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.HistoryVH;

import org.androidannotations.annotations.EBean;

@EBean
public class HistoryAdapter extends SimpleRecyclerAdapter<HistoryDH, HistoryVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_lead_history;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? 1 : 0;
    }
}
