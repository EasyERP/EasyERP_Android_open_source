package com.thinkmobiles.easyerp.presentation.adapters.reports;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.ReportDH;
import com.thinkmobiles.easyerp.presentation.holders.view.reports.ReportVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 4/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class GeneralReportsAdapter extends SimpleRecyclerAdapter<ReportDH, ReportVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_general_reports;
    }
}
