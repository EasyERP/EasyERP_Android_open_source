package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class InvoiceDH extends SelectableDHHelper {
    private final Invoice invoice;

    public InvoiceDH(Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public String getId() {
        return invoice.id;
    }
}
