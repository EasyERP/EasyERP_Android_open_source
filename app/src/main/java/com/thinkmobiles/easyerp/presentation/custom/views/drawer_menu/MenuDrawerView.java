package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders.ITransformContent;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders.MenuHeaderViewHolder;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders.MenuItemViewHolder;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders.MiniMenuHeaderViewHolder;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders.MiniMenuItemViewHolder;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/13/2017.)
 */
@EViewGroup(R.layout.view_drawer_menu)
public class MenuDrawerView extends FrameLayout implements IMenuProviderFunctions {

    @ViewById(R.id.svContainerExpandedLayout_VDM)
    protected View containerExpandedLayout;

    @ViewById(R.id.svContainerCollapsedLayout_VDM)
    protected View containerCollapsedLayout;

    @ViewById(R.id.llInsideContainerExpandedLayout_VDM)
    protected View insideContainerExpandedLayout;

    @ViewById(R.id.llMenuItemsContainer_VDM)
    protected LinearLayout menuItemsLayout;

    @ViewById(R.id.llMiniMenuItemsContainer_VDM)
    protected LinearLayout miniMenuItemsLayout;

    @ViewById(R.id.vBottomDividerMenuItems_VDM)
    protected View bottomDividerView;

    private ContentMenuState contentMenuState = ContentMenuState.MINI;
    private IMenuClickListener menuClickListener;

    private MenuHeaderViewHolder menuHeaderViewHolder;
    private List<MenuItemViewHolder> menuItemViewHolderList = new ArrayList<>();
    private MiniMenuHeaderViewHolder miniMenuHeaderViewHolder;
    private List<MiniMenuItemViewHolder> miniMenuItemViewHolderList = new ArrayList<>();

    private float distanceBetweenMenuItemsAndAvatar;

    public MenuDrawerView(@NonNull Context context) {
        super(context);
    }

    public MenuDrawerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuDrawerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    protected void initHeaderHolder() {
        distanceBetweenMenuItemsAndAvatar = getResources().getDimension(R.dimen.menu_items_distance_to_top);

        menuHeaderViewHolder = new MenuHeaderViewHolder(findViewById(R.id.rlHeaderContainer_VDMH), headerMenuProviderFunctions);
        miniMenuHeaderViewHolder = new MiniMenuHeaderViewHolder(findViewById(R.id.rlHeaderContainer_VDMMH), headerMenuProviderFunctions);
    }

    /**
     * Default state, when initialized menu. By default: module CRM
     */
    public void defaultSelect() {
        if (!isSelectedItemNow())
            selectItem(1, 0, true);
    }

    private boolean isSelectedItemNow() {
        return menuHeaderViewHolder.getCurrentChosenModuleId() >= 0 && menuHeaderViewHolder.getCurrentChosenItemId() >= 0;
    }

