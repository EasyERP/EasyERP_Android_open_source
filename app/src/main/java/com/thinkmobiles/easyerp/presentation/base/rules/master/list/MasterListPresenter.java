package com.thinkmobiles.easyerp.presentation.base.rules.master.list;

import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface MasterListPresenter extends ContentPresenter {
    void clickItem(int position);
    void refresh();
    void loadNextPage();
}
