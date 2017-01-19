package com.thinkmobiles.easyerp.presentation.adapters;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.LeadHistoryVH;

import org.androidannotations.annotations.EBean;

@EBean
public class LeadHistoryAdapter extends SimpleRecyclerAdapter<LeadHistoryDH, LeadHistoryVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_lead_history;
    }
}
