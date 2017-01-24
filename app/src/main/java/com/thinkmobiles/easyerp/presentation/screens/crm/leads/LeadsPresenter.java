package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import android.util.Log;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter extends MasterFlowSelectablePresenterHelper<String> implements LeadsContract.LeadsPresenter {

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
    public void loadLeads(final int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getLeads(page)
                        .subscribe(
                                responseGetLeads -> view.displayLeads(prepareLeadDHs(responseGetLeads, needClear), needClear),
                                t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK)));
    }

    @Override
    public void displayLeadDetails(String leadID) {
        Log.d("myLogs", "Display lead details ID = " + leadID);
        view.openLeadDetailsScreen(leadID);
    }

    @Override
    public void subscribe() {
        loadLeads(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    private ArrayList<LeadDH> prepareLeadDHs(ResponseGetLeads responseGetLeads, boolean isFirstPage) {
        int position = 0;
        final ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : responseGetLeads.data) {
            final LeadDH leadDH = new LeadDH(leadItem);
            makeSelectedDHIfNeed(leadDH, view, position++, isFirstPage);
            result.add(leadDH);
        }
        return result;
    }

}
