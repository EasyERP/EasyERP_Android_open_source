package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Lynx on 3/13/2017.
 */

public final class EmployeeVH extends SelectableVHHelper<EmployeeDH> {

    private ImageView ivEmployeeImage_VLIE;
    private TextView tvEmployeeName_VLIE;
    private TextView tvEmployeeEmail_VLIE;
    private TextView tvEmployeeSkype_VLIE;
    private TextView tvEmployeePhone_VLIE;
    private TextView tvEmployeeDepartment_VLIE;
    private TextView tvEmployeePosition_VLIE;

    public EmployeeVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivEmployeeImage_VLIE = findView(R.id.ivEmployeeImage_VLIE);
        tvEmployeeName_VLIE = findView(R.id.tvEmployeeName_VLIE);
        tvEmployeeEmail_VLIE = findView(R.id.tvEmployeeEmail_VLIE);
        tvEmployeeSkype_VLIE = findView(R.id.tvEmployeeSkype_VLIE);
        tvEmployeePhone_VLIE = findView(R.id.tvEmployeePhone_VLIE);
        tvEmployeeDepartment_VLIE = findView(R.id.tvEmployeeDepartment_VLIE);
        tvEmployeePosition_VLIE = findView(R.id.tvEmployeePosition_VLIE);
    }

    @Override
    public void bindData(EmployeeDH data) {
        super.bindData(data);

        ivEmployeeImage_VLIE.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);

        EmployeeItem item = data.getEmployeeItem();
        ImageHelper.getBitmapFromBase64(data.getBase64Image(), new CropCircleTransformation())
                .subscribe(bitmap -> {
                    if (bitmap != null)
                        ivEmployeeImage_VLIE.setImageBitmap(bitmap);

                });
        if (item.name != null) {
            tvEmployeeName_VLIE.setText(item.name.getFullName());
        } else {
            tvEmployeeName_VLIE.setText(null);
        }

        if(item.department != null && !TextUtils.isEmpty(item.department.name)) {
            tvEmployeeDepartment_VLIE.setText(item.department.name);
        } else {
            tvEmployeeDepartment_VLIE.setText(null);
        }

        if(item.jobPosition != null && !TextUtils.isEmpty(item.jobPosition.name)) {
            tvEmployeePosition_VLIE.setText(item.jobPosition.name);
        } else {
            tvEmployeePosition_VLIE.setText(null);
        }

        tvEmployeeEmail_VLIE.setVisibility(item.workEmail.isEmpty() ? View.GONE : View.VISIBLE);
        tvEmployeePosition_VLIE.setVisibility((item.jobPosition != null && !TextUtils.isEmpty(item.jobPosition.name)) ? View.VISIBLE : View.GONE);
        tvEmployeeSkype_VLIE.setVisibility(item.skype.isEmpty() ? View.GONE : View.VISIBLE);
        tvEmployeePhone_VLIE.setVisibility((item.workPhones != null
                && (!TextUtils.isEmpty(item.workPhones.fax) || !TextUtils.isEmpty(item.workPhones.mobile) || !TextUtils.isEmpty(item.workPhones.phone)))
                ? View.VISIBLE : View.GONE);

        tvEmployeeEmail_VLIE.setText(item.workEmail);
        tvEmployeeSkype_VLIE.setText(item.skype);
        if (item.workPhones != null) {
            if (!TextUtils.isEmpty(item.workPhones.mobile)) {
                tvEmployeePhone_VLIE.setText(item.workPhones.mobile);
            } else if (!TextUtils.isEmpty(item.workPhones.phone)) {
                tvEmployeePhone_VLIE.setText(item.workPhones.phone);
            } else if (!TextUtils.isEmpty(item.workPhones.fax)) {
                tvEmployeeEmail_VLIE.setText(item.workPhones.fax);
            } else {
                tvEmployeePhone_VLIE.setText(null);
            }
        } else {
            tvEmployeePhone_VLIE.setText(null);
        }
    }
}
