package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.InvoicesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class InvoicesFragment  extends SimpleListWithRefreshFragment implements InvoicesContract.InvoicesView {

    private InvoicesContract.InvoicesPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Bean
    protected InvoiceRepository invoiceRepository;
    @Bean
    protected InvoicesAdapter invoicesAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

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
        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadInvoices(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(invoicesAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        invoicesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.selectItem(invoicesAdapter.getItem(position), position));

        loadWithProgressBar();
    }

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
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
        errorViewHelper.hideError();
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        if (needClear)
            invoicesAdapter.setListDH(invoiceHs);
        else invoicesAdapter.addListDH(invoiceHs);

        if (getCountItemsNow() == 0)
            displayError(null, ErrorViewHelper.ErrorType.LIST_EMPTY);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(mActivity, resultMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openInvoiceDetailsScreen(String orderID) {
        if (orderID != null) {
            //TODO open invoice detail
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}
