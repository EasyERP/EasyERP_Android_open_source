package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeRowTransferDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.RowJobPositionVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/20/2017.
 */

@EBean
public class RowEmploymentJobPositionAdapter extends SimpleRecyclerAdapter<EmployeeRowTransferDH, RowJobPositionVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_row_job_position;
    }
}