    public void setMenuClickListener(IMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    @Override
    public void setHeaderUserData(UserInfo userInfo) {
        menuHeaderViewHolder.injectData(userInfo);
        miniMenuHeaderViewHolder.injectData(userInfo);
    }

    @Override
    public void selectModule(int moduleId) {
        if (contentMenuState.equals(ContentMenuState.FULL_MODULES) || menuHeaderViewHolder.getCurrentChosenModuleId() != moduleId) {
            menuHeaderViewHolder.setCurrentChosenModuleId(moduleId);

            buildMenuItems(MenuConfigs.menuModuleItems.get(moduleId));
            buildMiniMenuItems(MenuConfigs.menuModuleItems.get(moduleId));
        }
    }

    @Override
    public void selectItem(int moduleId, int itemId, boolean withUI) {
        final boolean isDifferentItems = moduleId != menuHeaderViewHolder.getCurrentChosenItemForModuleId() || menuHeaderViewHolder.getCurrentChosenItemId() != itemId;
        final boolean humanClickedItem = isDifferentItems && withUI;

        if (isDifferentItems && menuHeaderViewHolder.getCurrentChosenItemId() == MenuHeaderViewHolder.UNKNOWN_ID)
            menuHeaderViewHolder.setCurrentChosenItemId(moduleId, itemId);

        selectModule(moduleId);

        if (humanClickedItem) {
            menuHeaderViewHolder.setCurrentChosenItemId(moduleId, itemId);
            selectItemWithId(itemId);
            selectMiniItemWithId(itemId);
            if (menuClickListener != null)
                menuClickListener.chooseItem(moduleId, itemId);
        }
    }

    @Override
    public void selectItem(int itemId, boolean withUI) {
        if (menuHeaderViewHolder.getCurrentChosenModuleId() >= 0)
            selectItem(menuHeaderViewHolder.getCurrentChosenModuleId(), itemId, withUI);
    }

    @Override
    public void buildMenuItems(List<MenuItem> menuItems) {
        if (!contentMenuState.equals(ContentMenuState.FULL_MODULE_ITEMS)) {
            contentMenuState = ContentMenuState.FULL_MODULE_ITEMS;
            menuHeaderViewHolder.setModulesContentState(false);
            buildItems(menuItems);
        }
    }

    @Override
    public void buildMiniMenuItems(List<MenuItem> menuItems) {
        if (!contentMenuState.equals(ContentMenuState.MINI)) {
            contentMenuState = ContentMenuState.MINI;
            miniMenuItemsLayout.removeAllViews();
            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            for (MenuItem menuItem: menuItems) {
                final MiniMenuItemViewHolder miniMenuItemViewHolder = new MiniMenuItemViewHolder(layoutInflater.inflate(R.layout.view_drawer_mini_menu_item, miniMenuItemsLayout, false), menuItemsProviderFunctions);
                miniMenuItemViewHolder.injectData(menuItem);
                miniMenuItemViewHolderList.add(miniMenuItemViewHolder);
                miniMenuItemsLayout.addView(miniMenuItemViewHolder.getItemView());
            }
        }
    }

    @Override
    public void buildModuleItems(List<MenuItem> menuModules) {
        if (!contentMenuState.equals(ContentMenuState.FULL_MODULES)) {
            contentMenuState = ContentMenuState.FULL_MODULES;
            menuHeaderViewHolder.setModulesContentState(true);
            buildItems(menuModules);
            selectItemWithId(menuHeaderViewHolder.getCurrentChosenModuleId());
        } else selectItem(menuHeaderViewHolder.getCurrentChosenItemId(), false);
    }

    @Override
    public void setSlideOffset(float slideOffset) {
        if (slideOffset <= 0f) {
            containerCollapsedLayout.scrollTo(0, 0);
            transformViews(slideOffset);
            containerCollapsedLayout.setVisibility(VISIBLE);
            containerExpandedLayout.setVisibility(INVISIBLE);

            contentMenuState = ContentMenuState.MINI;
        } else {
            transformViews(slideOffset);
            containerExpandedLayout.setVisibility(VISIBLE);
            containerCollapsedLayout.setVisibility(INVISIBLE);

            if (slideOffset == 1f)
                contentMenuState = ContentMenuState.FULL_MODULE_ITEMS;
        }
    }

    private void transformViews(final float slideOffset) {
        if (contentMenuState.equals(ContentMenuState.FULL_MODULES))
            buildModuleItems(MenuConfigs.menuModules);

        menuHeaderViewHolder.setTransformCoefficient(slideOffset);
        transformMenuItemsContent.setTransformCoefficient(slideOffset);
    }

    /**
     * Find menu item (not module) with id and make select, another items is unselected
     * @param id - id menu item
     */
    private void selectItemWithId(final int id) {
        for (MenuItemViewHolder itemViewHolder : menuItemViewHolderList) {
            final MenuItem menuItem = itemViewHolder.getData();
            if (menuItem.getId() == id)
                menuItem.setSelected(true);
            else menuItem.setSelected(false);
            itemViewHolder.injectData(menuItem);
        }
    }

    /**
     * Find module menu item with id and make select, another items is unselected
     * @param id - id module item
     */
    private void selectMiniItemWithId(final int id) {
        for (MiniMenuItemViewHolder menuItemViewHolder : miniMenuItemViewHolderList) {
            final MenuItem menuItem = menuItemViewHolder.getData();
            if (menuItem.getId() == id)
                menuItem.setSelected(true);
            else menuItem.setSelected(false);
            menuItemViewHolder.injectData(menuItem);
        }
    }

    /**
     * Building main container with menu items. Using for modules and for items with modules
     * @param menuItems - list menu items (or modules)
     */
    private void buildItems(final List<MenuItem> menuItems) {
        menuItemsLayout.removeAllViews();
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (MenuItem menuItem: menuItems) {
            final MenuItemViewHolder menuItemHolder = new MenuItemViewHolder(layoutInflater.inflate(R.layout.view_drawer_menu_item, menuItemsLayout, false), menuItemsProviderFunctions);
            menuItem.setSelected(menuItem.getId() == menuHeaderViewHolder.getCurrentChosenItemId() && menuHeaderViewHolder.getCurrentChosenModuleId() == menuHeaderViewHolder.getCurrentChosenItemForModuleId());
            menuItemHolder.injectData(menuItem);
            menuItemViewHolderList.add(menuItemHolder);
            menuItemsLayout.addView(menuItemHolder.getItemView());
        }
    }

    private final IMenuItemsProviderFunctions menuItemsProviderFunctions = id -> {
        switch (contentMenuState) {
            case MINI:
            case FULL_MODULE_ITEMS:
                selectItem(id, true);
                break;
            case FULL_MODULES:
                selectModule(id);
                if (menuClickListener != null)
                    menuClickListener.chooseModule(id);
                break;
        }
    };

    private final IHeaderMenuProviderFunctions headerMenuProviderFunctions = new IHeaderMenuProviderFunctions() {
        @Override
        public void showModules() {
            buildModuleItems(MenuConfigs.menuModules);
        }

        @Override
        public void selectCurrentUser() {
            if (menuClickListener != null)
                menuClickListener.onClickUser();
        }
    };

    private final ITransformContent transformMenuItemsContent = slideOffset -> {
        for (MenuItemViewHolder itemViewHolder: menuItemViewHolderList)
            itemViewHolder.setTransformCoefficient(slideOffset);

        bottomDividerView.setAlpha(slideOffset);

        insideContainerExpandedLayout.setTranslationY((containerExpandedLayout.getScrollY() - containerCollapsedLayout.getScrollY()) * (1f - slideOffset));

        final float translationY = (-distanceBetweenMenuItemsAndAvatar) * (1f - slideOffset);
        menuItemsLayout.setTranslationY(translationY);
        bottomDividerView.setTranslationY(translationY);
    };

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.selectedModule = menuHeaderViewHolder.getCurrentChosenItemForModuleId();
        ss.selectedItem = menuHeaderViewHolder.getCurrentChosenItemId();
        ss.contentMenuState = contentMenuState;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (ss.selectedModule >= 0) {
            if (ss.selectedItem >= 0)
                selectItem(ss.selectedModule, ss.selectedItem, false);
            else selectModule(ss.selectedModule);
        }

        if (ss.contentMenuState != null) {
            contentMenuState = ss.contentMenuState;
            if (getParent() instanceof MenuDrawerContainer) {
                MenuDrawerContainer parent = (MenuDrawerContainer) getParent();
                switch (contentMenuState) {
                    case MINI:
                        parent.setStateMenu(MenuDrawerState.CLOSE);
                        break;
                    case FULL_MODULE_ITEMS:
                    case FULL_MODULES:
                        parent.setStateMenu(MenuDrawerState.OPEN);
                        break;
                }
                parent.initStateMenu(true);
            }
        }
    }

    private static class SavedState extends BaseSavedState {

        int selectedModule;
        int selectedItem;
        ContentMenuState contentMenuState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.selectedModule = in.readInt();
            this.selectedItem = in.readInt();
            this.contentMenuState = (ContentMenuState) in.readSerializable();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.selectedModule);
            out.writeInt(this.selectedItem);
            out.writeSerializable(this.contentMenuState);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
