package com.thinkmobiles.easyerp.presentation.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchDialogAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;


public class FilterDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText etSearch;
    private RecyclerView rvList;

    private SearchDialogAdapter searchAdapter;

    private ArrayList<FilterDH> filterList;
    private String filterName;

    private InputMethodManager inputMethodManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        filterList = getArguments().getParcelableArrayList(Constants.KEY_FILTER_LIST);
        filterName = getArguments().getString(Constants.KEY_FILTER_NAME);

        searchAdapter = new SearchDialogAdapter();
        searchAdapter.setOriginList(filterList);
        searchAdapter.setOnCardClickListener((view, position, viewType) -> {
            switch (view.getId()) {
                case R.id.cbItem:
                    int pos = filterList.indexOf(searchAdapter.getItem(position));
                    filterList.get(pos).selected = ((CheckBox) view).isChecked();
                    break;
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_filter, null);

        etSearch = (EditText) parent.findViewById(R.id.etSearch_DF);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchAdapter.getFilter().filter(s);
            }
        });
        etSearch.setOnClickListener((v) -> etSearch.setText(""));

        rvList = (RecyclerView) parent.findViewById(R.id.rvList_DF);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(searchAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_FilterDialogStyle)
                .setView(parent)
                .setTitle(String.format("%s : %s", getString(R.string.menu_filter), filterName))
                .setPositiveButton(R.string.dialog_btn_apply, this)
                .setNegativeButton(R.string.dialog_btn_cancel, this)
                .setNeutralButton(R.string.dialog_btn_remove, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Fragment target = getTargetFragment();
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if (target != null) {
                    if(target instanceof BaseView) {
                        BaseView baseViewTarget = (BaseView) target;
                        GoogleAnalyticHelper.trackFilters(baseViewTarget, filterName, filterList);
                    } else {
                        Log.d("myLogs", "Filters parent view must implement BaseView interface");
                    }

                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra(Constants.KEY_FILTER_LIST, filterList);
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
        dialog.dismiss();
    }
}
