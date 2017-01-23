package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail;

/**
 * Created by Asus_Dev on 1/23/2017.
 */

public enum DateFilterType {
    THIS_MONTH(0), THIS_FINANCIAL_YEAR(1), LAST_MONTH(2), LAST_QUARTER(3), LAST_FINANCIAL_YEAR(4), CUSTOM_DATES(5);

    DateFilterType(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    static DateFilterType valueOf(int type) {
        for (DateFilterType filterType: values())
            if (filterType.type == type)
                return filterType;
        return THIS_MONTH;
    }

}
