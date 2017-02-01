package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;

import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public interface IMenuProviderFunctions {

    void setHeaderUserData(final UserInfo userInfo);
    boolean selectModule(final int moduleId);
    void selectItem(final int moduleId, final int itemId, final boolean withUI);
    void selectItem(final int itemId, final boolean withUI);
    void buildMenuItems(final List<MenuItem> menuItems);
    void buildMiniMenuItems(final List<MenuItem> menuItems);
    void buildModuleItems(final List<MenuItem> menuModules);
    void setSlideOffset(float slideOffset);

    interface IMenuItemsProviderFunctions {
        void select(final int id);
    }

    interface IHeaderMenuProviderFunctions {
        void showModules();
        void selectCurrentUser();
    }

}
