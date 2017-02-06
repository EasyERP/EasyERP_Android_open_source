package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.OpportunityAndLeadVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/3/2017.
 */


@EBean
public class OpportunityAndLeadsAdapter extends SimpleRecyclerAdapter<OpportunityAndLeadDH, OpportunityAndLeadVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_opportunity_and_lead;
    }
}
