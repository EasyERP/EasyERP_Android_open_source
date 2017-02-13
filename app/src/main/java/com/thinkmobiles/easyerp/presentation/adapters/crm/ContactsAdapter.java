package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactsDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.ContactsVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/13/2017.
 */

@EBean
public class ContactsAdapter extends SimpleRecyclerAdapter<ContactsDH, ContactsVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_contacts;
    }
}
