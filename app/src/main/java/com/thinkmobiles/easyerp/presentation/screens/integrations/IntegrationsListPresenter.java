package com.thinkmobiles.easyerp.presentation.screens.integrations;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.data.model.integrations.ChannelType;
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

    private final ChannelType channelType;

    private final ArrayList<Channel> channels = new ArrayList<>();

    public IntegrationsListPresenter(IntegrationsListContract.IntegrationsListView view, IntegrationsListContract.IntegrationsListModel model, ChannelType channelType) {
        this.view = view;
        this.model = model;
        this.channelType = channelType;

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
                model.getChannels(channelType).subscribe(
                        channels -> {
                            totalItems = channels.size();
                            saveData(channels, true);
                            setData(channels, true);
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

    @Override
    public void updateListItemChannel(Channel channel) {
        for (int i = 0; i < channels.size(); i++) {
            if (channels.get(i).id.equals(channel.id)) {
                channels.set(i, channel);
                retainInstance();
                break;
            }
        }
    }
}
