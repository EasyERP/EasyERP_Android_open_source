package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.michenko.simpleadapter.TypedRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.EmptyNoteVH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.SimpleNoteVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/20/2017.
 */

@EBean
public class SimpleNotesAdapter extends TypedRecyclerAdapter<SimpleNoteDH> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_PLACEHOLDER = 1;
    public static final int VIEW_TYPE_LAST = 3;

    @Override
    protected void initViewTypes() {
        addType(VIEW_TYPE_ITEM, R.layout.view_list_item_simple_note, SimpleNoteVH.class);
        addType(VIEW_TYPE_LAST, R.layout.view_list_item_simple_note, SimpleNoteVH.class);
        addType(VIEW_TYPE_PLACEHOLDER, R.layout.view_list_item_empty_notes, EmptyNoteVH.class);
    }

    @Override
    protected int getViewType(int position) {
        if(getItem(position) == null) return VIEW_TYPE_PLACEHOLDER;
        else return position == getItemCount() - 1 ? VIEW_TYPE_ITEM : VIEW_TYPE_LAST;
    }
}
