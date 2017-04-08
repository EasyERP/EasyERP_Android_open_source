package com.thinkmobiles.easyerp.presentation.adapters.reports;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.FilterVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class ReportTypesAdapter extends SimpleRecyclerAdapter<FilterDH, FilterVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_report_type;
    }
}
