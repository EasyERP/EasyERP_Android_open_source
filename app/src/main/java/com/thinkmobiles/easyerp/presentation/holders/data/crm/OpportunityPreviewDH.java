package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;

/**
 * Created by Lynx on 1/24/2017.
 */

public final class OpportunityPreviewDH extends RecyclerDH {
    private final OpportunityItem opportunityItem;

    public OpportunityPreviewDH(OpportunityItem opportunityItem) {
        this.opportunityItem = opportunityItem;
    }

    public OpportunityItem getOpportunityItem() {
        return opportunityItem;
    }
}
