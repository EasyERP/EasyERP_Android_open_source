package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.EmployeeWithBirthday;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeBirthdayDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class EmployeeBirthdayVH extends SelectableVHHelper<EmployeeBirthdayDH> {

    private final ImageView ivEmployeeImage_VLIEB;
    private final TextView tvEmployeeName_VLIEB, tvEmployeeDepartment_VLIEB, tvEmployeePosition_VLIEB, tvEmployeeBirthdayDate_VLIEB;

    private final TextView tvTitleHeader_VLIEBH;

    private final String employeeBirthdayPattern;

    public EmployeeBirthdayVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivEmployeeImage_VLIEB = findView(R.id.ivEmployeeImage_VLIEB);
        tvEmployeeName_VLIEB = findView(R.id.tvEmployeeName_VLIEB);
        tvEmployeeDepartment_VLIEB = findView(R.id.tvEmployeeDepartment_VLIEB);
        tvEmployeePosition_VLIEB = findView(R.id.tvEmployeePosition_VLIEB);
        tvEmployeeBirthdayDate_VLIEB = findView(R.id.tvEmployeeBirthdayDate_VLIEB);

        tvTitleHeader_VLIEBH = findView(R.id.tvTitleHeader_VLIEBH);

        employeeBirthdayPattern = itemView.getResources().getString(R.string.pattern_birthday_date);
    }

    @Override
    public void bindData(EmployeeBirthdayDH data) {
        super.bindData(data);

        tvTitleHeader_VLIEBH.setVisibility(TextUtils.isEmpty(data.getHeaderTitle()) ? View.GONE : View.VISIBLE);
        tvTitleHeader_VLIEBH.setText(data.getHeaderTitle());

        ivEmployeeImage_VLIEB.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);

        ImageHelper.getBitmapFromBase64(data.getBase64Image(), new CropCircleTransformation())
                .subscribe(bitmap -> {
                    if (bitmap != null)
                        ivEmployeeImage_VLIEB.setImageBitmap(bitmap);

                });

        final EmployeeWithBirthday employeeWithBirthday = data.getEmployeeWithBirthday();
        if (employeeWithBirthday.name != null) {
            tvEmployeeName_VLIEB.setText(employeeWithBirthday.name.getFullName());
        } else {
            tvEmployeeName_VLIEB.setText(null);
        }

        if(employeeWithBirthday.department != null && !TextUtils.isEmpty(employeeWithBirthday.department.name)) {
            tvEmployeeDepartment_VLIEB.setText(employeeWithBirthday.department.name);
        } else {
            tvEmployeeDepartment_VLIEB.setText(null);
        }

        if(employeeWithBirthday.jobPosition != null && !TextUtils.isEmpty(employeeWithBirthday.jobPosition.name)) {
            tvEmployeePosition_VLIEB.setText(employeeWithBirthday.jobPosition.name);
        } else {
            tvEmployeePosition_VLIEB.setText(null);
        }

        tvEmployeeBirthdayDate_VLIEB.setText(String.format(employeeBirthdayPattern,
                new DateManager.DateConverter(employeeWithBirthday.dateBirth).setDstPattern(DateManager.PATTERN_DASHBOARD_PREVIEW).toString(),
                employeeWithBirthday.age));
    }
}
