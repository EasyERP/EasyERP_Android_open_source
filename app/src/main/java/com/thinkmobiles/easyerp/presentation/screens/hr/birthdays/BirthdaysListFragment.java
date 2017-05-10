package com.thinkmobiles.easyerp.presentation.screens.hr.birthdays;

import com.thinkmobiles.easyerp.domain.hr.EmployeesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.EmployeesWithBirthdayAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.hr.employees.details.EmployeeDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class BirthdaysListFragment extends MasterSelectableFragment implements BirthdaysListContract.BirthdaysListView {

    private BirthdaysListContract.BirthdaysListPresenter presenter;

    @Bean
    protected EmployeesWithBirthdayAdapter employeesWithBirthdayAdapter;
    @Bean
    protected EmployeesRepository employeesRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new BirthdaysListPresenter(this, employeesRepository);
    }

    @Override
    public void setPresenter(BirthdaysListContract.BirthdaysListPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Birthdays list screen";
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return employeesWithBirthdayAdapter;
    }

    @Override
    public void openEmployeeDetail(String id) {
        if (id != null) {
            getMasterDelegate().replaceFragmentContentDetail(EmployeeDetailsFragment_.builder()
                    .employeeID(id)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }
}
