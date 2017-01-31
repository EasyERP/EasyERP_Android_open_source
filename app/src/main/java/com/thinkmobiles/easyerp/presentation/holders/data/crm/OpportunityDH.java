package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunityDH extends MasterFlowSelectableDHHelper<String> {

    private OpportunityListItem opportunityListItem;

    public OpportunityDH(OpportunityListItem opportunityListItem) {
        this.opportunityListItem = opportunityListItem;
    }

    public OpportunityListItem getOpportunityListItem() {
        return opportunityListItem;
    }

    @Override
    public String getId() {
        return opportunityListItem._id;
    }
}
