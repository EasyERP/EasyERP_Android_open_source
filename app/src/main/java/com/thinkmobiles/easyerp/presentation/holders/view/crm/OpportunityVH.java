package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts.DollarFormatter;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.Locale;

/**
 * Created by Lynx on 1/30/2017.
 */

public final class OpportunityVH extends MasterFlowSelectableVHHelper<OpportunityDH> {

    private final TextView tvOpportunityName_VLIO;
    private final TextView tvStage_VLIO;
    private final TextView tvAssignedTo_VLIO;
    private final TextView tvRevenue_VLIO;
    private final TextView tvEditedBy_VLIO;

    private String patternEditedBy;

    public OpportunityVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvOpportunityName_VLIO = findView(R.id.tvOpportunityName_VLIO);
        tvStage_VLIO = findView(R.id.tvStage_VLIO);
        tvAssignedTo_VLIO = findView(R.id.tvAssignedTo_VLIO);
        tvRevenue_VLIO = findView(R.id.tvRevenue_VLIO);
        tvEditedBy_VLIO = findView(R.id.tvEditedBy_VLIO);

        patternEditedBy = itemView.getContext().getString(R.string.pattern_last_edited);
    }

    @Override
    public void bindData(OpportunityDH data) {
        super.bindData(data);

        OpportunityListItem item = data.getOpportunityListItem();

        tvOpportunityName_VLIO.setText(TextUtils.isEmpty(item.name) ? null : item.name);

        if(data.getOpportunityListItem().workflow != null && !TextUtils.isEmpty(data.getOpportunityListItem().workflow.name)) {
            tvStage_VLIO.setText(data.getOpportunityListItem().workflow.name);
            tvStage_VLIO.setBackground(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(),
                    TagHelper.getOpportunityStatusColorRes(item.workflow.status))));
        }
        else {
            tvStage_VLIO.setBackgroundResource(0);
        }
        if(item.salesPerson != null && !TextUtils.isEmpty(item.salesPerson.name)) {
            tvAssignedTo_VLIO.setText(item.salesPerson.name);
        } else {
            tvAssignedTo_VLIO.setText(itemView.getContext().getString(R.string.not_assigned));
        }

        if(item.expectedRevenue != null) {
            String prefix = TextUtils.isEmpty(item.expectedRevenue.currency) ? "$" : item.expectedRevenue.currency;
            tvRevenue_VLIO.setText(StringUtil.getFormattedPrice(
                    new DollarFormatter().getFormat(),
                    (double) item.expectedRevenue.value,
                    prefix));
        } else {
            tvRevenue_VLIO.setText(null);
        }

        if(data.getOpportunityListItem().editedBy != null && !TextUtils.isEmpty(data.getOpportunityListItem().editedBy.date)) {
            //Last Edited: Today, at 2:45 PM by Test Admin
            String strDate = data.getOpportunityListItem().editedBy.date;
            String out = String.format(patternEditedBy, DateManager.getDateToNow(strDate), DateManager.getTime(strDate));
            if(!TextUtils.isEmpty(data.getOpportunityListItem().editedBy.user))
                out = out + " by " + data.getOpportunityListItem().editedBy.user;
            tvEditedBy_VLIO.setText(out);
        } else
            tvEditedBy_VLIO.setText(null);
    }
}
