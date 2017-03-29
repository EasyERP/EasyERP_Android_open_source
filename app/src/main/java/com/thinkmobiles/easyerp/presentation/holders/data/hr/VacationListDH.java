package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * Created by Lynx on 3/29/2017.
 */

public class VacationListDH extends SelectableDHHelper {

    private String month;

    public VacationListDH(String month) {
        this.month = month;
    }

    @Override
    public String getId() {
        return month;
    }

    public String getData() {
        return month;
    }
}
