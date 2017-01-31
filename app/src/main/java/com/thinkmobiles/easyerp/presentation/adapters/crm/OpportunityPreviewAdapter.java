package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityPreviewDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.OpportunityPreviewVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/25/2017.
 */


@EBean
public class OpportunityPreviewAdapter extends SimpleRecyclerAdapter<OpportunityPreviewDH, OpportunityPreviewVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_opportunity_preview;
    }
}
