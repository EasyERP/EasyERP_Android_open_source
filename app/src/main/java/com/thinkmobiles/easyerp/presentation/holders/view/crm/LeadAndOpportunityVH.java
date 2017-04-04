package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadAndOpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.ColorHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

import java.util.Locale;

/**
 * Created by Lynx on 2/3/2017.
 */

public final class LeadAndOpportunityVH extends RecyclerVH<LeadAndOpportunityDH> {

    private final TextView tvName_VLIOAL;
    private final ImageView ivPriority_VLIOAL;
    private final TextView tvStatus_VLIOAL;
    private final TextView tvAssignedTo_VLIOAL;
    private final TextView tvCreatedEditedBy_VLIOAL;
    private final TextView tvRevenue_VLIOAL;

    public LeadAndOpportunityVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvName_VLIOAL = findView(R.id.tvName_VLIOAL);
        ivPriority_VLIOAL = findView(R.id.tvPriority_VLIOAL);
        tvStatus_VLIOAL = findView(R.id.tvStatus_VLIOAL);
        tvAssignedTo_VLIOAL = findView(R.id.tvAssignedTo_VLIOAL);
        tvCreatedEditedBy_VLIOAL = findView(R.id.tvCreatedEditedBy_VLIOAL);
        tvRevenue_VLIOAL = findView(R.id.tvRevenue_VLIOAL);
    }

    @Override
    public void bindData(LeadAndOpportunityDH data) {
        OpportunityItem item = data.getItem();
        tvName_VLIOAL.setText(item.name);
        if(!TextUtils.isEmpty(item.priority)) {
            switch (item.priority) {
                case "Hot":
                    ivPriority_VLIOAL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_hot));
                    break;
                case "Medium":
                    ivPriority_VLIOAL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_medium));
                    break;
                case "Cold":
                    ivPriority_VLIOAL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_cold));
                    break;
            }
            ivPriority_VLIOAL.setVisibility(View.VISIBLE);
        } else {
            ivPriority_VLIOAL.setImageResource(0);
            ivPriority_VLIOAL.setVisibility(View.GONE);
        }
        if(item.workflow != null && !TextUtils.isEmpty(item.workflow.name)) {
            tvStatus_VLIOAL.setText(item.workflow.name);
            tvStatus_VLIOAL.setBackground(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), ColorHelper.getStatusColorRes(item.workflow.name))));
        }
        else {
            tvStatus_VLIOAL.setBackgroundResource(0);
        }
        if(item.salesPerson != null && !TextUtils.isEmpty(item.salesPerson.fullName)) {
            tvAssignedTo_VLIOAL.setText(item.salesPerson.fullName);
        } else {
            tvAssignedTo_VLIOAL.setText(null);
        }
        if(item.expectedRevenue != null) {
            tvRevenue_VLIOAL.setText(String.format(Locale.US, "%d %s", item.expectedRevenue.value,
                    TextUtils.isEmpty(item.expectedRevenue.currency) ? "$" : item.expectedRevenue.currency));
        } else {
            tvRevenue_VLIOAL.setText(null);
        }
        if(item.editedBy != null && !TextUtils.isEmpty(item.editedBy.date)) {
            String str = "Last Edited: " + DateManager.getTime(item.editedBy.date);
            tvCreatedEditedBy_VLIOAL.setText(str);
        } else if(item.createdBy != null && !TextUtils.isEmpty(item.createdBy.date)) {
            String str = "Created: " + DateManager.getTime(item.editedBy.date);
            tvCreatedEditedBy_VLIOAL.setText(str);
        } else {
            tvCreatedEditedBy_VLIOAL.setText(null);
        }
    }
}
