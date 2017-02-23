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
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchDialogAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.SystemService;

import java.util.ArrayList;

@EFragment
public class FilterDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText etSearch;

    @Bean
    protected SearchDialogAdapter searchAdapter;

    @FragmentArg
    protected ArrayList<FilterDH> filterList;
    @FragmentArg
    protected String filterName;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @AfterInject
    public void initDialog() {
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
        ((Toolbar) parent.findViewById(R.id.toolbar)).setTitle(String.format("%s : %s", getString(R.string.menu_filter), filterName));
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

        RecyclerView rvList = (RecyclerView) parent.findViewById(R.id.rvList_DF);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(searchAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_FilterDialogStyle)
                .setView(parent)
                .setPositiveButton(R.string.dialog_btn_apply, this)
                .setNegativeButton(R.string.dialog_btn_cancel, this)
                .setNeutralButton(R.string.dialog_btn_remove, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Fragment target = getTargetFragment();
        inputMethodManager.hideSoftInputFromWindow(etSearch.getWindowToken(), InputMethodManager.RESULT_HIDDEN);
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if (target != null) {
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
    }

}
