package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationPersonDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.VacationPersonVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/31/2017.
 */

@EBean
public class VacationPersonAdapter extends SimpleRecyclerAdapter<VacationPersonDH, VacationPersonVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_vacation_person;
    }
}
