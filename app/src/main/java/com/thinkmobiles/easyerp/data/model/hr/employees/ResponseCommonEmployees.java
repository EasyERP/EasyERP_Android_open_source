package com.thinkmobiles.easyerp.data.model.hr.employees;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;

/**
 * Created by Lynx on 3/13/2017.
 */

public class ResponseCommonEmployees {

    public ResponseGetTotalItems<EmployeeItem> responseGetEmployees;
    public ResponseGetImages responseGetImages;

    public ResponseCommonEmployees(ResponseGetTotalItems<EmployeeItem> responseGetEmployees, ResponseGetImages responseGetImages) {
        this.responseGetEmployees = responseGetEmployees;
        this.responseGetImages = responseGetImages;
    }

}
