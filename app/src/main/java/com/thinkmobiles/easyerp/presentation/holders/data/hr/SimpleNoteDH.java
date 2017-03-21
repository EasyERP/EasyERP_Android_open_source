package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.SimpleNoteItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/20/2017.
 */

public class SimpleNoteDH extends RecyclerDH {

    private SimpleNoteItem simpleNoteItem;

    public SimpleNoteDH(SimpleNoteItem simpleNoteItem) {
        this.simpleNoteItem = simpleNoteItem;
    }

    public SimpleNoteItem getSimpleNoteItem() {
        return simpleNoteItem;
    }

    public static ArrayList<SimpleNoteDH> convert(ArrayList<SimpleNoteItem> list) {
        ArrayList<SimpleNoteDH> historyDHs = new ArrayList<>();
        for (SimpleNoteItem note : list) {
            historyDHs.add(new SimpleNoteDH(note));
        }
        if(historyDHs.isEmpty()) historyDHs.add(null);
        return historyDHs;
    }
}
