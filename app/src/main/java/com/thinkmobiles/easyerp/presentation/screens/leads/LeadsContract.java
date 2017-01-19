package com.thinkmobiles.easyerp.presentation.screens.leads;

import com.thinkmobiles.easyerp.data.model.leads.ResponseGetLeads;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.LeadDH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 1/16/2017.
 */

public interface LeadsContract {
    interface LeadsView extends BaseView<LeadsPresenter> {
        void displayLeads(ArrayList<LeadDH> leadDHs);
        void openLeadDetailsScreen(String leadId);
    }
    interface LeadsPresenter extends BasePresenter {
        void loadLeads(int page);
        void displayLeadDetails(String leadID);
    }
    interface LeadsModel extends BaseModel {
        Observable<ResponseGetLeads> getLeads(int page);
    }
}
