package com.thinkmobiles.easyerp.presentation.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.reports.ReportTypesAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.util.ArrayList;

@EFragment
public class ReportTypeDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @Bean
    protected ReportTypesAdapter reportTypesAdapter;

    @FragmentArg
    protected ArrayList<FilterDH> reportTypesList;

    @AfterInject
    public void initDialog() {
        reportTypesAdapter.setListDH(reportTypesList);
        reportTypesAdapter.setOnCardClickListener((view, position, viewType) -> {
            switch (view.getId()) {
                case R.id.cbItem:
                    int pos = reportTypesList.indexOf(reportTypesAdapter.getItem(position));
                    reportTypesList.get(pos).selected = ((CheckBox) view).isChecked();
                    break;
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_report_type, null);

        RecyclerView rvList = (RecyclerView) parent.findViewById(R.id.rvList_DRT);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(reportTypesAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_FilterDialogStyle)
                .setTitle(R.string.choose_report_type)
                .setView(parent)
                .setPositiveButton(R.string.dialog_btn_apply, this)
                .setNegativeButton(R.string.dialog_btn_cancel, this)
                .setNeutralButton(R.string.dialog_btn_remove_all, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Fragment target = getTargetFragment();
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if (target != null) {
                    if (target instanceof BaseView) {
                        BaseView baseViewTarget = (BaseView) target;
                        GoogleAnalyticHelper.trackClick(baseViewTarget, GoogleAnalyticHelper.EventType.SET_FILTER, getString(R.string.report_type));
                    } else {
                        Log.d("myLogs", "Filters parent view must implement BaseView interface");
                    }

                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra(Constants.KEY_REPORT_TYPES_LIST, reportTypesList);
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                if (target != null) {
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                }
                break;
        }
    }
}
