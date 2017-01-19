package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.crm.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.LeadsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/16/2017.
 */
@EFragment(R.layout.fragment_simple_list_with_swipe_refresh)
public class LeadsFragment extends SimpleListWithRefreshFragment implements LeadsContract.LeadsView {

    private LeadsContract.LeadsPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Bean
    protected LeadsRepository leadsRepository;
    @Bean
    protected LeadsAdapter leadsAdapter;
    @Bean
    protected ErrorViewHelper errorViewHelper;

    @StringRes(R.string.list_is_empty)
    protected String string_list_is_empty;

    @ViewById(R.id.llErrorLayout)
    protected View errorLayout;

    @AfterInject
    @Override
    public void initPresenter() {
        new LeadsPresenter(this, leadsRepository);
    }

    @Override
    public void setPresenter(LeadsContract.LeadsPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        errorViewHelper.init(errorLayout, view -> loadWithProgressBar());

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                displayProgress(true);
                presenter.loadLeads(page);
            }
        };
        listRecycler.setLayoutManager(llm);
        listRecycler.setAdapter(leadsAdapter);
        listRecycler.addOnScrollListener(scrollListener);
        leadsAdapter.setOnCardClickListener((view, position, viewType) -> {
            if (position != presenter.getSelectedItemPosition()) {
                final LeadDH itemDH = leadsAdapter.getItem(position);
                leadsAdapter.replaceSelectedItem(presenter.getSelectedItemPosition(), position);
                presenter.setSelectedInfo(position, itemDH.getId());
                presenter.displayLeadDetails(itemDH.getLeadItem().id);
            }
        });

        loadWithProgressBar();
    }

    private void loadWithProgressBar() {
        errorViewHelper.hideError();
        displayProgress(true);
        presenter.subscribe();
    }

    @Override
    public void displayLeads(ArrayList<LeadDH> leadDHs) {
        errorViewHelper.hideError();
        displayProgress(false);
        swipeContainer.setRefreshing(false);
        leadsAdapter.addListDH(leadDHs);
    }

    @Override
    public void displayError(String msg, ErrorViewHelper.ErrorType errorType) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);

        final String resultMsg = errorType.equals(ErrorViewHelper.ErrorType.LIST_EMPTY) ? string_list_is_empty : msg;
        if (getCountItemsNow() == 0)
            errorViewHelper.showErrorMsg(resultMsg, errorType);
        else Toast.makeText(getContext(), resultMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void onRefresh() {
        errorViewHelper.hideError();
        scrollListener.resetState();
        presenter.setSelectedInfo(-1, presenter.getSelectedItemId());
        leadsAdapter.clear();
        presenter.subscribe();
    }

    @Override
    public int getCountItemsNow() {
        return leadsAdapter.getItemCount();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

}
