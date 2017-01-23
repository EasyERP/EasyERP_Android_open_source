package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 1/23/2017.
 */

@EBean
public class AlphabetListAdapter extends SimpleRecyclerAdapter<LetterDH, LetterVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.list_item_letter;
    }
}
