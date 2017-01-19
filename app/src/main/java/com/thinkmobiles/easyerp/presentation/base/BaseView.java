package com.thinkmobiles.easyerp.presentation.base;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void initPresenter();
    void setPresenter(T presenter);
}
