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
    public void clearSelectedInfo() {
        selectedInfo = null;
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
    public void makeSelectedDHIfNeed(final MasterFlowSelectableDHHelper<ID_TYPE> dhHelper, final MasterFlowSelectableBaseView selectableBaseView, final int currentPositionInData, boolean isRefresh) {
        if (dhHelper.getId().equals(getSelectedItemId()) && selectableBaseView.withItemSelecting()) {
            dhHelper.setSelected(true);
            setSelectedInfo((isRefresh ? 0 : selectableBaseView.getCountItemsNow()) + currentPositionInData, getSelectedItemId());
        }
    }

    @Override
    public boolean selectItem(final DH_TYPE dh, final int position, final MasterFlowSelectableBaseView selectableBaseView) {
        if (!selectableBaseView.withItemSelecting() || position != getSelectedItemPosition() || !dh.getId().equals(getSelectedItemId())) {
            if (selectableBaseView.withItemSelecting())
                selectableBaseView.changeSelectedItem(getSelectedItemPosition(), position);
            setSelectedInfo(position, dh.getId());
            return true;
        } else return false;
    }
}
