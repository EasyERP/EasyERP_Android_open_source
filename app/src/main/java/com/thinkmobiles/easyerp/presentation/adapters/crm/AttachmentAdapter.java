package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.AttachmentVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/6/2017.
 */

@EBean
public class AttachmentAdapter extends SimpleRecyclerAdapter<AttachmentDH, AttachmentVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_attachment;
    }
}
