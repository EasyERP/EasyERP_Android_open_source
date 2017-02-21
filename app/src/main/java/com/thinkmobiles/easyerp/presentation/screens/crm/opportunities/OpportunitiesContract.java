package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunitiesContract {

    interface OpportunitiesView extends MasterFlowSelectableBaseView<OpportunitiesPresenter> {
        void displayOpportunities(ArrayList<OpportunityDH> opportunityDHs, boolean needClear);
        void openOpportunityDetailsScreen(String opportunityID);

        void displayErrorState(final ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);

        void createMenuFilters(FilterHelper helper);
        void selectFilter(int id, boolean isSelected);

        void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName);
    }

    interface OpportunitiesPresenter extends MasterFlowSelectableBasePresenter<String, OpportunityDH> {
        void refresh();
        void loadNextPage();

        void filterBySearchItem(FilterDH filterDH);
        void filterBySearchText(String name);
        void filterByList(ArrayList<FilterDH> filterDHs, int requestCode);
        void removeFilter(int requestCode);

        void changeFilter(int position, String filterName);
        void buildOptionMenu();
        void removeAll();
    }

    interface OpportunitiesModel extends BaseModel {
        Observable<ResponseGetOpportunities> getOpportunities(FilterHelper query, int page);
        Observable<ResponseFilters> getOpportunityFilters();
    }
}
