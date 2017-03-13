package com.thinkmobiles.easyerp.presentation.holders.view.inventory;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.GoodsOutNoteItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.GoodsOutNoteDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

/**
 * Created by Lynx on 3/7/2017.
 */

public final class GoodsOutNoteVH extends SelectableVHHelper<GoodsOutNoteDH> {

    private TextView tvId_VLIGON;
    private TextView tvStatus_VLIGON;
    private ImageView ivPrinted_VLIGON;
    private ImageView ivPicked_VLIGON;
    private ImageView ivPacked_VLIGON;
    private ImageView ivShipped_VLIGON;
    private TextView tvDeliverTo_VLIGON;
    private TextView tvCreatedDate_VLIGON;
    private TextView tvWarehouse_VLIGON;

    private String not_assigned;
    private String patternCreated;

    public GoodsOutNoteVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvId_VLIGON = findView(R.id.tvId_VLIGON);
        tvStatus_VLIGON = findView(R.id.tvStatus_VLIGON);
        ivPrinted_VLIGON = findView(R.id. ivPrinted_VLIGON);
        ivPicked_VLIGON = findView(R.id. ivPicked_VLIGON);
        ivPacked_VLIGON = findView(R.id. ivPacked_VLIGON);
        ivShipped_VLIGON = findView(R.id. ivShipped_VLIGON);
        tvDeliverTo_VLIGON = findView(R.id.tvDeliverTo_VLIGON);
        tvCreatedDate_VLIGON = findView(R.id.tvCreatedDate_VLIGON);
        tvWarehouse_VLIGON = findView(R.id.tvWarehouse_VLIGON);

        not_assigned = itemView.getContext().getString(R.string.not_assigned);
        patternCreated = itemView.getContext().getString(R.string.pattern_created);
    }

    @Override
    public void bindData(GoodsOutNoteDH data) {
        super.bindData(data);
        GoodsOutNoteItem item = data.getItem();

        tvId_VLIGON.setText(item.name);
        tvStatus_VLIGON.setText(item.workflow.name);
        tvStatus_VLIGON.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getColorResIdByName(item.workflow.status))));

        ivPrinted_VLIGON.setImageResource(item.printed ? R.drawable.ic_print : R.drawable.ic_print_off);
        ivPicked_VLIGON.setImageResource(item.picked ? R.drawable.ic_allocated : R.drawable.ic_allocated_off);
        ivPacked_VLIGON.setImageResource(item.packed ? R.drawable.ic_fulfilled : R.drawable.ic_fulfilled_off);
        ivShipped_VLIGON.setImageResource(item.shipped ? R.drawable.ic_shipped : R.drawable.ic_shipped_off);

        if(item.customer != null && !TextUtils.isEmpty(item.customer.name)) tvDeliverTo_VLIGON.setText(item.customer.name);
        else tvDeliverTo_VLIGON.setText(not_assigned);

        if(item.createdBy != null && !TextUtils.isEmpty(item.createdBy.date)) {
            tvCreatedDate_VLIGON.setText(String.format(patternCreated, new DateManager.DateConverter(item.createdBy.date)
                    .setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW)
                    .toString()));
        } else
            tvCreatedDate_VLIGON.setText(null);
        if(item.warehouse != null && !TextUtils.isEmpty(item.warehouse.name)) {
            tvWarehouse_VLIGON.setText(item.warehouse.name);
        } else
            tvWarehouse_VLIGON.setText(null);
    }
}
