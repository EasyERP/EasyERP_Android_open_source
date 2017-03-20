package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.EmployeeTransferItem;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeRowTransferDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

/**
 * Created by Lynx on 3/20/2017.
 */

public class RowJobPositionVH extends RecyclerVH<EmployeeRowTransferDH> {

    private TextView tvStatus_VLIRJP;
    private TextView tvDate_VLIRJP;
    private TextView tvJobPosition_VLIRJP;
    private TextView tvDepartment_VLIRJP;
    private TextView tvManager_VLIRJP;

    public RowJobPositionVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvStatus_VLIRJP = findView(R.id.tvStatus_VLIRJP);
        tvDate_VLIRJP = findView(R.id.tvDate_VLIRJP);
        tvJobPosition_VLIRJP = findView(R.id.tvJobPosition_VLIRJP);
        tvDepartment_VLIRJP = findView(R.id.tvDepartment_VLIRJP);
        tvManager_VLIRJP = findView(R.id.tvManager_VLIRJP);
    }

    @Override
    public void bindData(EmployeeRowTransferDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.color.color_bg_product_details : android.R.color.white);
        EmployeeTransferItem item = data.getEmployeeTransferItem();
        tvStatus_VLIRJP.setText(!TextUtils.isEmpty(item.status) ? item.status : null);
        tvDate_VLIRJP.setText(!TextUtils.isEmpty(item.date)
                ? DateManager.convert(item.date).setDstPattern(DateManager.PATTERN_SIMPLE_DATE_SHORT).toString()
                : null);
        if(item.jobPosition != null && !TextUtils.isEmpty(item.jobPosition.name)) {
            tvJobPosition_VLIRJP.setText(item.jobPosition.name);
        } else {
            tvJobPosition_VLIRJP.setText(null);
        }
        if(item.department != null && !TextUtils.isEmpty(item.department.name)) {
            tvDepartment_VLIRJP.setText(item.department.name);
        } else {
            tvDepartment_VLIRJP.setText(null);
        }
        if(item.manager != null && item.manager.name != null) {
            tvManager_VLIRJP.setText(StringUtil.getFullName(item.manager.name.first, item.manager.name.last));
        } else {
            tvManager_VLIRJP.setText(null);
        }
    }
}
