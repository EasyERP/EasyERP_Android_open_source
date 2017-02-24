package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * Created by Lynx on 1/16/2017.
 */

public final class LeadDH extends SelectableDHHelper {
    private final LeadItem leadItem;

    public LeadDH(LeadItem leadItem) {
        this.leadItem = leadItem;
    }

    public LeadItem getLeadItem() {
        return leadItem;
    }

    @Override
    public String getId() {
        return leadItem.id;
    }
}
