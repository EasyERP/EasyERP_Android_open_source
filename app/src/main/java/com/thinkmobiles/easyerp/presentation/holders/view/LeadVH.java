package com.thinkmobiles.easyerp.presentation.holders.view;

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
import com.thinkmobiles.easyerp.data.model.leads.LeadTag;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadVH extends RecyclerVH<LeadDH> {

    private TextView tvLeadName_LIL;
    private TextView tvStage_LIL;
    private TextView tvPriority_LIL;
    private TextView tvSource_LIL;
    private TextView tvAssignedTo_LIL;
    private TextView tvEditedDate_LIL;
    private TextView tvTags_LIL;

    private String noData;

    public LeadVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvLeadName_LIL = findView(R.id.tvLeadName_LIL);
        tvStage_LIL = findView(R.id.tvStage_LIL);
        tvPriority_LIL = findView(R.id.tvPriority_LIL);
        tvSource_LIL = findView(R.id.tvSource_LIL);
        tvAssignedTo_LIL = findView(R.id.tvAssignedTo_LIL);
        tvEditedDate_LIL = findView(R.id.tvEditedDate_LIL);
        tvTags_LIL = findView(R.id.tvTags_LIL);

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
        if(!TextUtils.isEmpty(data.getLeadItem().priority))
            tvPriority_LIL.setText(data.getLeadItem().priority);
        else
            tvPriority_LIL.setText(noData);
        if(!TextUtils.isEmpty(data.getLeadItem().source))
            tvSource_LIL.setText(data.getLeadItem().source);
        else
            tvSource_LIL.setText(noData);
        if(data.getLeadItem().salesPerson != null && !TextUtils.isEmpty(data.getLeadItem().salesPerson.name))
            tvAssignedTo_LIL.setText(data.getLeadItem().salesPerson.name);
        else
            tvAssignedTo_LIL.setText(noData);
        if(data.getLeadItem().editedBy != null && !TextUtils.isEmpty(data.getLeadItem().editedBy.date)) {
            String out = DateManager.convertLeadDate(data.getLeadItem().editedBy.date);
            if(!TextUtils.isEmpty(data.getLeadItem().editedBy.user))
                out = String.format("%s (%s)", out, data.getLeadItem().editedBy.user);
            tvEditedDate_LIL.setText(out);
        } else
            tvEditedDate_LIL.setText(noData);
        if(data.getLeadItem().tags != null && data.getLeadItem().tags.size() > 0) {
            tvTags_LIL.setText(prepareTags(data.getLeadItem().tags));
        } else
            tvTags_LIL.setText("");
    }

    private SpannableStringBuilder prepareTags(ArrayList<LeadTag> leadTags) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        String between = " ";
        int tagStart = 0;

        for (LeadTag tag : leadTags) {
            // Append tag and space after
            stringBuilder.append(tag.name);
            stringBuilder.append(between);

            // Set span for tag
            RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(itemView.getContext(), TagHelper.getColorResIdByName(tag.color), Color.WHITE);
            stringBuilder.setSpan(tagSpan, tagStart, tagStart + tag.name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Update to next tag start
            tagStart += tag.name.length() + between.length();
        }
        return stringBuilder;
    }
}
