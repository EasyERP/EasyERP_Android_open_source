package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;

/**
 * Created by Lynx on 2/3/2017.
 */

public final class ContactDH extends RecyclerDH {

    private final  Customer customer;

    public ContactDH(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
