package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;

import java.util.Locale;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunityVH extends RecyclerVH<OpportunityDH> {

    private View flOpportunityItemContainer_VLIO;
    private TextView tvOpportunityName_VLIO;
    private TextView tvStage_LIL;
    private TextView tvAssignedTo_VLIO;
    private TextView tvRevenue_VLIO;

    private String noData;

    public OpportunityVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        flOpportunityItemContainer_VLIO = findView(R.id.flOpportunityItemContainer_VLIO);
        tvOpportunityName_VLIO = findView(R.id.tvOpportunityName_VLIO);
        tvStage_LIL = findView(R.id.tvStage_LIL);
        tvAssignedTo_VLIO = findView(R.id.tvAssignedTo_VLIO);
        tvRevenue_VLIO = findView(R.id.tvRevenue_VLIO);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(OpportunityDH data) {
        OpportunityListItem item = data.getOpportunityListItem();

        tvOpportunityName_VLIO.setText(TextUtils.isEmpty(item.name) ? "Unknown" : item.name);
        if(item.workflow != null && !TextUtils.isEmpty(item.workflow.name))
            tvStage_LIL.setText(item.workflow.name);
        if(item.salesPerson != null && !TextUtils.isEmpty(item.salesPerson.name))
            tvAssignedTo_VLIO.setText(item.salesPerson.name);
        if(item.expectedRevenue != null) {
            tvRevenue_VLIO.setText(String.format(Locale.US, "%d %s",
                    item.expectedRevenue.value,
                    TextUtils.isEmpty(item.expectedRevenue.currency) ? "$" : item.expectedRevenue.currency));
        }
        flOpportunityItemContainer_VLIO.setSelected(data.isSelected());
    }

}
