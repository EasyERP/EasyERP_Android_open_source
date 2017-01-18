package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.v4.util.Pair;

/**
 * Created by Asus_Dev on 1/18/2017.
 */
public abstract class MasterFlowSelectablePresenterHelper<ID_TYPE> implements MasterFlowSelectableBasePresenter<ID_TYPE> {

    protected Pair<Integer, ID_TYPE> selectedInfo;

    @Override
    public void setSelectedInfo(final int position, final ID_TYPE id) {
        selectedInfo = Pair.create(position, id);
    }

    @Override
    public Integer getSelectedItemPosition() {
        return selectedInfo != null ? selectedInfo.first : -1;
    }

    @Override
    public ID_TYPE getSelectedItemId() {
        return selectedInfo != null ? selectedInfo.second : null;
    }

    @Override
    public void makeSelectedDHIfNeed(final MasterFlowSelectableDHHelper<ID_TYPE> dhHelper, final MasterFlowSelectableBaseView baseViewWithCountItemsLeft, final int currentPositionInData) {
        if (getSelectedItemPosition() == -1 && dhHelper.getId().equals(getSelectedItemId())) {
            dhHelper.setSelected(true);
            setSelectedInfo(baseViewWithCountItemsLeft.getCountItemsNow() + currentPositionInData, getSelectedItemId());
        }
    }

}
