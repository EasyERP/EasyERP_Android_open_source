package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;

/**
 * Created by Lynx on 1/24/2017.
 */

public class OpportunityPreviewDH extends RecyclerDH {
    private OpportunityItem opportunityItem;

    public OpportunityPreviewDH(OpportunityItem opportunityItem) {
        this.opportunityItem = opportunityItem;
    }

    public OpportunityItem getOpportunityItem() {
        return opportunityItem;
    }
}
