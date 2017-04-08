package com.thinkmobiles.easyerp.presentation.screens.reports.general;

import com.thinkmobiles.easyerp.data.model.reports.general.Category;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.GeneralCategoryDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
final class GeneralCategoriesListPresenter extends MasterSelectablePresenterHelper implements GeneralCategoriesListContract.GeneralCategoriesListPresenter {

    private GeneralCategoriesListContract.GeneralCategoriesListView view;
    private GeneralCategoriesListContract.GeneralCategoriesListModel model;

    private List<Category> generalCategories = new ArrayList<>();

    public GeneralCategoriesListPresenter(GeneralCategoriesListContract.GeneralCategoriesListView view, GeneralCategoriesListContract.GeneralCategoriesListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        final Category category = generalCategories.get(position);
        if (super.selectItem(category.id, position))
            view.openCategoryDetail(category.key);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getListCategories()
                        .subscribe(categoryList -> {
                            generalCategories = categoryList;
                            totalItems = generalCategories.size();
                            setData();
                        },  t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return this.generalCategories.size();
    }

    @Override
    protected boolean hasContent() {
        return !this.generalCategories.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData();
    }

    private void setData() {
        if (generalCategories.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
            view.setDataList(prepareDashboardDHs(generalCategories), true);
        }
    }

    private ArrayList<GeneralCategoryDH> prepareDashboardDHs(final List<Category> categories) {
        final ArrayList<GeneralCategoryDH> result = new ArrayList<>();
        for (Category category : categories) {
            final GeneralCategoryDH generalCategoryDH = new GeneralCategoryDH(category);
            makeSelectedDHIfNeed(generalCategoryDH, generalCategories.indexOf(category));
            result.add(generalCategoryDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
