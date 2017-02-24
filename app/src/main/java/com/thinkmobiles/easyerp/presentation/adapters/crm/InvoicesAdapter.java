package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoiceDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.InvoiceVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class InvoicesAdapter extends SelectableAdapter<InvoiceDH, InvoiceVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_invoice;
    }
}
