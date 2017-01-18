package com.thinkmobiles.easyerp.presentation.screens.home;

import android.widget.ImageView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowActivity;
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

    @ViewById(R.id.mdvMenu_AM)
    protected MenuDrawerView menuDrawerView;
    @ViewById
    protected ImageView ivImage;

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
}
