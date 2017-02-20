package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.ContactVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/13/2017.
 */

@EBean
public class ContactAdapter extends SimpleRecyclerAdapter<ContactDH, ContactVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_contact;
    }
}
