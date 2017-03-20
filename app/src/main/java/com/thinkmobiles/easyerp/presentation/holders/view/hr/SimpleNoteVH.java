package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.SimpleNoteItem;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * Created by Lynx on 3/20/2017.
 */

public class SimpleNoteVH extends RecyclerVH<SimpleNoteDH> {

    private TextView tvNoteDateDate_VLISN;
    private TextView tvNoteTime_VLISN;
    private TextView tvPersonName_VLISN;
    private TextView tvMessage_VLISN;

    public SimpleNoteVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvNoteDateDate_VLISN = findView(R.id.tvNoteDateDate_VLISN);
        tvNoteTime_VLISN = findView(R.id.tvNoteTime_VLISN);
        tvPersonName_VLISN = findView(R.id.tvPersonName_VLISN);
        tvMessage_VLISN = findView(R.id.tvMessage_VLISN);

        View line = findView(R.id.vLine_VLISN);
        ViewGroup.LayoutParams params = line.getLayoutParams();
        params.height = viewType == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : 50;
        line.setLayoutParams(params);
    }

    @Override
    public void bindData(SimpleNoteDH data) {
        SimpleNoteItem item = data.getSimpleNoteItem();
        tvNoteTime_VLISN.setText(TextUtils.isEmpty(item.date) ? null : DateManager.getTime(item.date));
        tvNoteDateDate_VLISN.setText(TextUtils.isEmpty(item.date) ? null : DateManager.getShortDate(item.date));
        tvPersonName_VLISN.setText(StringUtil.getSpannedByUser(StringUtil.getField(item.author, "Unknown")));
        tvMessage_VLISN.setText(!TextUtils.isEmpty(item.note) ? item.note : null);
    }
}
