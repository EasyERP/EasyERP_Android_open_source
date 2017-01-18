package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.LeadVH;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

@EBean
public class LeadsAdapter extends SimpleRecyclerAdapter<LeadDH, LeadVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_lead;
    }

    public void clear() {
        setListDH(new ArrayList<>());
    }
}
