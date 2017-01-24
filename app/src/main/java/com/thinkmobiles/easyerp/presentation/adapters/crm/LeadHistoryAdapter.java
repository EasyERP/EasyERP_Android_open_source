package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.michenko.simpleadapter.TypedRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.LeadHistoryVH;

import org.androidannotations.annotations.EBean;

@EBean
public class LeadHistoryAdapter extends SimpleRecyclerAdapter<LeadHistoryDH, LeadHistoryVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_lead_history;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? 1 : 0;
    }
}
