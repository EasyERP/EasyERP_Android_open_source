package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public interface IMenuClickListener {
    void chooseModule(final int moduleId);
    void chooseItem(final int moduleId, final int itemId);
    void onClickUser();
}
