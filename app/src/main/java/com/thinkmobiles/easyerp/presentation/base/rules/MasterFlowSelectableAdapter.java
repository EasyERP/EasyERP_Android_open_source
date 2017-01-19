package com.thinkmobiles.easyerp.presentation.base.rules;

import com.michenko.simpleadapter.RecyclerVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Asus_Dev on 1/18/2017.
 */
public abstract class MasterFlowSelectableAdapter<DH extends MasterFlowSelectableDHHelper, VH extends RecyclerVH> extends SimpleRecyclerAdapter<DH, VH> {

    public void replaceSelectedItem(int selectedPrevItemPosition, int selectedNewItemPosition) {
        chooseItem(selectedPrevItemPosition, false);
        chooseItem(selectedNewItemPosition, true);
    }

    private void chooseItem(final int position, final boolean selected) {
        MasterFlowSelectableDHHelper dh = getItem(position);
        if (dh != null) {
            dh.setSelected(selected);
            updateItem(position);
        }
    }

    public void clear() {
        setListDH(new ArrayList<>());
    }

}
