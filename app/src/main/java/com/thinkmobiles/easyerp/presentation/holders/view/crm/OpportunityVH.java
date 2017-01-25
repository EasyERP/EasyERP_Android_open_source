package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;

/**
 * Created by Lynx on 1/25/2017.
 */

public class OpportunityVH extends RecyclerVH<OpportunityDH> {

    private TextView tvOpportunityCost_VLIP;
    private TextView tvOpportunityName_VLIP;
    private TextView tvOpportunityAssignedTo_VLIP;
    private TextView tvOpportunityStatus_VLIP;

    public OpportunityVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvOpportunityCost_VLIP = findView(R.id.tvOpportunityCost_VLIP);
        tvOpportunityName_VLIP = findView(R.id.tvOpportunityName_VLIP);
        tvOpportunityAssignedTo_VLIP = findView(R.id.tvOpportunityAssignedTo_VLIP);
        tvOpportunityStatus_VLIP = findView(R.id.tvOpportunityStatus_VLIP);
    }

    @Override
    public void bindData(OpportunityDH data) {
        OpportunityItem opportunityItem = data.getOpportunityItem();
        boolean isCostVisible = (opportunityItem.expectedRevenue != null) && (opportunityItem.expectedRevenue.value > 0);
        tvOpportunityCost_VLIP.setVisibility(isCostVisible ? View.VISIBLE : View.GONE);
        if(isCostVisible) {
            tvOpportunityCost_VLIP.setText(opportunityItem.expectedRevenue.value + " " + opportunityItem.expectedRevenue.currency);
        }
        tvOpportunityName_VLIP.setText(TextUtils.isEmpty(opportunityItem.name) ? "" : opportunityItem.name);
        tvOpportunityAssignedTo_VLIP.setText((opportunityItem.salesPerson != null && !TextUtils.isEmpty(opportunityItem.salesPerson.fullName))
                ? opportunityItem.salesPerson.fullName : "");
        tvOpportunityStatus_VLIP.setText((opportunityItem.workflow != null && !TextUtils.isEmpty(opportunityItem.workflow.name))
                ? opportunityItem.workflow.name : "");
    }
}
