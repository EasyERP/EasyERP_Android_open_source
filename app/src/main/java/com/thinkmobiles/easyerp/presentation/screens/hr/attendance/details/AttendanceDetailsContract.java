package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * Created by Alex Michenko on 22.03.2017.
 */

public interface AttendanceDetailsContract {

    interface AttendanceDetailsView extends ContentView, BaseView<AttendanceDetailsPresenter> {

    }

    interface AttendanceDetailsPresenter extends ContentPresenter {

    }

    interface AttendanceDetailsModel extends BaseModel {

    }
}
