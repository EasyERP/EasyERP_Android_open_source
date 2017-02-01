package com.thinkmobiles.easyerp.presentation.base.rules;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/18/2017.)
 */

public interface MasterFlowSelectableBaseView<T extends BasePresenter> extends BaseView<T> {
    int getCountItemsNow();
    void changeSelectedItem(int oldPosition, int newPosition);
}
