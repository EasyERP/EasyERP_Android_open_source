package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 1/16/2017.
 */

public class LeadsPresenter extends MasterFilterablePresenterHelper implements LeadsContract.LeadsPresenter {

    private LeadsContract.LeadsView view;
    private LeadsContract.LeadsModel model;

    private ArrayList<LeadItem> leadItems = new ArrayList<>();

    public LeadsPresenter(LeadsContract.LeadsView view, LeadsContract.LeadsModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getFilteredLeads(helper, page)
                        .subscribe(
                                responseGetLeads -> {
                                    currentPage = page;
                                    totalItems = responseGetLeads.total;
                                    saveData(responseGetLeads.data, needClear);
                                    setData(responseGetLeads.data, needClear);
                                },  t -> error(t)
                        ));
    }

    @Override
    protected void retainInstance() {
        setData(leadItems, true);
    }

    @Override
    protected boolean hasContent() {
        return leadItems.size() != 0;
    }

    @Override
    protected int getCountItems() {
        return leadItems.size();
    }

    private void saveData(List<LeadItem> leadItems, boolean needClear) {
        if(needClear)
            this.leadItems.clear();
        this.leadItems.addAll(leadItems);
    }

    private void setData(List<LeadItem> leadItems, boolean needClear) {
        view.setDataList(prepareLeadDHs(leadItems), needClear);
        if(this.leadItems.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    @Override
    public void clickItem(int position) {
        String id = leadItems.get(position).id;
        if(super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    private ArrayList<LeadDH> prepareLeadDHs(List<LeadItem> leadItems) {
        final ArrayList<LeadDH> result = new ArrayList<>();
        for (LeadItem leadItem : leadItems) {
            final LeadDH leadDH = new LeadDH(leadItem);
            makeSelectedDHIfNeed(leadDH, this.leadItems.indexOf(leadItem));
            result.add(leadDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }
}
