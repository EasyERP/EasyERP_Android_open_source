package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;

/**
 * Created by Lynx on 2/6/2017.
 */

public final class AttachmentDH extends RecyclerDH {
    private AttachmentItem item;

    public AttachmentDH(AttachmentItem item) {
        this.item = item;
    }

    public AttachmentItem getItem() {
        return item;
    }
}
