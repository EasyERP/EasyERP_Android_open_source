package com.thinkmobiles.easyerp.presentation.base.rules;

import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 2/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public abstract class MasterFlowListSelectableFragment extends ListRefreshFragment {

    public boolean withItemSelecting() {
        return mActivity.isTablet && !mActivity.isPortrait;
    }
    public abstract void clearSelectedItem();
}
