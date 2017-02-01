package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public enum ContentMenuState {
    MINI, FULL_MODULES, FULL_MODULE_ITEMS

    /**
     * MINI - state when showed collapsed menu configuration UI (User avatar + demo menu icons). Default state, because menu by default is opened
     * FULL_MODULES - state when active modules preview (Highlighted spinner for choosing module and full list modules)
     * FULL_MODULE_ITEMS - showing menu items with selected module
     */
}
