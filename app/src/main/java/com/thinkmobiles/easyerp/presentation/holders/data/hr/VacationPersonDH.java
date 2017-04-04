package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;

/**
 * Created by Lynx on 3/31/2017.
 */

public class VacationPersonDH extends RecyclerDH {

    private MonthDetail monthDetail;

    public VacationPersonDH(MonthDetail monthDetail) {
        this.monthDetail = monthDetail;
    }

    public MonthDetail getMonthDetail() {
        return monthDetail;
    }
}
