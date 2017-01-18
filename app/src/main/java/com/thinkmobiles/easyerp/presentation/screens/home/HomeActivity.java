package com.thinkmobiles.easyerp.presentation.screens.home;

import android.view.MenuItem;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowActivity;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerContainer;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerState;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.MenuDrawerView;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 1/13/2017.
 */

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseMasterFlowActivity {

    @ViewById(R.id.mdvMenu_AH)
    protected MenuDrawerView menuDrawerView;

    @ViewById(R.id.mdvMenuContainer_AH)
    protected MenuDrawerContainer menuDrawerContainer;

    @AfterViews
    protected void initMenu() {
        menuDrawerView.setMenuClickListener((moduleId, itemId) -> replaceFragmentContent(MenuConfigs.getFragmentByMenuId(moduleId, itemId)));
    }

    @Override
    protected int contentIdLayout() {
        return R.id.flContentContainer;
    }

    @Override
    protected int contentDetailIdLayout() {
        return R.id.flContentDetailContainer;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                changeStateMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
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
