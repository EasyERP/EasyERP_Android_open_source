package com.thinkmobiles.easyerp.domain.reports;

import com.thinkmobiles.easyerp.data.model.reports.general.Category;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.reports.general.GeneralCategoriesListContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class ReportsRepository extends NetworkRepository implements GeneralCategoriesListContract.GeneralCategoriesListModel {

    @Override
    public Observable<List<Category>> getListCategories() {
        final List<Category> generalCategories = new ArrayList<>();
        generalCategories.add(new Category("0", "Recent", "recent"));
        generalCategories.add(new Category("1", "Favorites", "favorite"));
        generalCategories.add(new Category("2", "Created By Me", "createdByMe"));
        generalCategories.add(new Category("3", "Private", "private"));
        generalCategories.add(new Category("4", "Public", "public"));
        generalCategories.add(new Category("5", "All", "all"));
        return Observable.just(generalCategories);
    }
}
