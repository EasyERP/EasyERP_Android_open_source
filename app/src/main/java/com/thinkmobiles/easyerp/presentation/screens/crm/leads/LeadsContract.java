package com.thinkmobiles.easyerp.presentation.screens.crm.leads;

import com.thinkmobiles.easyerp.data.model.crm.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LeadDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface LeadsContract {
    interface LeadsView extends MasterFlowSelectableBaseView<LeadsPresenter> {
        void displayLeads(ArrayList<LeadDH> leadDHs);
    }
    interface LeadsPresenter extends MasterFlowSelectableBasePresenter<String> {
        void loadLeads(int page);
        void displayLeadDetails(String leadID);
    }
    interface LeadsModel extends BaseModel {
        Observable<ResponseGetLeads> getLeads(int page);
    }
}
