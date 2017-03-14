package com.thinkmobiles.easyerp.data.model.hr.employees;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;

/**
 * Created by Lynx on 3/13/2017.
 */

public class ResponseCommonEmployees {

    public ResponseGetTotalItems<EmployeeItem> responseGetEmployees;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public ResponseCommonEmployees(ResponseGetTotalItems<EmployeeItem> responseGetEmployees, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.responseGetEmployees = responseGetEmployees;
        this.responseGetImages = responseGetImages;
    }

}
