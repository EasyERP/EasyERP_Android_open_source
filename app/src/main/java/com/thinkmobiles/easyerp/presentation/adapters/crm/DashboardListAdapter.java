package com.thinkmobiles.easyerp.presentation.adapters.crm;


import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.DashboardVH;

import org.androidannotations.annotations.EBean;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
@EBean
public class DashboardListAdapter extends SelectableAdapter<DashboardListDH, DashboardVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_crm_dashbord;
    }
}
