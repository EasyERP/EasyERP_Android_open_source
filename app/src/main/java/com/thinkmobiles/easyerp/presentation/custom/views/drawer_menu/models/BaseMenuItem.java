package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models;

import android.support.annotation.DrawableRes;

/**
 * Created by Asus_Dev on 1/16/2017.
 */

public abstract class BaseMenuItem {

    private int id;
    private @DrawableRes int iconRes;
    private String label;
    private boolean selected;

    public BaseMenuItem(int id, int iconRes, String label) {
        this.id = id;
        this.iconRes = iconRes;
        this.label = label;
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
}
