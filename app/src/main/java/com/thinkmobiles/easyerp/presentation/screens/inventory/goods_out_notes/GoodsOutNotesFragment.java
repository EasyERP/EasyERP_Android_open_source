package com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes;

import android.widget.Toast;

import com.thinkmobiles.easyerp.domain.inventory.GoodsOutNotesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.GoodsOutNotesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail.GoodsOutNotesDetailsFragment;
import com.thinkmobiles.easyerp.presentation.screens.inventory.goods_out_notes.detail.GoodsOutNotesDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 3/6/2017.
 */

@EFragment
public class GoodsOutNotesFragment extends MasterFilterableFragment implements GoodsOutNotesContract.GoodsOutNotesView {

    private GoodsOutNotesContract.GoodsOutNotesPresenter presenter;

    @Bean
    protected GoodsOutNotesRepository goodsOutNotesRepository;
    @Bean
    protected GoodsOutNotesAdapter goodsOutNotesAdapter;

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new GoodsOutNotesPresenter(this, goodsOutNotesRepository);
    }

    @Override
    public void setPresenter(GoodsOutNotesContract.GoodsOutNotesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Goods Out Notes list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return goodsOutNotesAdapter;
    }

    @Override
    public void openDetailsScreen(String goodOutNoteID) {
        mActivity.replaceFragmentContentDetail(GoodsOutNotesDetailsFragment_.builder()
                .id(goodOutNoteID)
                .build());
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }
}
