package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview;

import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

/**
 * Created by Alex Michenko on 29.03.2017.
 */

public interface VacationOverviewContract {

    interface VacationOverviewView extends ContentView, BaseView<VacationOverviewPresenter> {

    }

    interface VacationOverviewPresenter extends ContentPresenter {

    }

    interface VacationOverviewModel extends BaseModel {

    }
}
