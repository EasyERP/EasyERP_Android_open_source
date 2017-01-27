package com.thinkmobiles.easyerp.presentation.base.rules;

import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * Created by Asus_Dev on 1/18/2017.
 */

public interface MasterFlowSelectableBaseView<T extends BasePresenter> extends BaseView<T> {
    int getCountItemsNow();
    void changeSelectedItem(int oldPosition, int newPosition);
}
