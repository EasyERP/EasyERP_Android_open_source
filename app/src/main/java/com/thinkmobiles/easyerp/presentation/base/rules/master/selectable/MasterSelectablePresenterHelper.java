package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import android.support.v4.util.Pair;

import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListPresenterHelper;

import java.util.List;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class MasterSelectablePresenterHelper extends MasterListPresenterHelper {

    protected abstract SelectableView getView();

    private Pair<Integer, String> selectedInfo;

    private void setSelectedInfo(final int position, final String id) {
        selectedInfo = Pair.create(position, id);
    }

    public void clearSelectedInfo() {
        selectedInfo = null;
    }

    private Integer getSelectedItemPosition() {
        return selectedInfo != null ? selectedInfo.first : -1;
    }

    private String getSelectedItemId() {
        return selectedInfo != null ? selectedInfo.second : null;
    }

    protected void makeSelectedDHIfNeed(final SelectableDHHelper dhHelper, final int currentPositionInData, boolean isRefresh) {
        if (dhHelper.getId().equals(getSelectedItemId()) && getView().withItemSelecting()) {
            dhHelper.setSelected(true);
            setSelectedInfo((isRefresh ? 0 : getCountItems()) + currentPositionInData, getSelectedItemId());
        }
    }

    protected boolean selectItem(final String dhId, final int position) {
        if (!getView().withItemSelecting() || position != getSelectedItemPosition() || !dhId.equals(getSelectedItemId())) {
            if (getView().withItemSelecting())
                getView().changeSelectedItem(getSelectedItemPosition(), position);
            setSelectedInfo(position, dhId);
            return true;
        } else
            return false;
    }

    protected void selectFirstElementIfNeed(final List<? extends SelectableDHHelper> preparedDHs) {
        if (getSelectedItemId() == null && preparedDHs.size() > 0 && getView().withItemSelecting()) {
            clickItem(0);
            makeSelectedDHIfNeed(preparedDHs.get(0), 0, true);
        }
    }

}
