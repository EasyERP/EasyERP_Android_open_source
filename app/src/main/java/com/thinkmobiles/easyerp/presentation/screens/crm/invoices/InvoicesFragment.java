package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.domain.DomainHelper;
import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.presentation.adapters.crm.InvoicesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.details.InvoiceDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class InvoicesFragment extends MasterFilterableFragment implements InvoicesContract.InvoicesView {

    private InvoicesContract.InvoicesPresenter presenter;

    @FragmentArg
    protected int moduleId;

    protected InvoiceRepository invoiceRepository;
    @Bean
    protected InvoicesAdapter invoicesAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        invoiceRepository = DomainHelper.getInvoiceRepository(moduleId);
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
        return String.format("%s Invoice list screen", MenuConfigs.getModuleLabel(moduleId));
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
            getMasterDelegate().replaceFragmentContentDetail(InvoiceDetailsFragment_.builder()
                    .invoiceId(invoiceID)
                    .moduleId(moduleId)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }
}
