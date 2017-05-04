package com.thinkmobiles.easyerp.presentation.screens.integrations.details;

import android.widget.TextView;

import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * @author Michael Soyma (Created on 5/4/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EFragment
public abstract class IntegrationChannelDetailFragment extends ContentFragment {

    @FragmentArg
    protected Channel channel;

    @ViewById
    protected TextView tvChannel_VICH;
    @ViewById
    protected TextView tvOrdersValue_VICH, tvConflictedProductsValue_VICH, tvListingValue_VICH, tvUnlinkedOrdersValue_VICH;

    @AfterViews
    protected void fillHeaderUI() {
        tvChannel_VICH.setText(channel.channelName);
        tvOrdersValue_VICH.setText(String.valueOf(channel.importedOrders));
        tvConflictedProductsValue_VICH.setText(String.valueOf(channel.conflictProducts));
        tvListingValue_VICH.setText(String.valueOf(channel.importedProducts));
        tvUnlinkedOrdersValue_VICH.setText(String.valueOf(channel.unlinkedOrders));
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }
}
