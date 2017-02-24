package com.thinkmobiles.easyerp.presentation.base.rules.master.list;

import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenterHelper;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class MasterListPresenterHelper extends ContentPresenterHelper implements MasterListPresenter {

    protected abstract MasterListView getView();
    protected abstract void loadPage(final int page);
    protected abstract int getCountItems();

    protected int totalItems = Integer.MAX_VALUE;
    protected int currentPage = 1;

    @Override
    public void subscribe() {
        if (!hasContent()) {
            getFirstPage();
        } else {
            retainInstance();
        }
    }

    protected void getFirstPage() {
        getView().showProgress(Constants.ProgressType.CENTER);
        refresh();
    }

    @Override
    public void refresh() {
        loadPage(1);
    }

    @Override
    public void loadNextPage() {
        if (getCountItems() == totalItems) {
            return;
        }
        getView().showProgress(Constants.ProgressType.BOTTOM);
        loadPage(currentPage + 1);
    }
}
