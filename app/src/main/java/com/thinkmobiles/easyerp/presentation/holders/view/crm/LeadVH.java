package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

/**
 * Created by Lynx on 1/16/2017.
 */

public final class LeadVH extends SelectableVHHelper<LeadDH> {

    private final TextView tvLeadName_LIL;
    private final TextView tvStage_LIL;
    private final ImageView ivPriority_LIL;
    private final TextView tvSource_LIL;
    private final TextView tvAssignedTo_LIL;
    private final TextView tvEditedBy_VLIL;

    private String patternEditedBy;

    public LeadVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvLeadName_LIL = findView(R.id.tvLeadName_LIL);
        tvStage_LIL = findView(R.id.tvStage_LIL);
        ivPriority_LIL = findView(R.id.ivPriority_LIL);
        tvSource_LIL = findView(R.id.tvSource_LIL);
        tvAssignedTo_LIL = findView(R.id.tvAssignedTo_LIL);
        tvEditedBy_VLIL = findView(R.id.tvEditedBy_VLIL);

        patternEditedBy = itemView.getContext().getString(R.string.pattern_last_edited);
    }

    @Override
    public void bindData(LeadDH data) {
        super.bindData(data);

        if(!TextUtils.isEmpty(data.getLeadItem().name))
            tvLeadName_LIL.setText(data.getLeadItem().name);
        else
            tvLeadName_LIL.setText(null);
        if(data.getLeadItem().workflow != null && !TextUtils.isEmpty(data.getLeadItem().workflow.name)) {
            tvStage_LIL.setText(data.getLeadItem().workflow.name);
            tvStage_LIL.setBackground(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getStatusColorRes(data.getLeadItem().workflow.status))));
        }
        else {
            tvStage_LIL.setBackgroundResource(0);
        }
        if(!TextUtils.isEmpty(data.getLeadItem().priority)) {
            switch (data.getLeadItem().priority) {
                case "Hot":
                    ivPriority_LIL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_hot));
                    break;
                case "Medium":
                    ivPriority_LIL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_medium));
                    break;
                case "Cold":
                    ivPriority_LIL.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_cold));
                    break;
            }
            ivPriority_LIL.setVisibility(View.VISIBLE);
        } else {
            ivPriority_LIL.setImageResource(0);
            ivPriority_LIL.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(data.getLeadItem().source))
            tvSource_LIL.setText(data.getLeadItem().source);
        else
            tvSource_LIL.setText(null);
        if(data.getLeadItem().salesPerson != null && !TextUtils.isEmpty(data.getLeadItem().salesPerson.name))
            tvAssignedTo_LIL.setText(data.getLeadItem().salesPerson.name);
        else
            tvAssignedTo_LIL.setText(itemView.getContext().getString(R.string.not_assigned));
        if(data.getLeadItem().editedBy != null && !TextUtils.isEmpty(data.getLeadItem().editedBy.date)) {
            //Last Edited: Today, at 2:45 PM by Test Admin
            String strDate = data.getLeadItem().editedBy.date;
            String out = String.format(patternEditedBy, DateManager.getDateToNow(strDate), DateManager.getTime(strDate));
            if(!TextUtils.isEmpty(data.getLeadItem().editedBy.user))
                out = out + " by " + data.getLeadItem().editedBy.user;
            tvEditedBy_VLIL.setText(out);
        } else
            tvEditedBy_VLIL.setText(null);

        tvLeadName_LIL.requestLayout();
    }

}
