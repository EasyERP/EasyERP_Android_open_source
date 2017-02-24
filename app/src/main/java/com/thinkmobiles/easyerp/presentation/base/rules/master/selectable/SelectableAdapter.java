package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class SelectableAdapter<DH extends SelectableDHHelper, VH extends SelectableVHHelper<DH>> extends SimpleRecyclerAdapter<DH, VH> {

    public void replaceSelectedItem(int selectedPrevItemPosition, int selectedNewItemPosition) {
        chooseItem(selectedPrevItemPosition, false);
        chooseItem(selectedNewItemPosition, true);
    }

    private void chooseItem(final int position, final boolean selected) {
        SelectableDHHelper dh = getItem(position);
        if (dh != null) {
            dh.setSelected(selected);
            updateItem(position);
        }
    }
}
