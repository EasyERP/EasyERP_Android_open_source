package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.GoodOutNoteDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.GoodOutNoteVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/7/2017.
 */

@EBean
public class GoodsOutNotesAdapter extends SelectableAdapter<GoodOutNoteDH, GoodOutNoteVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_good_out_note;
    }
}
