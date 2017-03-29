package com.thinkmobiles.easyerp.presentation.screens.hr.attendance;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/20/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface AttendanceListContract {
    interface AttendanceListView extends BaseView<AttendanceListPresenter>, SelectableView {
        void openAttendanceDetail(String id, String fullName);
    }
    interface AttendanceListPresenter extends SelectablePresenter {

    }
    interface AttendanceListModel extends BaseModel {
        Observable<ResponseGetTotalItems<ImageItem>> getEmployeeImages(final ArrayList<String> employeeIDs);
        Observable<ResponseGetTotalItems<EmployeeItem>> getAllEmployees();
    }
}
