package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 1/30/2017.
 */

public class OpportunitiesPresenter extends MasterFilterablePresenterHelper implements OpportunitiesContract.OpportunitiesPresenter {

    private OpportunitiesContract.OpportunitiesView view;
    private OpportunitiesContract.OpportunitiesModel model;

    private ArrayList<OpportunityListItem> opportunityItems = new ArrayList<>();

    public OpportunitiesPresenter(OpportunitiesContract.OpportunitiesView view, OpportunitiesContract.OpportunitiesModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getOpportunities(helper, page)
                        .subscribe(responseGetOpportunities -> {
                                    currentPage = page;
                                    totalItems = responseGetOpportunities.total;
                                    saveData(responseGetOpportunities.data, needClear);
                                    setData(responseGetOpportunities.data, needClear);
                                },  t -> error(t)));
    }

    @Override
    protected int getCountItems() {
        return opportunityItems.size();
    }

    @Override
    protected boolean hasContent() {
        return !opportunityItems.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(opportunityItems, true);
    }

    @Override
    public void clickItem(int position) {
        String id = opportunityItems.get(position)._id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    private void saveData(final List<OpportunityListItem> list, boolean needClear) {
        if (needClear)
            this.opportunityItems.clear();
        this.opportunityItems.addAll(list);
    }


    private void setData(final List<OpportunityListItem> list, boolean needClear) {
        view.setDataList(prepareOpportunitiesDHs(list), needClear);
        if (opportunityItems.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }


    private ArrayList<OpportunityDH> prepareOpportunitiesDHs(List<OpportunityListItem> list) {
        final ArrayList<OpportunityDH> result = new ArrayList<>();
        for (OpportunityListItem opportunityItem : list) {
            final OpportunityDH opportunityDH = new OpportunityDH(opportunityItem);
            makeSelectedDHIfNeed(opportunityDH, opportunityItems.indexOf(opportunityItem));
            result.add(opportunityDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
