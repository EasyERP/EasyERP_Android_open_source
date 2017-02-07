package com.thinkmobiles.easyerp.presentation.base.rules;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface MasterFlowSelectableBasePresenter<ID_TYPE, DH_TYPE extends MasterFlowSelectableDHHelper<ID_TYPE>> extends BasePresenter {

    void setSelectedInfo(final int position, final ID_TYPE id);
    void clearSelectedInfo();
    Integer getSelectedItemPosition();
    ID_TYPE getSelectedItemId();
    void makeSelectedDHIfNeed(final MasterFlowSelectableDHHelper<ID_TYPE> dhHelper, final MasterFlowSelectableBaseView selectableBaseView, final int currentPositionInData, final boolean isRefresh);
    void selectItem(final DH_TYPE dh, final int position);
    boolean selectItem(final DH_TYPE dh, final int position, final MasterFlowSelectableBaseView selectableBaseView);

}
