package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

/**
 * Created by Lynx on 1/16/2017.
 */

public final class LeadVH extends MasterFlowSelectableVHHelper<LeadDH> {

    private final TextView tvLeadName_LIL;
    private final TextView tvStage_LIL;
    private final TextView tvPriority_LIL;
    private final TextView tvSource_LIL;
    private final TextView tvAssignedTo_LIL;
    private final TextView tvEditedBy_VLIL;

    private final String noData;

    public LeadVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvLeadName_LIL = findView(R.id.tvLeadName_LIL);
        tvStage_LIL = findView(R.id.tvStage_LIL);
        tvPriority_LIL = findView(R.id.tvPriority_LIL);
        tvSource_LIL = findView(R.id.tvSource_LIL);
        tvAssignedTo_LIL = findView(R.id.tvAssignedTo_LIL);
        tvEditedBy_VLIL = findView(R.id.tvEditedBy_VLIL);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(LeadDH data) {
        super.bindData(data);

        if(!TextUtils.isEmpty(data.getLeadItem().name))
            tvLeadName_LIL.setText(data.getLeadItem().name);
        else
            tvLeadName_LIL.setText(noData);
        if(data.getLeadItem().workflow != null && !TextUtils.isEmpty(data.getLeadItem().workflow.name))
            tvStage_LIL.setText(data.getLeadItem().workflow.name);
        else
            tvStage_LIL.setText(noData);
        if(!TextUtils.isEmpty(data.getLeadItem().priority)) {
            tvPriority_LIL.setText(data.getLeadItem().priority.toUpperCase());
            tvPriority_LIL.setBackgroundDrawable(
                    new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getColorResIdByName(data.getLeadItem().priority))));
            tvPriority_LIL.setVisibility(View.VISIBLE);
        } else {
            tvPriority_LIL.setText(null);
            tvPriority_LIL.setVisibility(View.GONE);
        }
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

        tvLeadName_LIL.requestLayout();
    }

}
