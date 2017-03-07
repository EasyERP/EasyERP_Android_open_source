package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers;

import com.thinkmobiles.easyerp.domain.inventory.TransfersRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 3/7/2017.
 */

@EFragment
public class TransfersFragment extends MasterFilterableFragment implements TransfersContract.TransfersView {

    private TransfersContract.TransfersPresenter presenter;

    @Bean
    protected TransfersRepository transfersRepository;

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(TransfersContract.TransfersPresenter presenter) {

    }

    @Override
    public String getScreenName() {
        return null;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return null;
    }

    @Override
    public void openDetailsScreen(String goodOutNoteID) {

    }

    @Override
    protected FilterablePresenter getPresenter() {
        return null;
    }
}
