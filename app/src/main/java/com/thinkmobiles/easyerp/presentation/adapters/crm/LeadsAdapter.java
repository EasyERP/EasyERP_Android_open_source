package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.LeadVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean
public class LeadsAdapter extends MasterFlowSelectableAdapter<LeadDH, LeadVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_lead;
    }

}
