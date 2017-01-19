package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.TypedRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.LeadHistoryVH;

import org.androidannotations.annotations.EBean;

@EBean
public class LeadHistoryAdapter extends TypedRecyclerAdapter<LeadHistoryDH> {

    @Override
    protected void initViewTypes() {
        addType(0, R.layout.view_list_item_lead_history, LeadHistoryVH.class);
        addType(1, R.layout.view_list_item_lead_history, LeadHistoryVH.class);
    }

    @Override
    protected int getViewType(int position) {
        return position == getItemCount() - 1 ? 1: 0;
    }
}
