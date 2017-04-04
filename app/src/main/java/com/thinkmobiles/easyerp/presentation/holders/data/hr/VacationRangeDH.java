package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Alex Michenko on 04.04.2017.
 */

public class VacationRangeDH extends RecyclerDH {

    private String type;
    private String range;
    private int color;

    public VacationRangeDH(String type, String range, int color) {
        this.type = getTypeName(type);
        this.range = range;
        this.color = color;
    }

    private String getTypeName(String type) {
        switch (type) {
            case "V":
                return "On Vacation";
            case "P":
                return "On Personal";
            case "S":
                return "On Sick";
            case "E":
                return "On Education";
            default:
                return type;
        }
    }

    public String getType() {
        return type;
    }

    public String getRange() {
        return range;
    }

    public int getColor() {
        return color;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
