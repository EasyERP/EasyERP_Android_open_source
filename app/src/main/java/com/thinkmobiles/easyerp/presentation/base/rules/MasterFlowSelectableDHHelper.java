package com.thinkmobiles.easyerp.presentation.base.rules;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public abstract class MasterFlowSelectableDHHelper<T> extends RecyclerDH {

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract T getId();

}
