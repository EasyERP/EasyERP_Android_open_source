package com.thinkmobiles.easyerp.presentation.custom.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EViewGroup(R.layout.dialog_month_year_picker)
public class MonthYearView extends FrameLayout {

    private List<String> months = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();

    private String currentMonth;
    private Integer currentYear, minYear, maxYear;

    private DataAdapter<String> monthsAdapter;
    private DataAdapter<Integer> yearsAdapter;

    @ViewById
    protected ListView lvMonths_DMYP;
    @ViewById
    protected ListView lvYears_DMYP;

    public MonthYearView(@NonNull Context context, final int minYear, final int maxYear) {
        super(context);
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    public void setCurrentMonth(int month) {
        currentMonth = months.get(month);
        monthsAdapter.setSelectedItem(currentMonth);
        lvMonths_DMYP.setSelection(Math.max(months.indexOf(currentMonth) - 2, 0));
    }

    public void setCurrentYear(int year) {
        currentYear = year;
        yearsAdapter.setSelectedItem(currentYear);
        lvYears_DMYP.setSelection(Math.max(years.indexOf(currentYear) - 2, 0));
    }

    public int getChosenMonth() {
        return months.indexOf(currentMonth);
    }

    public int getChosenYear() {
        return currentYear;
    }

    public int getTodayYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int getTodayMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    @AfterInject
    protected void initData() {
        for (int year = minYear; year <= maxYear; year++)
            years.add(year);
        months = Arrays.asList(new DateFormatSymbols().getMonths());
    }

    @AfterViews
    protected void initUI() {
        monthsAdapter = new DataAdapter<>(months);
        yearsAdapter = new DataAdapter<>(years);

        lvMonths_DMYP.setAdapter(monthsAdapter);
        lvYears_DMYP.setAdapter(yearsAdapter);
    }

    @ItemClick(R.id.lvMonths_DMYP)
    protected void monthSelect(int position) {
        currentMonth = months.get(position);
        monthsAdapter.setSelectedItem(currentMonth);
    }

    @ItemClick(R.id.lvYears_DMYP)
    protected void yearSelect(int position) {
        currentYear = years.get(position);
        yearsAdapter.setSelectedItem(currentYear);
    }

    private final class DataAdapter<T> extends BaseAdapter {

        private final List<T> data;
        private T selectedItem;

        public DataAdapter(List<T> data) {
            this.data = data;
        }

        public void setSelectedItem(T selectedItem) {
            this.selectedItem = selectedItem;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public T getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            TextView tvDataLabel_VLIYMP;

            if (convertView == null) {
                convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.view_list_item_year_month_picker, container, false);
                tvDataLabel_VLIYMP = (TextView) convertView.findViewById(R.id.tvDataLabel_VLIYMP);
                convertView.setTag(tvDataLabel_VLIYMP);
            } else tvDataLabel_VLIYMP = (TextView) convertView.getTag();

            tvDataLabel_VLIYMP.setText(getItem(position).toString());
            Log.d("TAG", String.format("%s| %s | %s",
                    getItem(position).toString(),
                    getItem(position).equals(selectedItem),
                    selectedItem == null ? null : selectedItem.toString()));
            tvDataLabel_VLIYMP.setSelected(getItem(position).equals(selectedItem));
            return convertView;
        }
    }
}
