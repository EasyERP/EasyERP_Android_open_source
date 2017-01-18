package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu;

/**
 * Created by Asus_Dev on 1/13/2017.
 */

public enum MenuDrawerState {
    OPEN(0), CLOSE(1), HIDE(2);

    private int code;

    MenuDrawerState(final int code) {
        this.code = code;
    }

    public static MenuDrawerState valueOf(final int code) {
        for (MenuDrawerState state: values())
            if (state.code == code)
                return state;
        return OPEN;
    }

}
