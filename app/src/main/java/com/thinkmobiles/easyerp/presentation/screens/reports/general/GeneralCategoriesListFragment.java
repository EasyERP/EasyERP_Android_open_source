package com.thinkmobiles.easyerp.presentation.screens.reports.general;

import com.thinkmobiles.easyerp.domain.reports.ReportsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.reports.GeneralCategoriesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 4/8/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class GeneralCategoriesListFragment extends MasterSelectableFragment implements GeneralCategoriesListContract.GeneralCategoriesListView {

    private GeneralCategoriesListContract.GeneralCategoriesListPresenter presenter;

    @Bean
    protected GeneralCategoriesAdapter generalCategoriesAdapter;
    @Bean
    protected ReportsRepository reportsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        new GeneralCategoriesListPresenter(this, reportsRepository);
    }

    @Override
    public void setPresenter(GeneralCategoriesListContract.GeneralCategoriesListPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Reports General list screen";
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return generalCategoriesAdapter;
    }

    @Override
    public void openCategoryDetail(String key) {
        if (key != null) {
            //TODO open general category detail screen
        } else mActivity.replaceFragmentContentDetail(null);
    }
}
