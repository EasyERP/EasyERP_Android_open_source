package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.hr.employees.details.SimpleNoteItem;

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
}
