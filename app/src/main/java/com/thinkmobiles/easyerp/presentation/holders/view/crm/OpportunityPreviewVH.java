package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityPreviewDH;

/**
 * Created by Lynx on 1/25/2017.
 */

public final class OpportunityPreviewVH extends RecyclerVH<OpportunityPreviewDH> {

    private final TextView tvOpportunityCost_VLIOP;
    private final TextView tvOpportunityName_VLIOP;
    private final TextView tvOpportunityAssignedTo_VLIOP;
    private final TextView tvOpportunityStatus_VLIOP;

    public OpportunityPreviewVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvOpportunityCost_VLIOP = findView(R.id.tvOpportunityCost_VLIOP);
        tvOpportunityName_VLIOP = findView(R.id.tvOpportunityName_VLIOP);
        tvOpportunityAssignedTo_VLIOP = findView(R.id.tvOpportunityAssignedTo_VLIOP);
        tvOpportunityStatus_VLIOP = findView(R.id.tvOpportunityStatus_VLIOP);
    }

    @Override
    public void bindData(OpportunityPreviewDH data) {
        OpportunityItem opportunityItem = data.getOpportunityItem();
        boolean isCostVisible = (opportunityItem.expectedRevenue != null) && (opportunityItem.expectedRevenue.value > 0);
        tvOpportunityCost_VLIOP.setVisibility(isCostVisible ? View.VISIBLE : View.GONE);
        if(isCostVisible) {
            tvOpportunityCost_VLIOP.setText(opportunityItem.expectedRevenue.value + " " + opportunityItem.expectedRevenue.currency);
        }
        tvOpportunityName_VLIOP.setText(TextUtils.isEmpty(opportunityItem.name) ? "" : opportunityItem.name);
        tvOpportunityAssignedTo_VLIOP.setText((opportunityItem.salesPerson != null && !TextUtils.isEmpty(opportunityItem.salesPerson.fullName))
                ? opportunityItem.salesPerson.fullName : "");
        tvOpportunityStatus_VLIOP.setText((opportunityItem.workflow != null && !TextUtils.isEmpty(opportunityItem.workflow.name))
                ? opportunityItem.workflow.name : "");
    }
}
