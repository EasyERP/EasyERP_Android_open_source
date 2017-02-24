package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.OpportunityVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/30/2017.
 */

@EBean
public class OpportunitiesAdapter extends SelectableAdapter<OpportunityDH, OpportunityVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_opportunity;
    }
}
