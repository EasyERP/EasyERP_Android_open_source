package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunitiesPresenter extends MasterFlowSelectablePresenterHelper<String, OpportunityDH> implements OpportunitiesContract.OpportunitiesPresenter {

    private OpportunitiesContract.OpportunitiesView view;
    private OpportunitiesContract.OpportunitiesModel model;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;
    private int totalItems;
    private ArrayList<OpportunityListItem> opportunityItems = new ArrayList<>();

    public OpportunitiesPresenter(OpportunitiesContract.OpportunitiesView view, OpportunitiesContract.OpportunitiesModel model) {
        this.view = view;
        this.model = model;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    private void loadOpportunities(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOpportunities(page)
                        .subscribe(responseGetOpportunities -> {
                                    currentPage = page;
                                    totalItems = responseGetOpportunities.total;
                                    saveData(responseGetOpportunities.data, needClear);
                                    if (opportunityItems.isEmpty()) {
                                        view.displayErrorState(ErrorManager.getErrorType(null));
                                    } else {
                                        view.showProgress(Constants.ProgressType.NONE);
                                        view.displayOpportunities(prepareOpportunitiesDHs(responseGetOpportunities.data, needClear), needClear);
                                    }
                                },
                                t -> {
                                    if (opportunityItems.isEmpty()) {
                                        view.displayErrorState(ErrorManager.getErrorType(t));
                                    } else {
                                        view.displayErrorToast(t.getMessage());
                                    }
                                }));
    }


    private void saveData(final List<OpportunityListItem> list, boolean needClear) {
        if (needClear)
            this.opportunityItems.clear();
        this.opportunityItems.addAll(list);
    }

    @Override
    public void subscribe() {
        if (opportunityItems.size() == 0) {
            view.showProgress(Constants.ProgressType.CENTER);
            refresh();
        } else view.displayOpportunities(prepareOpportunitiesDHs(opportunityItems, true), true);
    }

    @Override
    public void refresh() {
        loadOpportunities(1);
    }

    @Override
    public void loadNextPage() {
        if(view.getCountItemsNow() == totalItems) {
            return;
        }
        view.showProgress(Constants.ProgressType.BOTTOM);
        loadOpportunities(currentPage + 1);
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<OpportunityDH> prepareOpportunitiesDHs(ArrayList<OpportunityListItem> list, boolean needClear) {
        int position = 0;
        final ArrayList<OpportunityDH> result = new ArrayList<>();
        for (OpportunityListItem opportunityItem : list) {
            final OpportunityDH opportunityDH = new OpportunityDH(opportunityItem);
            makeSelectedDHIfNeed(opportunityDH, view, position++, needClear);
            result.add(opportunityDH);
        }
        selectFirstElementIfNeed(result, view);
        return result;
    }

    @Override
    public void selectItem(OpportunityDH dh, int position) {
        if (super.selectItem(dh, position, view))
            view.openOpportunityDetailsScreen(dh.getId());
    }
}
