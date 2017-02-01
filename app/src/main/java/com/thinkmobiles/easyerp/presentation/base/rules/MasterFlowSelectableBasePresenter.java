package com.thinkmobiles.easyerp.presentation.base.rules;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.DashboardListDH;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface MasterFlowSelectableBasePresenter<ID_TYPE, DH_TYPE extends MasterFlowSelectableDHHelper<ID_TYPE>> extends BasePresenter {

    void setSelectedInfo(final int position, final ID_TYPE id);
    Integer getSelectedItemPosition();
    ID_TYPE getSelectedItemId();
    void makeSelectedDHIfNeed(final MasterFlowSelectableDHHelper<ID_TYPE> dhHelper, final MasterFlowSelectableBaseView baseViewWithCountItemsLeft, final int currentPositionInData, final boolean isRefresh);
    void selectItem(DH_TYPE dh, int position);

}
