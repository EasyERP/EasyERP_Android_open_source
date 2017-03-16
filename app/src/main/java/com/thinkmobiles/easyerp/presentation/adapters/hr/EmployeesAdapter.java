package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.EmployeeVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/13/2017.
 */

@EBean
public class EmployeesAdapter extends SelectableAdapter<EmployeeDH, EmployeeVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_employee;
    }
}
