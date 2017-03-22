package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import rx.Observable;

/**
 * Created by Lynx on 3/22/2017.
 */

public interface ApplicationDetailsContract {
    interface ApplicationDetailsView extends BaseView<ApplicationDetailsPresenter>, ContentView {

    }
    interface ApplicationDetailsPresenter extends ContentPresenter {

    }
    interface ApplicationDetailsModel extends BaseModel {
        Observable<ResponseEmployeeDetails> getApplicationDetails(String id);
    }
}
