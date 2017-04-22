package com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections;

import com.thinkmobiles.easyerp.domain.inventory.StockCorrectionRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.StockCorrectionsAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.details.StockCorrectionsDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class StockCorrectionsListFragment extends MasterSelectableFragment implements StockCorrectionsListContract.StockCorrectionsListView {

    private StockCorrectionsListContract.StockCorrectionsListPresenter presenter;

    @Bean
    protected StockCorrectionRepository stockCorrectionRepository;
    @Bean
    protected StockCorrectionsAdapter stockCorrectionsAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new StockCorrectionsListPresenter(this, stockCorrectionRepository);
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public void setPresenter(StockCorrectionsListContract.StockCorrectionsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Stock Correction list screen";
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return stockCorrectionsAdapter;
    }

    @Override
    public void openStockCorrectionDetail(String id) {
        if (id != null) {
            mActivity.replaceFragmentContentDetail(StockCorrectionsDetailsFragment_.builder()
                    .id(id)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
