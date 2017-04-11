package com.thinkmobiles.easyerp.presentation.screens.hr.vacations.overview;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.CalendarManager;
import com.thinkmobiles.easyerp.presentation.custom.views.calendar.MonthDH;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.VacationRangeDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Alex Michenko on 29.03.2017.
 */

public class VacationOverviewPresenter extends ContentPresenterHelper implements VacationOverviewContract.VacationOverviewPresenter {

    private VacationOverviewContract.VacationOverviewView view;
    private MonthDetail monthDetail;

    public VacationOverviewPresenter(VacationOverviewContract.VacationOverviewView view, MonthDetail monthDetail) {
        this.view = view;
        this.monthDetail = monthDetail;
        view.setPresenter(this);
    }

    @Override
    public void refresh() {
        view.showProgress(Constants.ProgressType.NONE);
        setData(monthDetail);
    }

    @Override
    protected ContentView getView() {
        return view;
    }

    @Override
    protected boolean hasContent() {
        return monthDetail != null;
    }

    @Override
    protected void retainInstance() {
        setData(monthDetail);
    }

    private void setData(MonthDetail detail) {
        view.setTitle(StringUtil.getFullName(detail.employee.name.first, detail.employee.name.last));
        view.setPosition(String.format("(%s)", detail.department.name));
        initCalendar(detail);
    }

    private void initCalendar(MonthDetail detail) {

        MonthDH monthDH = new MonthDH(detail.year, detail.month - 1);
        monthDH.setDayDHs(CalendarManager.fillMonth(monthDH, detail.vacArray));
        view.initMonth(String.format(Locale.ENGLISH, "%s, %d", monthDH.getMonthName(), detail.year), monthDH.getDayDHs());

        ArrayList<VacationRangeDH> rangeDHs = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(detail.year, detail.month - 1, 1);
        int working = DateManager.getWorkingDaysInMonth(detail.year, detail.month - 1);
        int v = 0, p = 0, s = 0, e = 0, l = 0;
        int color = Color.TRANSPARENT;
        boolean isVac = false;
        for (int i = 0; i < detail.vacArray.size(); i++) {
            String d = detail.vacArray.get(i);
            if (d != null) {
                switch (d) {
                    case "V":
                        ++v;
                        color = ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_vacation);
                        break;
                    case "P":
                        ++p;
                        color = ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_personal);
                        break;
                    case "S":
                        ++s;
                        color = ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_sick);
                        break;
                    case "E":
                        ++e;
                        color = ContextCompat.getColor(EasyErpApplication.getInstance(), R.color.color_education);
                        break;
                }
                l = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? l : l + 1;
                if (!isVac) {
                    isVac = true;
                    rangeDHs.add(new VacationRangeDH(d, DateManager.convert(calendar).setDstPattern(DateManager.PATTERN_DATE_MONTH_PREVIEW).toString(), color));
                } else if (i == detail.vacArray.size() - 1) {
                    rangeDHs.get(rangeDHs.size() - 1).setRange(String.format("%s - %s",
                            rangeDHs.get(rangeDHs.size() - 1).getRange(),
                            DateManager.convert(calendar).setDstPattern(DateManager.PATTERN_DATE_MONTH_PREVIEW).toString()
                    ));
                }

            } else if (isVac) {
                isVac = false;
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                rangeDHs.get(rangeDHs.size() - 1).setRange(String.format("%s - %s",
                        rangeDHs.get(rangeDHs.size() - 1).getRange(),
                        DateManager.convert(calendar).setDstPattern(DateManager.PATTERN_DATE_MONTH_PREVIEW).toString()
                ));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        view.setRanges(rangeDHs);
        working -= l;
        view.setCountWorking(String.valueOf(working));
        view.setCountVacation(String.valueOf(v));
        view.setCountPersonal(String.valueOf(p));
        view.setCountSick(String.valueOf(s));
        view.setCountEducation(String.valueOf(e));
        view.setCountLeave(String.valueOf(l));

    }
}
