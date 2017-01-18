package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadDH extends RecyclerDH {
    private LeadItem leadItem;

    public LeadDH(LeadItem leadItem) {
        this.leadItem = leadItem;
    }

    public LeadItem getLeadItem() {
        return leadItem;
    }
}
