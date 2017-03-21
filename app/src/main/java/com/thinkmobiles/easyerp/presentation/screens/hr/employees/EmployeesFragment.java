package com.thinkmobiles.easyerp.presentation.screens.hr.employees;

import android.widget.Toast;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.domain.hr.EmployeesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.EmployeesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.gallery.GalleryActivity_;
import com.thinkmobiles.easyerp.presentation.screens.hr.employees.details.EmployeeDetailsFragment;
import com.thinkmobiles.easyerp.presentation.screens.hr.employees.details.EmployeeDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/13/2017.
 */

@EFragment
public class EmployeesFragment extends MasterAlphabeticalFragment implements EmployeesContract.EmployeeView {

    private EmployeesContract.EmployeePresenter presenter;

    @Bean
    protected EmployeesAdapter employeesAdapter;
    @Bean
    protected EmployeesRepository employeesRepository;

    @Override
    protected SelectableAdapter getAdapter() {
        return employeesAdapter;
    }

    @Override
    protected AlphabeticalPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new EmployeesPresenter(this, employeesRepository);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        alphabetView.setListener(letter -> {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_FILTER, "Letter " + letter);
            presenter.setLetter(letter);
        });
    }

    @Override
    public void setPresenter(EmployeesContract.EmployeePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Employees list screen";
    }

    @Override
    public void openDetailsScreen(String employeeID) {
        if (employeeID != null) {
            mActivity.replaceFragmentContentDetail(EmployeeDetailsFragment_.builder()
                    .employeeID(employeeID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
