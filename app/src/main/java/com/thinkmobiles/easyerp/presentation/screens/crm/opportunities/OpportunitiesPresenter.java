package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunitiesPresenter extends MasterFlowSelectablePresenterHelper<String, OpportunityDH> implements OpportunitiesContract.OpportunitiesPresenter {

    private OpportunitiesContract.OpportunitiesView view;
    private OpportunitiesContract.OpportunitiesModel model;
    private CompositeSubscription compositeSubscription;

    public OpportunitiesPresenter(OpportunitiesContract.OpportunitiesView view, OpportunitiesContract.OpportunitiesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void loadOpportunities(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOpportunities(page)
                        .subscribe(
                                responseGetLeads -> view.displayOpportunities(prepareLeadDHs(responseGetLeads, needClear), needClear),
                                t -> view.displayError(t.getMessage(), ErrorViewHelper.ErrorType.NETWORK)));
    }

    @Override
    public void displayOpportunityDetails(String opportunityID) {
        view.openOpportunityDetailsScreen(opportunityID);
    }

    @Override
    public void subscribe() {
        loadOpportunities(1);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions()) compositeSubscription.clear();
    }

    private ArrayList<OpportunityDH> prepareLeadDHs(ResponseGetOpportunities responseGetOpportunities, boolean isFirstPage) {
        int position = 0;
        final ArrayList<OpportunityDH> result = new ArrayList<>();
        for (OpportunityListItem opportunityItem : responseGetOpportunities.data) {
            final OpportunityDH opportunityDH = new OpportunityDH(opportunityItem);
            makeSelectedDHIfNeed(opportunityDH, view, position++, isFirstPage);
            result.add(opportunityDH);
        }
        return result;
    }

    @Override
    public void selectItem(OpportunityDH dh, int position) {
        if (position != getSelectedItemPosition()) {
            view.changeSelectedItem(getSelectedItemPosition(), position);
            setSelectedInfo(position, dh.getId());
            view.openOpportunityDetailsScreen(dh.getId());
        }
    }
}
