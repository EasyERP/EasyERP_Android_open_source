package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListFragment;

import org.androidannotations.annotations.EFragment;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class MasterSelectableFragment extends MasterListFragment implements SelectableView {

    protected abstract SelectablePresenter getPresenter();
    protected abstract SelectableAdapter getAdapter();

    @Override
    public void changeSelectedItem(int oldPosition, int newPosition) {
        getAdapter().replaceSelectedItem(oldPosition, newPosition);
    }

    @Override
    public boolean withItemSelecting() {
        return getMasterDelegate().isTablet() && !getMasterDelegate().isPortrait();
    }

    @Override
    public void clearSelectedItem() {
        getPresenter().clearSelectedInfo();
    }
}
