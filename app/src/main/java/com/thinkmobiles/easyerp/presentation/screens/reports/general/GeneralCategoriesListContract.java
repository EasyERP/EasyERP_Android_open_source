package com.thinkmobiles.easyerp.presentation.screens.reports.general;

import com.thinkmobiles.easyerp.data.model.reports.general.Category;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface GeneralCategoriesListContract {
    interface GeneralCategoriesListView extends BaseView<GeneralCategoriesListPresenter>, SelectableView {
        void openCategoryDetail(final String key);
    }
    interface GeneralCategoriesListPresenter extends SelectablePresenter {

    }
    interface GeneralCategoriesListModel extends BaseModel {
        Observable<List<Category>> getListCategories();
    }
}
