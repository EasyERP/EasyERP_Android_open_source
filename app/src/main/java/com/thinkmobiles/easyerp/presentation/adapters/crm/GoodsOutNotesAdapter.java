package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.inventory.GoodsOutNoteDH;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.GoodsOutNoteVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 3/7/2017.
 */

@EBean
public class GoodsOutNotesAdapter extends SelectableAdapter<GoodsOutNoteDH, GoodsOutNoteVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_goods_out_note;
    }
}
