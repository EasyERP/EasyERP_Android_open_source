package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;

/**
 * Created by Lynx on 2/3/2017.
 */

public final class OpportunityAndLeadDH extends RecyclerDH {

    private final OpportunityItem item;

    public OpportunityAndLeadDH(OpportunityItem item) {
        this.item = item;
    }

    public OpportunityItem getItem() {
        return item;
    }
}
