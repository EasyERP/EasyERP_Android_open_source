package com.thinkmobiles.easyerp.presentation.screens.hr.employees;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetImages;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseGetEmployees;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 3/13/2017.
 */

public interface EmployeesContract {
    interface EmployeeView extends BaseView<EmployeePresenter>, AlphabeticalView {
        void openDetailsScreen(String employeeID);
    }
    interface EmployeePresenter extends AlphabeticalPresenter {

    }
    interface EmployeeModel extends AlphabeticalModel {
        Observable<ResponseGetImages> getEmployeeImages(ArrayList<String> employeeIDs);
        Observable<ResponseGetTotalItems<EmployeeItem>> getEmployees(FilterHelper helper, String letter, int page);
    }
}
