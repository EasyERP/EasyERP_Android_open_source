package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models;

import android.support.annotation.DrawableRes;

/**
 * Created by Asus_Dev on 1/16/2017.
 */

public final class MenuItem {

    private int id;
    private @DrawableRes int iconRes;
    private String label;
    private boolean selected;
    private boolean enabled;

    public MenuItem(int id, int iconRes, String label, boolean enabled) {
        this.id = id;
        this.iconRes = iconRes;
        this.label = label;
        this.enabled = enabled;
    }

    public MenuItem(int id, int iconRes, String label) {
        this.id = id;
        this.iconRes = iconRes;
        this.label = label;
        this.enabled = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
