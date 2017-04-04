package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationRangeDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.VacationRangeVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

@EBean
public class VacationRangeAdapter extends SimpleRecyclerAdapter<VacationRangeDH, VacationRangeVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_vacation_type;
    }
}
