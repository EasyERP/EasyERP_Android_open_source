package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;

/**
 * Created by Lynx on 1/24/2017.
 */

public class OpportunityDH extends RecyclerDH {
    private OpportunityItem opportunityItem;

    public OpportunityDH(OpportunityItem opportunityItem) {
        this.opportunityItem = opportunityItem;
    }

    public OpportunityItem getOpportunityItem() {
        return opportunityItem;
    }
}
