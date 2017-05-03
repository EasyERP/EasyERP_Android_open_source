package com.thinkmobiles.easyerp.presentation.screens.integrations;

import android.text.TextUtils;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.domain.integrations.IntegrationsRepository;
import com.thinkmobiles.easyerp.presentation.adapters.integrations.ChannelsListAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public class IntegrationsListFragment extends MasterSelectableFragment implements IntegrationsListContract.IntegrationsListView {

    private IntegrationsListContract.IntegrationsListPresenter presenter;

    @FragmentArg
    protected String channel;
    @FragmentArg
    protected String itemLabel;

    @Bean
    protected ChannelsListAdapter channelsListAdapter;
    @Bean
    protected IntegrationsRepository integrationsRepository;

    @AfterInject
    @Override
    public void initPresenter() {
        presenter = new IntegrationsListPresenter(this, integrationsRepository, channel);
    }

    @Override
    public void setPresenter(IntegrationsListContract.IntegrationsListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected SelectablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return channelsListAdapter;
    }

    @Override
    public String getScreenName() {
        return String.format("Integrations | %s list screen", itemLabel);
    }

    @Override
    public void openDetailChannel(Channel channel) {
        //TODO open channel detail
    }
}
