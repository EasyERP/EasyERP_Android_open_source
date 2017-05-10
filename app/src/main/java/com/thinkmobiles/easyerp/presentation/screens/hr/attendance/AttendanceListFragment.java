package com.thinkmobiles.easyerp.presentation.screens.hr.attendance;

import com.thinkmobiles.easyerp.domain.hr.EmployeesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.AttendanceListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details.AttendanceDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/20/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class AttendanceListFragment extends MasterSelectableFragment implements AttendanceListContract.AttendanceListView {

    private AttendanceListContract.AttendanceListPresenter presenter;

    @Bean
    protected EmployeesRepository employeesRepository;
    @Bean
    protected AttendanceListAdapter attendanceListAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new AttendanceListPresenter(this, employeesRepository);
    }

    @Override
    public void setPresenter(AttendanceListContract.AttendanceListPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Attendance list screen";
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return attendanceListAdapter;
    }

    @Override
    public void openAttendanceDetail(String id, String fullName) {
        if (id != null) {
            getMasterDelegate().replaceFragmentContentDetail(AttendanceDetailsFragment_.builder()
                    .id(id)
                    .fullName(fullName)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }
}
