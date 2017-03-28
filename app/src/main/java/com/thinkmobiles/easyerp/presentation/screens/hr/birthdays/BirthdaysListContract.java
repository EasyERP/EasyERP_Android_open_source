package com.thinkmobiles.easyerp.presentation.screens.hr.birthdays;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.BirthdaysResponse;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface BirthdaysListContract {
    interface BirthdaysListView extends BaseView<BirthdaysListPresenter>, SelectableView {
        void openEmployeeDetail(final String id);
    }
    interface BirthdaysListPresenter extends SelectablePresenter {

    }
    interface BirthdaysListModel extends BaseModel {
        Observable<ResponseGetTotalItems<ImageItem>> getEmployeeImages(ArrayList<String> employeeIDs);
        Observable<BirthdaysResponse> getBirthdaysInEmployees();
    }
}
