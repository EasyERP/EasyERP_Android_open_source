package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;

import java.util.ArrayList;


public final class HistoryDH extends RecyclerDH {

    public static ArrayList<HistoryDH> convert(ArrayList<NoteItem> list) {
        ArrayList<HistoryDH> historyDHs = new ArrayList<>();
        for (NoteItem note : list) {
            historyDHs.add(new HistoryDH(note));
        }
        return historyDHs;
    }

    private final NoteItem model;

    public HistoryDH(NoteItem model) {
        this.model = model;
    }

    public NoteItem getModel() {
        return model;
    }
}
