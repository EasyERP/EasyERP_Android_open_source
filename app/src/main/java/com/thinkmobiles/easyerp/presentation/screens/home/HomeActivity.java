package com.thinkmobiles.easyerp.presentation.screens.home;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowActivity;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerContainer;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerState;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerView;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 1/13/2017.
 */

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseMasterFlowActivity {

    @Extra
    protected UserInfo userInfo;

    @ViewById(R.id.mdvMenu_AH)
    protected MenuDrawerView menuDrawerView;

    @ViewById(R.id.mdvMenuContainer_AH)
    protected MenuDrawerContainer menuDrawerContainer;

    @AfterViews
    protected void initMenu() {
        menuDrawerView.setMenuClickListener((moduleId, itemId) -> replaceFragmentContent(MenuConfigs.getFragmentByMenuId(moduleId, itemId)));
        menuDrawerView.setHeaderUserData(userInfo);
    }

    @Override
    protected void onHomeMenuSelect(boolean isHamburger) {
        if (isHamburger)
            changeStateMenu();
        else onBackPressed();
    }

    @Override
    protected int contentIdLayout() {
        return R.id.flContentContainer;
    }

    @Override
    protected int contentDetailIdLayout() {
        return R.id.flContentDetailContainer;
    }

    private void changeStateMenu() {
        switch (menuDrawerContainer.getStateMenu()) {
            case OPEN:
                menuDrawerContainer.setStateMenu(MenuDrawerState.CLOSE);
                break;
            case CLOSE:
                menuDrawerContainer.setStateMenu(MenuDrawerState.OPEN);
                break;
        }
        menuDrawerContainer.initStateMenu();
    }

}
