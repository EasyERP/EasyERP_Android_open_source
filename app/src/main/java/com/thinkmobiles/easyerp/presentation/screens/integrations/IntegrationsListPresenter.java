package com.thinkmobiles.easyerp.presentation.screens.integrations;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.integrations.ChannelDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class IntegrationsListPresenter extends MasterSelectablePresenterHelper implements IntegrationsListContract.IntegrationsListPresenter {

    private IntegrationsListContract.IntegrationsListView view;
    private IntegrationsListContract.IntegrationsListModel model;

    private final String channelFilter;

    private final ArrayList<Channel> channels = new ArrayList<>();

    public IntegrationsListPresenter(IntegrationsListContract.IntegrationsListView view, IntegrationsListContract.IntegrationsListModel model, String channel) {
        this.view = view;
        this.model = model;
        this.channelFilter = channel;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        Channel channel = channels.get(position);
        if (super.selectItem(channel.id, position))
            view.openDetailChannel(channel);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getChannels(channelFilter).subscribe(
                        channelResponseGetResultItems -> {
                            totalItems = channelResponseGetResultItems.result.size();
                            saveData(channelResponseGetResultItems.result, true);
                            setData(channelResponseGetResultItems.result, true);
                        }, t -> error(t))
                );
    }

    @Override
    protected int getCountItems() {
        return channels.size();
    }

    @Override
    protected boolean hasContent() {
        return !channels.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(channels, true);
    }

    private void saveData(final List<Channel> channels, boolean needClear) {
        if (needClear)
            this.channels.clear();
        this.channels.addAll(channels);
    }

    public void setData(final List<Channel> channels, boolean needClear) {
        view.setDataList(prepareChannelDHs(channels), needClear);
        if (this.channels.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<ChannelDH> prepareChannelDHs(final List<Channel> channels) {
        final ArrayList<ChannelDH> result = new ArrayList<>();
        for (Channel channel : channels) {
            final ChannelDH channelDH = new ChannelDH(channel);
            makeSelectedDHIfNeed(channelDH, this.channels.indexOf(channel));
            result.add(channelDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
