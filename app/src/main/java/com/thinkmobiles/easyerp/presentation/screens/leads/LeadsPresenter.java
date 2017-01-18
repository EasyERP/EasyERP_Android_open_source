package com.thinkmobiles.easyerp.presentation.screens.leads;

import android.util.Log;

import com.thinkmobiles.easyerp.data.model.leads.LeadItem;
import com.thinkmobiles.easyerp.data.model.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter implements LeadsContract.LeadsPresenter {

    private LeadsContract.LeadsView view;
    private LeadsContract.LeadsModel model;
    private CompositeSubscription compositeSubscription;

    public LeadsPresenter(LeadsContract.LeadsView view, LeadsContract.LeadsModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void loadLeads(int page) {
        compositeSubscription.add(
                model.getLeads(page)
                        .subscribe(responseGetLeads -> {
                            view.displayLeads(prepareLeadDHs(responseGetLeads));
                        }, t -> Log.d("HTTP", "Error: " + t.getMessage()))
        );
    }

    @Override
    public void displayLeadDetails(String leadID) {
        Log.d("myLogs", "Display lead details ID = " + leadID);
    }

    @Override
    public void subscribe() {
        loadLeads(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    private ArrayList<LeadDH> prepareLeadDHs(ResponseGetLeads responseGetLeads) {
        ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : responseGetLeads.data)
            result.add(new LeadDH(leadItem));
        return result;
    }
}
