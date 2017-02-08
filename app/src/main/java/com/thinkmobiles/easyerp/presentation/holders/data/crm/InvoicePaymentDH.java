package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.invoice.detail.InvoicePayment;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public final class InvoicePaymentDH extends RecyclerDH {

    private final InvoicePayment model;

    public InvoicePaymentDH(InvoicePayment model) {
        this.model = model;
    }

    public InvoicePayment getModel() {
        return model;
    }
}
