package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;

import java.util.ArrayList;


public class LeadHistoryDH extends RecyclerDH {

    public static ArrayList<LeadHistoryDH> convert(ArrayList<NoteItem> list) {
        ArrayList<LeadHistoryDH> historyDHs = new ArrayList<>();
        for (NoteItem note : list) {
            historyDHs.add(new LeadHistoryDH(note));
        }
        return historyDHs;
    }

    private NoteItem model;

    public LeadHistoryDH(NoteItem model) {
        this.model = model;
    }

    public NoteItem getModel() {
        return model;
    }

    public void setModel(NoteItem model) {
        this.model = model;
    }
}
