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

import java.util.Locale;

/**
 * Created by Lynx on 3/20/2017.
 */

public class RowEmploymentDetailsVH extends RecyclerVH<EmployeeRowTransferDH> {

    private TextView tvJobType_VLIRED;
    private TextView tvSchedule_VLIRED;
    private TextView tvContract_VLIRED;
    private TextView tvScheduledPay_VLIRED;
    private TextView tvSalary_VLIRED;

    public RowEmploymentDetailsVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvJobType_VLIRED = findView(R.id.tvJobType_VLIRED);
        tvSchedule_VLIRED = findView(R.id.tvSchedule_VLIRED);
        tvContract_VLIRED = findView(R.id.tvContract_VLIRED);
        tvScheduledPay_VLIRED = findView(R.id.tvScheduledPay_VLIRED);
        tvSalary_VLIRED = findView(R.id.tvSalary_VLIRED);
    }

    @Override
    public void bindData(EmployeeRowTransferDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.color.color_bg_product_details : android.R.color.white);
        EmployeeTransferItem item = data.getEmployeeTransferItem();
        tvJobType_VLIRED.setText(!TextUtils.isEmpty(item.jobType) ? item.jobType : null);
        if(item.weeklyScheduler != null && !TextUtils.isEmpty(item.weeklyScheduler.name)) {
            tvSchedule_VLIRED.setText(item.weeklyScheduler.name);
        } else {
            tvSchedule_VLIRED.setText(null);
        }
        if(item.payrollStructureType != null && !TextUtils.isEmpty(item.payrollStructureType.name)) {
            tvContract_VLIRED.setText(item.payrollStructureType.name);
        } else {
            tvContract_VLIRED.setText(null);
        }
        if(item.scheduledPay != null && !TextUtils.isEmpty(item.scheduledPay.name)) {
            tvScheduledPay_VLIRED.setText(item.scheduledPay.name);
        } else {
            tvScheduledPay_VLIRED.setText(null);
        }
        tvSalary_VLIRED.setText(String.format(Locale.US, "$ %d", item.salary));
    }
}
