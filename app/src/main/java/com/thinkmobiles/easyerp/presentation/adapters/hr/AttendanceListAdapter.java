package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.AttendanceEmployeeVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 3/20/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class AttendanceListAdapter extends SelectableAdapter<EmployeeDH, AttendanceEmployeeVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_attendance;
    }
}
