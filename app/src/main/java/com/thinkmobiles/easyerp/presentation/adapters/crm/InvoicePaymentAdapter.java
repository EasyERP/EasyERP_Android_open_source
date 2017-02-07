package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.InvoicePaymentDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.InvoicePaymentVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EBean
public class InvoicePaymentAdapter extends SimpleRecyclerAdapter<InvoicePaymentDH, InvoicePaymentVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_invoice_payment;
    }
}
