package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.SimpleNoteDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.SimpleNoteVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/20/2017.
 */

@EBean
public class SimpleNotesAdapter extends SimpleRecyclerAdapter<SimpleNoteDH, SimpleNoteVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_simple_note;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? 1 : 0;
    }
}
