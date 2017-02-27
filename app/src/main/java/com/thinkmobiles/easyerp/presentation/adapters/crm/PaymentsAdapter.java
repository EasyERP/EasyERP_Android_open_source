package com.thinkmobiles.easyerp.presentation.adapters.crm;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PaymentDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.PaymentVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class PaymentsAdapter extends SelectableAdapter<PaymentDH, PaymentVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_payment;
    }
}
