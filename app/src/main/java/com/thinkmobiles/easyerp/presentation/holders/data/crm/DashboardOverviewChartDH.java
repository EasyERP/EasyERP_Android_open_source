package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Asus_Dev on 1/26/2017.
 */

public class DashboardOverviewChartDH extends RecyclerDH {

    private final String labelWorkflow;
    private final int valueCount;

    public DashboardOverviewChartDH(String labelWorkflow, int valueCount) {
        this.labelWorkflow = labelWorkflow;
        this.valueCount = valueCount;
    }

    public String getLabelWorkflow() {
        return labelWorkflow;
    }

    public int getValueCount() {
        return valueCount;
    }

}
