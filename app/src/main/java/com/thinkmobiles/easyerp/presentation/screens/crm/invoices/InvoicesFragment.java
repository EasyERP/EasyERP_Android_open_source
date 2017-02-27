package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.InvoicesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorType;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowListSelectableFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details.InvoiceDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class InvoicesFragment extends MasterFilterableFragment implements InvoicesContract.InvoicesView {

    private InvoicesContract.InvoicesPresenter presenter;

    @Bean
    protected InvoiceRepository invoiceRepository;
    @Bean
    protected InvoicesAdapter invoicesAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new InvoicesPresenter(this, invoiceRepository);
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public void setPresenter(InvoicesContract.InvoicesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Invoice list screen";
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return invoicesAdapter;
    }

    @Override
    public void openDetailsScreen(String invoiceID) {
        if (invoiceID != null) {
            mActivity.replaceFragmentContentDetail(InvoiceDetailsFragment_.builder()
                    .invoiceId(invoiceID)
                    .build());
        } else {
            mActivity.replaceFragmentContentDetail(null);
        }
    }
}
