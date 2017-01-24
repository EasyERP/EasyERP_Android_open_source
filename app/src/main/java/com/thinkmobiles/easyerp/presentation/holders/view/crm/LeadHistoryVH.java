package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadNote;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadHistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.Locale;


public class LeadHistoryVH extends RecyclerVH<LeadHistoryDH> {

    private ImageView ivIconType_LILH;
    private TextView tvPersonName_LILH;
    private TextView tvDate_LILH;
    private TextView tvAction_LILH;
    private TextView tvMessage_LILH;

    public LeadHistoryVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        ivIconType_LILH = findView(R.id.ivIconType_LILH);
        tvPersonName_LILH = findView(R.id.tvPersonName_LILH);
        tvDate_LILH = findView(R.id.tvDate_LILH);
        tvAction_LILH = findView(R.id.tvAction_LILH);
        tvMessage_LILH = findView(R.id.tvMessage_LILH);
        View line = findView(R.id.vLine_LILH);
        ViewGroup.LayoutParams params = line.getLayoutParams();
        params.height = viewType == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : 50;
        line.setLayoutParams(params);
    }

    @Override
    public void bindData(LeadHistoryDH data) {
        LeadNote note = data.getModel();
        tvDate_LILH.setText(DateManager.convertLeadDate(note.date));
        tvPersonName_LILH.setText(StringUtil.getField(note.user.login, "Unknown"));
        tvAction_LILH.setText(StringUtil.getNoteAction(note));
        tvMessage_LILH.setText(StringUtil.getNoteMessage(note));
        if (note.task != null) {
            ivIconType_LILH.setImageResource(R.drawable.ic_circle);
        } else if (note.history != null) {
            ivIconType_LILH.setImageResource(R.drawable.ic_dollar);
        } else {
            ivIconType_LILH.setImageResource(R.drawable.ic_calendar);
        }
    }
}
