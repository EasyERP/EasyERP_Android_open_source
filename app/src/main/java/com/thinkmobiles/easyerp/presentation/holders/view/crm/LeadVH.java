package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.LeadTag;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadVH extends RecyclerVH<LeadDH> {

    private View flLeadItemContainer_LIL;
    private TextView tvLeadName_LIL;
    private TextView tvStage_LIL;
    private TextView tvPriority_LIL;
    private TextView tvSource_LIL;
    private TextView tvAssignedTo_LIL;
    private TextView tvEditedBy_VLIL;
    private LinearLayout llFirstLine_VLIL;

    private String noData;

    public LeadVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        flLeadItemContainer_LIL = findView(R.id.flLeadItemContainer_LIL);
        tvLeadName_LIL = findView(R.id.tvLeadName_LIL);
        tvStage_LIL = findView(R.id.tvStage_LIL);
        tvPriority_LIL = findView(R.id.tvPriority_LIL);
        tvSource_LIL = findView(R.id.tvSource_LIL);
        tvAssignedTo_LIL = findView(R.id.tvAssignedTo_LIL);
        llFirstLine_VLIL = findView(R.id.llFirstLine_VLIL);
        tvEditedBy_VLIL = findView(R.id.tvEditedBy_VLIL);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(LeadDH data) {
        if(!TextUtils.isEmpty(data.getLeadItem().name))
            tvLeadName_LIL.setText(data.getLeadItem().name);
        else
            tvLeadName_LIL.setText(noData);
        if(data.getLeadItem().workflow != null && !TextUtils.isEmpty(data.getLeadItem().workflow.name))
            tvStage_LIL.setText(data.getLeadItem().workflow.name);
        else
            tvStage_LIL.setText(noData);
        if(!TextUtils.isEmpty(data.getLeadItem().priority)) {
            tvPriority_LIL.setVisibility(View.VISIBLE);
            tvPriority_LIL.setText(buildPriorityTag(data.getLeadItem().priority));
        } else
            tvPriority_LIL.setVisibility(View.GONE);
        if(!TextUtils.isEmpty(data.getLeadItem().source))
            tvSource_LIL.setText(data.getLeadItem().source);
        else
            tvSource_LIL.setText(noData);
        if(data.getLeadItem().salesPerson != null && !TextUtils.isEmpty(data.getLeadItem().salesPerson.name))
            tvAssignedTo_LIL.setText(data.getLeadItem().salesPerson.name);
        else
            tvAssignedTo_LIL.setText(noData);
        if(data.getLeadItem().editedBy != null && !TextUtils.isEmpty(data.getLeadItem().editedBy.date)) {
            //Last Edited: Today, at 2:45 PM by Test Admin
            String strDate = data.getLeadItem().editedBy.date;
            String out = String.format("Last edited: %s at %s", DateManager.getDateToNow(strDate), DateManager.getTime(strDate));
            if(!TextUtils.isEmpty(data.getLeadItem().editedBy.user))
                out = out + " by " + data.getLeadItem().editedBy.user;
            tvEditedBy_VLIL.setText(out);
        } else
            tvEditedBy_VLIL.setText(noData);

        flLeadItemContainer_LIL.setSelected(data.isSelected());
        tvLeadName_LIL.requestLayout();
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
