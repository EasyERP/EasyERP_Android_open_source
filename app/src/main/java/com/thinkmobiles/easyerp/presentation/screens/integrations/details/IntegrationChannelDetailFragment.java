package com.thinkmobiles.easyerp.presentation.screens.integrations.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.domain.integrations.IntegrationsRepository;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.Bean;
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

    @Bean
    protected IntegrationsRepository integrationsRepository;

    @ViewById
    protected TextView tvChannel_VICH, tvChannelStatus_VICH;
    @ViewById
    protected TextView tvOrdersValue_VICH, tvConflictedProductsValue_VICH, tvListingValue_VICH, tvUnlinkedOrdersValue_VICH;
    @ViewById
    protected View tilContainerBankAccount_VICWS;
    @ViewById
    protected EditText etWarehouse_VICWS, etLocation_VICWS, etPriceList_VICWS, etBankAccount_VICWS;

    protected MenuItem menuItemConnection;
    private ProgressDialog progressDialog;

    @Override
    public void fillHeaderUI(final Channel _channel) {
        tvChannel_VICH.setText(_channel.channelName);
        tvChannelStatus_VICH.setText(_channel.connected ? R.string.connected : R.string.disconnected);
        tvChannelStatus_VICH.setBackgroundDrawable(new RoundRectDrawable(getResources().getColor(_channel.connected ? R.color.color_chips_green : R.color.color_chips_red)));

        tvOrdersValue_VICH.setText(String.valueOf(_channel.importedOrders));
        tvConflictedProductsValue_VICH.setText(String.valueOf(_channel.conflictProducts));
        tvListingValue_VICH.setText(String.valueOf(_channel.importedProducts));
        tvUnlinkedOrdersValue_VICH.setText(String.valueOf(_channel.unlinkedOrders));

        if (menuItemConnection != null)
            ((Button) menuItemConnection.getActionView().findViewById(R.id.btnChangeConnection_MLAS)).setText(_channel.connected ? R.string.disconnect : R.string.connect);
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
    public void showProgress(String msg) {
        progressDialog = new ProgressDialog(mActivity, R.style.DefaultTheme_NoTitleDialogWithAnimation);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showInfoToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean optionsMenuForDetail() {
        return true;
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_integration_channel_detail;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        super.optionsMenuInitialized(menu);
        if (menu != null) {
            menuItemConnection = menu.findItem(R.id.actionChangeConnect_MICD);
            menuItemConnection.getActionView().findViewById(R.id.btnChangeConnection_MLAS).setOnClickListener(v -> onOptionsItemSelected(menuItemConnection));
        }
        getPresenter().subscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionChangeConnect_MICD:
                changeConnectState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendBroadcastUpdateChannel(Channel channel) {
        final Intent intent = new Intent(Constants.Actions.ACTION_UPDATE_CHANNEL);
        intent.putExtra(Constants.KEY_CHANNEL, channel);
        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);
    }

    protected abstract void changeConnectState();
}
