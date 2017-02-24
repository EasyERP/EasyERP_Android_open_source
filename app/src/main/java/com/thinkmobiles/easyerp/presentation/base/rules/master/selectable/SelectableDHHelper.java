package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class SelectableDHHelper extends RecyclerDH {

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract String getId();
}
