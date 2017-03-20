package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * @author Michael Soyma (Created on 3/20/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class AttendanceEmployeeVH extends SelectableVHHelper<EmployeeDH> {

    private ImageView ivEmployeeImage_VLIA;
    private TextView tvEmployeeName_VLIA, tvEmployeeDepartment_VLIA, tvIsEmployee_VLIA;

    public AttendanceEmployeeVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvIsEmployee_VLIA = findView(R.id.tvIsEmployee_VLIA);
        ivEmployeeImage_VLIA = findView(R.id.ivEmployeeImage_VLIA);
        tvEmployeeName_VLIA = findView(R.id.tvEmployeeName_VLIA);
        tvEmployeeDepartment_VLIA = findView(R.id.tvEmployeeDepartment_VLIA);

        tvIsEmployee_VLIA.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), R.color.color_not_employee)));
    }

    @Override
    public void bindData(EmployeeDH data) {
        super.bindData(data);

        ivEmployeeImage_VLIA.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);
        ImageHelper.getBitmapFromBase64(data.getBase64Image(), new CropCircleTransformation())
                .subscribe(bitmap -> {
                    if (bitmap != null)
                        ivEmployeeImage_VLIA.setImageBitmap(bitmap);
                });

        final EmployeeItem employeeItem = data.getEmployeeItem();

        tvIsEmployee_VLIA.setVisibility(employeeItem.isEmployee ? View.GONE : View.VISIBLE);
        if (employeeItem.name != null) {
            tvEmployeeName_VLIA.setText(employeeItem.name.getFullName());
        } else {
            tvEmployeeName_VLIA.setText(null);
        }
        if(employeeItem.department != null && !TextUtils.isEmpty(employeeItem.department.name)) {
            tvEmployeeDepartment_VLIA.setText(employeeItem.department.name);
        } else {
            tvEmployeeDepartment_VLIA.setText(null);
        }
    }
}
