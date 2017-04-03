package com.thinkmobiles.easyerp.presentation.screens.hr.vacations;

import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/29/2017.
 */

public interface VacationsListContract {
    interface VacationsListView extends BaseView<VacationsListPresenter>, AlphabeticalView {
        void openDetailsScreen(int year, int month);
    }
    interface VacationsListPresenter extends AlphabeticalPresenter {

    }
    interface VacationsListModel extends AlphabeticalModel {
        ArrayList<String> getMonths();
    }
}
