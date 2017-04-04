package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationPersonDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

import java.util.Calendar;

/**
 * Created by Lynx on 3/31/2017.
 */

public final class VacationPersonVH extends RecyclerVH<VacationPersonDH> {

    private ImageView ivEmployeeImage_VLIVP;
    private TextView tvEmployeeName_VLIVP;
    private TextView tvEmployeePosition_VLIVP;
    private TextView tvWorkingCount_VLIVP;
    private TextView tvOnLeaveCount_VLIVP;
    private TextView tvVacationCount_VLIVP;
    private TextView tvPersonalCount_VLIVP;
    private TextView tvSickCount_VLIVP;
    private TextView tvEducationCount_VLIVP;

    public VacationPersonVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivEmployeeImage_VLIVP = findView(R.id.ivEmployeeImage_VLIVP);
        tvEmployeeName_VLIVP = findView(R.id.tvEmployeeName_VLIVP);
        tvEmployeePosition_VLIVP = findView(R.id.tvEmployeePosition_VLIVP);
        tvWorkingCount_VLIVP = findView(R.id.tvWorkingCount_VLIVP);
        tvOnLeaveCount_VLIVP = findView(R.id.tvOnLeaveCount_VLIVP);
        tvVacationCount_VLIVP = findView(R.id.tvVacationCount_VLIVP);
        tvPersonalCount_VLIVP = findView(R.id.tvPersonalCount_VLIVP);
        tvSickCount_VLIVP = findView(R.id.tvSickCount_VLIVP);
        tvEducationCount_VLIVP = findView(R.id.tvEducationCount_VLIVP);
    }

    @Override
    public void bindData(VacationPersonDH data) {
        MonthDetail item = data.getMonthDetail();

        ivEmployeeImage_VLIVP.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);
        ImageHelper.getBitmapFromBase64(item.employee.employeeBase64Image, new CropCircleTransformation())
                .subscribe(bitmap -> {
                    if (bitmap != null)
                        ivEmployeeImage_VLIVP.setImageBitmap(bitmap);
                });
        tvEmployeeName_VLIVP.setText(item.employee.name.getFullName());
        tvEmployeePosition_VLIVP.setText(item.department.name);
        int v = 0;
        int p = 0;
        int s = 0;
        int e = 0;
        int l = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(item.year, item.month - 1, 1);
        for(String day : item.vacArray) {
            if(!TextUtils.isEmpty(day)) {
                switch (day) {
                    case "V":
                        v++;
                        break;
                    case "P":
                        p++;
                        break;
                    case "S":
                        s++;
                        break;
                    case "E":
                        e++;
                        break;
                }
                l = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? l : l + 1;
            }
        }
        int w = DateManager.getWorkingDaysInMonth(item.year, item.month - 1);
        w -= l;
        tvWorkingCount_VLIVP.setText(String.valueOf(w));
        tvOnLeaveCount_VLIVP.setText(String.valueOf(l));
        tvVacationCount_VLIVP.setText(String.valueOf(v));
        tvPersonalCount_VLIVP.setText(String.valueOf(p));
        tvSickCount_VLIVP.setText(String.valueOf(s));
        tvEducationCount_VLIVP.setText(String.valueOf(e));
    }
}
