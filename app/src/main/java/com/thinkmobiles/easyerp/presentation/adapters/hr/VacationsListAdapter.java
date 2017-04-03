package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationListDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.VacationListVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/29/2017.
 */

@EBean
public class VacationsListAdapter extends SelectableAdapter<VacationListDH, VacationListVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_vacation;
    }
}
