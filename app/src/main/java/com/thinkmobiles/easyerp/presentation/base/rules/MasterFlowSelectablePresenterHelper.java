package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.v4.util.Pair;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */
public abstract class MasterFlowSelectablePresenterHelper<ID_TYPE, DH_TYPE extends MasterFlowSelectableDHHelper<ID_TYPE>> implements MasterFlowSelectableBasePresenter<ID_TYPE, DH_TYPE> {

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
    public void makeSelectedDHIfNeed(final MasterFlowSelectableDHHelper<ID_TYPE> dhHelper, final MasterFlowSelectableBaseView baseViewWithCountItemsLeft, final int currentPositionInData, boolean isRefresh) {
        if (dhHelper.getId().equals(getSelectedItemId())) {
            dhHelper.setSelected(true);
            setSelectedInfo((isRefresh ? 0 : baseViewWithCountItemsLeft.getCountItemsNow()) + currentPositionInData, getSelectedItemId());
        }
    }

    @Override
    public boolean selectItem(DH_TYPE dh, int position) {
        if (position != getSelectedItemPosition() || !dh.getId().equals(getSelectedItemId())) {
            setSelectedInfo(position, dh.getId());
            return true;
        } else return false;
    }
}
