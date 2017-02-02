package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.HistoryDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;


public final class HistoryVH extends RecyclerVH<HistoryDH> {

    private final ImageView ivIconType_LILH;
    private final TextView tvPersonName_LILH;
    private final TextView tvAction_LILH;
    private final TextView tvMessage_LILH;
    private final TextView tvNoteTime_LILH;
    private final TextView tvNoteDateDate_LILH;

    public HistoryVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        ivIconType_LILH = findView(R.id.ivIconType_LILH);
        tvPersonName_LILH = findView(R.id.tvPersonName_LILH);
        tvAction_LILH = findView(R.id.tvAction_LILH);
        tvMessage_LILH = findView(R.id.tvMessage_LILH);
        tvNoteTime_LILH = findView(R.id.tvNoteTime_LILH);
        tvNoteDateDate_LILH = findView(R.id.tvNoteDateDate_LILH);

        View line = findView(R.id.vLine_LILH);
        ViewGroup.LayoutParams params = line.getLayoutParams();
        params.height = viewType == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : 50;
        line.setLayoutParams(params);
    }

    @Override
    public void bindData(HistoryDH data) {
        NoteItem note = data.getModel();

        tvNoteTime_LILH.setText(TextUtils.isEmpty(note.date) ? "" : DateManager.getTime(note.date));
        tvNoteDateDate_LILH.setText(TextUtils.isEmpty(note.date) ? "" : DateManager.getShortDate(note.date));

        if(note.user != null) {
            tvPersonName_LILH.setText(StringUtil.getSpannedByUser(StringUtil.getField(note.user.login, "Unknown")));
        } else
            tvPersonName_LILH.setText(StringUtil.getSpannedByUser("Unknown"));
        tvAction_LILH.setText(StringUtil.getNoteAction(note));
        tvMessage_LILH.setText(StringUtil.getNoteMessage(note));
        if (note.task != null) {
            ivIconType_LILH.setImageResource(R.drawable.ic_circle);
        } else if (note.history != null) {
            if(note.history.collectionName.toLowerCase().equals("persons")) {
                ivIconType_LILH.setImageResource(R.drawable.ic_person_outline_black_24dp);
            } else {
                ivIconType_LILH.setImageResource(R.drawable.ic_dollar);
            }
        } else {
            ivIconType_LILH.setImageResource(R.drawable.ic_calendar);
        }
    }
}
