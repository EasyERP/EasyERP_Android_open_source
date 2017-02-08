package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityAndLeadDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

import java.util.Locale;

/**
 * Created by Lynx on 2/3/2017.
 */

public final class OpportunityAndLeadVH extends RecyclerVH<OpportunityAndLeadDH> {

    private final TextView tvName_VLIOAL;
    private final TextView tvPriority_VLIOAL;
    private final TextView tvStatus_VLIOAL;
    private final TextView tvAssignedTo_VLIOAL;
    private final TextView tvCreatedEditedBy_VLIOAL;
    private final TextView tvRevenue_VLIOAL;

    public OpportunityAndLeadVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvName_VLIOAL = findView(R.id.tvName_VLIOAL);
        tvPriority_VLIOAL = findView(R.id.tvPriority_VLIOAL);
        tvStatus_VLIOAL = findView(R.id.tvStatus_VLIOAL);
        tvAssignedTo_VLIOAL = findView(R.id.tvAssignedTo_VLIOAL);
        tvCreatedEditedBy_VLIOAL = findView(R.id.tvCreatedEditedBy_VLIOAL);
        tvRevenue_VLIOAL = findView(R.id.tvRevenue_VLIOAL);
    }

    @Override
    public void bindData(OpportunityAndLeadDH data) {
        OpportunityItem item = data.getItem();
        tvName_VLIOAL.setText(item.name);
        if(!TextUtils.isEmpty(item.priority)) tvPriority_VLIOAL.setText(buildPriorityTag(item.priority));
        if(item.workflow != null && !TextUtils.isEmpty(item.workflow.name)) tvStatus_VLIOAL.setText(item.workflow.name);
        if(item.salesPerson != null && !TextUtils.isEmpty(item.salesPerson.fullName)) tvAssignedTo_VLIOAL.setText(item.salesPerson.fullName);
        if(item.expectedRevenue != null) {
            tvRevenue_VLIOAL.setText(String.format(Locale.US, "%d %s", item.expectedRevenue.value,
                    TextUtils.isEmpty(item.expectedRevenue.currency) ? "$" : item.expectedRevenue.currency));
        }
        if(item.editedBy != null && !TextUtils.isEmpty(item.editedBy.date)) {
            String str = "Last Edited: " + DateManager.getTime(item.editedBy.date);
            tvCreatedEditedBy_VLIOAL.setText(str);
        } else if(item.createdBy != null && !TextUtils.isEmpty(item.createdBy.date)) {
            String str = "Created: " + DateManager.getTime(item.editedBy.date);
            tvCreatedEditedBy_VLIOAL.setText(str);
        }
    }

    private SpannableStringBuilder buildPriorityTag(String priority) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(" ");
        if(priority.startsWith(TagHelper.MEDIUM)) priority = priority.substring(0,3);
        stringBuilder.append(priority.toUpperCase());
        stringBuilder.append("  ");
        RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(itemView.getContext(), TagHelper.getColorResIdByName(priority), Color.WHITE);
        stringBuilder.setSpan(tagSpan, 0, stringBuilder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }
}
