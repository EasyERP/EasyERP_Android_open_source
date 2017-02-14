package com.thinkmobiles.easyerp.presentation.screens.crm.invoices;

import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseGetFilters;
import com.thinkmobiles.easyerp.data.model.crm.invoice.ResponseGetInvoice;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.rules.ErrorViewHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBasePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableBaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;

import java.util.ArrayList;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface InvoicesContract {
    interface InvoicesView extends MasterFlowSelectableBaseView<InvoicesPresenter> {
        void displayInvoices(ArrayList<InvoiceDH> invoiceDHs, boolean needClear);
        void openInvoiceDetailsScreen(String invoiceID);

        void displayErrorState(final String msg, final ErrorViewHelper.ErrorType errorType);
        void displayErrorToast(final String msg);
        void showProgress(Constants.ProgressType type);

        void setCustomers(ArrayList<FilterDH> customers);
        void setTextToSearch(String text);
        void showFilters(boolean isShow);
        void selectFilter(int pos, boolean isSelected);

        void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName);
    }
    interface InvoicesPresenter extends MasterFlowSelectableBasePresenter<String, InvoiceDH> {
        void refresh();
        void loadNextPage();

        void filterByContactName(FilterDH filterDH);
        void filterBySearchContactName(String name);
        void filterByList(ArrayList<FilterDH> filterDHs, int requestCode);
        void removeFilter(int requestCode);

        void changeFilter(int requestCode, String filterName);
        void refreshOptionMenu();
        void removeAll();
    }
    interface InvoicesModel extends BaseModel {
        Observable<ResponseGetInvoice> getFilteredInvoices(FilterQuery query, int page);
        Observable<ResponseGetFilters> getInvoiceFilters();
    }
}

