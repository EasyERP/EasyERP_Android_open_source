package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public abstract class BaseViewHolder<D> {

    protected final View itemView;
    protected D data;

    public View getItemView() {
        return itemView;
    }

    public D getData() {
        return data;
    }

    public BaseViewHolder(View itemView) {
        this.itemView = itemView;
    }

    protected <T extends View> T findWithId(@IdRes int idView) {
        return (T) itemView.findViewById(idView);
    }

    public void injectData(final D _data) {
        this.data = _data;
    }

}
