package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.LeadsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.LeadsAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

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
        displayProgress(true);
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
        leadsAdapter.setOnCardClickListener((view, position, viewType) -> presenter.displayLeadDetails(leadsAdapter.getItem(position).getLeadItem().id));

        presenter.subscribe();
    }

    @Override
    public void displayLeads(ArrayList<LeadDH> leadDHs) {
        displayProgress(false);
        swipeContainer.setRefreshing(false);
        leadsAdapter.addListDH(leadDHs);
    }

    @Override
    protected boolean needProgress() {
        return true;
    }

    @Override
    public void onRefresh() {
        scrollListener.resetState();
        leadsAdapter.clear();
        presenter.subscribe();
    }

}
