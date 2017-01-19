package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.details.LeadNote;

import java.util.ArrayList;


public class LeadHistoryDH extends RecyclerDH {

    public static ArrayList<LeadHistoryDH> convert(ArrayList<LeadNote> list) {
        ArrayList<LeadHistoryDH> historyDHs = new ArrayList<>();
        for (LeadNote note : list) {
            historyDHs.add(new LeadHistoryDH(note));
        }
        return historyDHs;
    }

    private LeadNote model;

    public LeadHistoryDH(LeadNote model) {
        this.model = model;
    }

    public LeadNote getModel() {
        return model;
    }

    public void setModel(LeadNote model) {
        this.model = model;
    }
}
