package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * Created by Lynx on 3/13/2017.
 */

public final class EmployeeDH extends SelectableDHHelper {

    private EmployeeItem employeeItem;
    private String base64Image;

    public EmployeeDH(EmployeeItem employeeItem, String base64Image) {
        this.employeeItem = employeeItem;
        this.base64Image = base64Image;
    }

    public EmployeeItem getEmployeeItem() {
        return employeeItem;
    }

    public String getBase64Image() {
        return base64Image;
    }

    @Override
    public String getId() {
        return employeeItem.id;
    }
}
