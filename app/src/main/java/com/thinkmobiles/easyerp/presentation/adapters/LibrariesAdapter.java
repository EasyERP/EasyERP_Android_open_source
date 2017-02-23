package com.thinkmobiles.easyerp.presentation.adapters;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LibraryDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.LibraryVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 2/23/2017.
 */


@EBean
public class LibrariesAdapter extends SimpleRecyclerAdapter<LibraryDH, LibraryVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_library;
    }
}
