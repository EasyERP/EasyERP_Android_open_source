package com.thinkmobiles.easyerp.presentation.screens.crm.opportunities;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.ResponseGetOpportunities;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.OpportunityDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/30/2017.
 */

public interface OpportunitiesContract {

    interface OpportunitiesView extends MasterFlowSelectableBaseView<OpportunitiesPresenter> {
        void displayOpportunities(ArrayList<OpportunityDH> opportunityDHs, boolean needClear);
        void displayError(final String msg, final ErrorViewHelper.ErrorType errorType);
        void openOpportunityDetailsScreen(String opportunityID);
    }

    interface OpportunitiesPresenter extends MasterFlowSelectableBasePresenter<String, OpportunityDH> {
        void loadOpportunities(int page);
    }

    interface OpportunitiesModel extends BaseModel {
        Observable<ResponseGetOpportunities> getOpportunities(int page);
    }
}
