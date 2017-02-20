package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.InvoicesAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SearchAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details.InvoiceDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class InvoicesFragment extends MasterFlowListSelectableFragment implements InvoicesContract.InvoicesView {

    private InvoicesContract.InvoicesPresenter presenter;

    @Bean
    protected InvoiceRepository invoiceRepository;
    @Bean
    protected InvoicesAdapter invoicesAdapter;

    protected MenuItem menuFilter;

    @AfterInject
    @Override
    public void initPresenter() {
        new InvoicesPresenter(this, invoiceRepository);
    }

    @Override
    public void setPresenter(InvoicesContract.InvoicesPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(invoicesAdapter);
        invoicesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(invoicesAdapter.getItem(position), position));

        actSearch.setOnItemClickListener((adapterView, view, i, l) ->
                presenter.filterByContactName(searchAdapter.getItem(i))
        );

        actSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN) {

                String name = actSearch.getText().toString();
                if (!name.trim().isEmpty())
                    presenter.filterBySearchContactName(name);

                hideKeyboard();
                actSearch.dismissDropDown();
                listRecycler.requestFocus();
                return true;
            }
            return false;
        });

        presenter.subscribe();
    }

    @Override
    public void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    public int getCountItemsNow() {
        return invoicesAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        invoicesAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void displayInvoices(ArrayList<InvoiceDH> invoiceHs, boolean needClear) {
        if (needClear)
            invoicesAdapter.setListDH(invoiceHs);
        else invoicesAdapter.addListDH(invoiceHs);
    }

    @Override
    public void displayErrorState(String msg, ErrorViewHelper.ErrorType errorType) {
        showErrorState(msg, errorType);
    }

    @Override
    public void displayErrorToast(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void openInvoiceDetailsScreen(String invoiceID) {
        if (invoiceID != null) {
            mActivity.replaceFragmentContentDetail(InvoiceDetailsFragment_.builder()
                    .invoiceId(invoiceID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void showProgress(Constants.ProgressType type) {
        showProgressBar(type);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void clearSelectedItem() {
        presenter.clearSelectedInfo();
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_invoice_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        actSearch.dismissDropDown();
        this.menuFilter = menu.findItem(R.id.menuFilter_MB);
        presenter.refreshOptionMenu();
    }

    @OptionsItem({R.id.menuFilterCustomer, R.id.menuFilterAssignedTo, R.id.menuFilterProject, R.id.menuFilterStage, R.id.menuFilterRemoveAll})
    void clickMenu(MenuItem item) {
        String filterName = item.getTitle().toString();
        switch (item.getItemId()) {
            case R.id.menuFilterCustomer:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_CUSTOMER, filterName);
                break;
            case R.id.menuFilterAssignedTo:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_ASSIGNED_TO, filterName);
                break;
            case R.id.menuFilterProject:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_PROJECT, filterName);
                break;
            case R.id.menuFilterStage:
                presenter.changeFilter(Constants.REQUEST_CODE_FILTER_WORKFLOW, filterName);
                break;
            case R.id.menuFilterRemoveAll:
                presenter.removeAll();
                break;
        }
    }
    @Override
    public void setCustomers(ArrayList<FilterDH> customers) {
        searchAdapter.setItems(customers);
    }

    @Override
    public void setTextToSearch(String text) {
        actSearch.setText(text);
        actSearch.setSelection(text.length());
        hideKeyboard();
    }

    @Override
    public void showFilters(boolean isShow) {
        actSearch.setVisibility(View.VISIBLE);
        menuFilter.setVisible(true);
    }

    @Override
    public void selectFilter(int pos, boolean isSelected) {
        menuFilter.getSubMenu().getItem(pos).setChecked(isSelected);
    }

    @Override
    public void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName) {
        showDialogFiltering(filterDHs, requestCode, filterName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<FilterDH> filterDHs = data.getParcelableArrayListExtra(Constants.KEY_FILTER_LIST);
            presenter.filterByList(filterDHs, requestCode);
        } else {
            presenter.removeFilter(requestCode);
        }
    }
}
