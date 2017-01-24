package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Company;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPurchases;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Social;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/24/2017.
 */

public class ResponseGetPersonDetails {
    public String id;
    public String fullName;
    public String imageSrc;
    public String email;
    public String jobPosition;
    public String skype;
    public String dateBirth;
    public String type;

    /*Nested models*/
    public CreatedEditedBy editedBy;
    public CreatedEditedBy createdBy;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public Social social;
    public SalesPurchases salesPurchases;
    public Phone phones;
    public Address shippingAddress;
    public Address address;
    public Company company;
    public Name name;
    public ArrayList<OpportunityItem> opportunities;
}
