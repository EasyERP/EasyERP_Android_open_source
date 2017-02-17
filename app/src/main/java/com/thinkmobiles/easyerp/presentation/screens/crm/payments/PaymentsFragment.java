package com.thinkmobiles.easyerp.presentation.screens.crm.payments;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.payments.Payment;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.PaymentsAdapter;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SuggestionAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsFragment_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */

@EFragment
public class PaymentsFragment extends MasterFlowListSelectableFragment implements PaymentsContract.PaymentsView {

    private PaymentsContract.PaymentsPresenter presenter;

    @Bean
    protected PaymentsRepository paymentsRepository;
    @Bean
    protected PaymentsAdapter paymentsAdapter;

    private SuggestionAdapter suggestionAdapter;
    private MenuItem menuFilter;

    @AfterInject
    @Override
    public void initPresenter() {
        new PaymentsPresenter(this, paymentsRepository);

        suggestionAdapter = new SuggestionAdapter(getActivity());
    }

    @Override
    public void setPresenter(PaymentsContract.PaymentsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        listRecycler.setAdapter(paymentsAdapter);
        paymentsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(paymentsAdapter.getItem(position), position));

        actSearch.setOnItemClickListener((adapterView, view, i, l) ->
                presenter.filterBySearchItem(searchAdapter.getItem(i))
        );

        actSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN) {

                String name = actSearch.getText().toString();
                if (!name.trim().isEmpty())
                    presenter.filterBySearchText(name);

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
    protected void onRetry() {
        presenter.subscribe();
    }

    @Override
    protected void onRefreshData() {
        super.onRefreshData();
        presenter.refresh();
    }

    @Override
    protected void onLoadNextPage() {
        presenter.loadNextPage();
    }

    @Override
    public int getCountItemsNow() {
        return paymentsAdapter.getItemCount();
    }

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        paymentsAdapter.replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public void displayPayments(ArrayList<PaymentDH> paymentDHs, boolean needClear) {
        if (needClear) {
            paymentsAdapter.setListDH(paymentDHs);
        } else {
            paymentsAdapter.addListDH(paymentDHs);
        }
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
    public void openPaymentDetailsScreen(Payment payment) {
        if (payment != null) {
            mActivity.replaceFragmentContentDetail(PaymentDetailsFragment_.builder()
                    .payment(payment)
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
        return R.menu.menu_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        actSearch.dismissDropDown();
        menuFilter = menu.findItem(R.id.menuFilter_MB);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        searchView.setSuggestionsAdapter(suggestionAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener()
        {
            @Override
            public boolean onSuggestionClick(int position)
            {
                FilterDH item = suggestionAdapter.getSuggestion(position);
                presenter.filterBySearchItem(item);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position)
            {
                return false;
            }
        });
        presenter.buildOptionMenu();
    }

    @Override
    public void createMenuFilters(FilterHelper helper) {
        if (helper.isInitialized()) {
            menuFilter.setVisible(true);
            suggestionAdapter.setItems(helper.getSearchableFilterList());
            helper.buildMenu(menuFilter.getSubMenu());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFilter_MB:
            case R.id.menuSearch:
                return false;
            case R.id.menuFilterRemoveAll:
                presenter.removeAll();
                break;
            default:
                presenter.changeFilter(item.getItemId(), item.getTitle().toString());
                break;
        }
        return true;
    }

    @Override
    public void setListToSearch(ArrayList<FilterDH> companies) {
        searchAdapter.setItems(companies);
    }

    @Override
    public void setTextToSearch(String text) {
        actSearch.setText(text);
        actSearch.setSelection(text.length());
        hideKeyboard();
    }

    @Override
    public void selectFilter(int id, boolean isSelected) {
        menuFilter.getSubMenu().getItem(id).setChecked(isSelected);
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
