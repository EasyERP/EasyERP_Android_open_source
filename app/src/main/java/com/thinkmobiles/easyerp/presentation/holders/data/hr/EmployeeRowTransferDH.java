package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.EmployeeTransferItem;

/**
 * Created by Lynx on 3/20/2017.
 */

public class EmployeeRowTransferDH extends RecyclerDH {
    private EmployeeTransferItem employeeTransferItem;

    public EmployeeRowTransferDH(EmployeeTransferItem employeeTransferItem) {
        this.employeeTransferItem = employeeTransferItem;
    }

    public EmployeeTransferItem getEmployeeTransferItem() {
        return employeeTransferItem;
    }
}
