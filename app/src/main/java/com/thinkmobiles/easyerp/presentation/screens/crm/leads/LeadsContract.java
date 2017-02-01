package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.ResponseGetLeadsFilters;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface LeadsContract {
    interface LeadsView extends MasterFlowSelectableBaseView<LeadsPresenter> {
        void displayLeads(ArrayList<LeadDH> leadDHs, boolean needClear);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
        void openLeadDetailsScreen(String leadId);

        void setContactNames(ArrayList<FilterDH> contactNames);
        void setTextToSearch(String text);
        void showFilters(boolean isShow);
        void selectContactNameInFilters(boolean isSelected);
        void selectWorkflowInFilters(boolean isSelected);
        void selectAssignedToInFilters(boolean isSelected);
        void selectCreatedByInFilters(boolean isSelected);
        void selectSourceInFilters(boolean isSelected);

        void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName);

        void showProgress(boolean isShow);
        void showEmptyState();

    }
    interface LeadsPresenter extends MasterFlowSelectableBasePresenter<String, LeadDH> {
        void refresh();
        void loadNextPage(int page);

        void filterByContactName(FilterDH filterDH);
        void filterBySearchContactName(String name);
        void filterByList(ArrayList<FilterDH> filterDHs, int requestCode);
        void removeFilter(int requestCode);

        void changeFilter(int requestCode, String filterName);
        void removeAll();
    }
    interface LeadsModel extends BaseModel {
        Observable<ResponseGetLeads> getFilteredLeads(FilterQuery query, int page);
        Observable<ResponseGetLeadsFilters> getLeadFilters();
    }
}
