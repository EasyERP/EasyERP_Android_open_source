package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.PersonVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/23/2017.
 */

@EBean
public class PersonsAdapter extends SelectableAdapter<PersonDH, PersonVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_person;
    }
}
