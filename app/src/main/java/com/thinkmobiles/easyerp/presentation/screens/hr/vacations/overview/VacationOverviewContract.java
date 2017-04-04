package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.DayDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationRangeDH;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 29.03.2017.
 */

public interface VacationOverviewContract {

    interface VacationOverviewView extends ContentView, BaseView<VacationOverviewPresenter> {
        void setTitle(String title);
        void setPosition(String position);
        void setCountWorking(String countWorking);
        void setCountVacation(String countVacation);
        void setCountPersonal(String countPersonal);
        void setCountSick(String countSick);
        void setCountEducation(String countEducation);
        void setCountLeave(String countLeave);
        void initMonth(String title, ArrayList<DayDH> dayDHs);
        void setRanges(ArrayList<VacationRangeDH> ranges);
    }

    interface VacationOverviewPresenter extends ContentPresenter {

    }
}
