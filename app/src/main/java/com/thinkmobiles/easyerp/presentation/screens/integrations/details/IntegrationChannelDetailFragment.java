package com.thinkmobiles.easyerp.presentation.screens.integrations.details;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;

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
public abstract class IntegrationChannelDetailFragment extends ContentFragment implements IntegrationChannelDetailView {

    @FragmentArg
    protected Channel channel;

    @ViewById
    protected TextView tvChannel_VICH, tvChannelStatus_VICH;
    @ViewById
    protected TextView tvOrdersValue_VICH, tvConflictedProductsValue_VICH, tvListingValue_VICH, tvUnlinkedOrdersValue_VICH;
    @ViewById
    protected View tilContainerBankAccount_VICWS;
    @ViewById
    protected EditText etWarehouse_VICWS, etLocation_VICWS, etPriceList_VICWS, etBankAccount_VICWS;

    @AfterViews
    protected void fillHeaderUI() {
        tvChannel_VICH.setText(channel.channelName);
        tvChannelStatus_VICH.setText(channel.connected ? R.string.connected : R.string.disconnected);
        tvChannelStatus_VICH.setBackgroundDrawable(new RoundRectDrawable(getResources().getColor(channel.connected ? R.color.color_chips_green : R.color.color_chips_red)));

        tvOrdersValue_VICH.setText(String.valueOf(channel.importedOrders));
        tvConflictedProductsValue_VICH.setText(String.valueOf(channel.conflictProducts));
        tvListingValue_VICH.setText(String.valueOf(channel.importedProducts));
        tvUnlinkedOrdersValue_VICH.setText(String.valueOf(channel.unlinkedOrders));
    }

    @Override
    public void displayWarehouse(String warehouse) {
        etWarehouse_VICWS.setText(warehouse);
    }

    @Override
    public void displayLocation(String location) {
        etLocation_VICWS.setText(location);
    }

    @Override
    public void displayPriceList(String priceList) {
        etPriceList_VICWS.setText(priceList);
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }
}
