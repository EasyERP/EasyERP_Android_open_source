package com.thinkmobiles.easyerp.presentation.base.rules.master.list;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentView;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface MasterListView extends ContentView {
    void setDataList(ArrayList<? extends RecyclerDH> list, boolean needClear);
}
