package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
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
        tvPersonName_LILH.setText(StringUtil.getField(note.user.login));
        if (note != null) {
            tvAction_LILH.setText("left a note");
            tvMessage_LILH.setText(note.note);
        } else if (note.task != null){
            tvAction_LILH.setText("created task");
            tvMessage_LILH.setText("Assigned to " + note.task.assignedTo.fullName);
        } else if (note.history != null) {
            tvAction_LILH.setText("changed");
            tvMessage_LILH.setText(String.format(
                    Locale.ENGLISH,
                    "%s from %s to %s",
                    note.history.changedField,
                    note.history.prevValue,
                    note.history.newValue
                    ));
        } else {
            tvAction_LILH.setText(null);
            tvMessage_LILH.setText(null);
        }
    }
}
