package com.thinkmobiles.easyerp.presentation.adapters;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadDH;
import com.thinkmobiles.easyerp.presentation.holders.view.LeadVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean
public class LeadsAdapter extends SimpleRecyclerAdapter<LeadDH, LeadVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.list_item_lead;
    }
}
