package com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.hr.AttendanceRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.managers.CalendarManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.details.DetailsActivity_;
import com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview.VacationOverviewFragment;
import com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview.VacationOverviewFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Alex Michenko on 22.03.2017.
 */
@EFragment
public class AttendanceDetailsFragment extends ContentFragment implements AttendanceDetailsContract.AttendanceDetailsView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_attendance_details;
    }

    @FragmentArg
    protected String id;
    @FragmentArg
    protected String fullName;

    private AttendanceDetailsContract.AttendanceDetailsPresenter presenter;
    private CalendarManager calendarManager;

    @ViewById
    protected RecyclerView rvYear_FAD;
    @ViewById
    protected TextView tvYear_FAD;
    @ViewById
    protected TextView tvEmployeeName_FAD;
    @ViewById
    protected TextView tvCountWorking_FAD;
    @ViewById
    protected TextView tvCountVacation_FAD;
    @ViewById
    protected TextView tvCountPersonal_FAD;
    @ViewById
    protected TextView tvCountSick_FAD;
    @ViewById
    protected TextView tvCountEducation_FAD;
    @ViewById
    protected TextView tvCountLeave_FAD;

    protected MenuItem menuCalendar;

    @Bean
    protected AttendanceRepository attendanceRepository;

    @Override
    public AttendanceDetailsContract.AttendanceDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(AttendanceDetailsContract.AttendanceDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new AttendanceDetailsPresenter(this, attendanceRepository, id, fullName);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        calendarManager = new CalendarManager(rvYear_FAD);
        tvYear_FAD.setOnClickListener(v -> {

            DetailsActivity_.intent(this)
                    .bundle(VacationOverviewFragment_.builder().args())
                    .creator(VacationOverviewFragment.getCreator())
                    .titleDetails("Year")
                    .start();
        });
        getPresenter().subscribe();
    }

    @Override
    public void setYears(ArrayList<String> years) {
        if (menuCalendar != null) {
            Menu menu = menuCalendar.getSubMenu();
            menu.clear();
            for (int i = 0; i < years.size(); ++i) {
                menu.add(0, i, i, years.get(i)).setCheckable(true);
            }
            menu.setGroupCheckable(0, true, true);
        }
    }

    @Override
    public void setMonthDetails(int month, ArrayList<String> types) {
        calendarManager.setMonth(month, types);
    }

    @Override
    public void setEmployeeName(String name) {
        tvEmployeeName_FAD.setText(name);
    }

    @Override
    public void selectYear(int position) {
        if (menuCalendar != null) {
            MenuItem item = menuCalendar.getSubMenu().findItem(position);
            item.setChecked(true);
            calendarManager.setYear(Integer.valueOf(item.getTitle().toString()));
            tvYear_FAD.setText(item.getTitle());
        }
    }

    @Override
    public void setCountWorking(String countWorking) {
        tvCountWorking_FAD.setText(countWorking);
    }

    @Override
    public void setCountVacation(String countVacation) {
        tvCountVacation_FAD.setText(countVacation);
    }

    @Override
    public void setCountPersonal(String countPersonal) {
        tvCountPersonal_FAD.setText(countPersonal);
    }

    @Override
    public void setCountSick(String countSick) {
        tvCountSick_FAD.setText(countSick);
    }

    @Override
    public void setCountEducation(String countEducation) {
        tvCountEducation_FAD.setText(countEducation);
    }

    @Override
    public void setCountLeave(String countLeave) {
        tvCountLeave_FAD.setText(countLeave);
    }

    @Override
    public String getScreenName() {
        return "Attendance details screen";
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_hr_attendance_detail;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        menuCalendar = menu.findItem(R.id.menuYear_MHAD);
        getPresenter().buildMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.selectYear(item.getItemId());
        return true;
    }
}
