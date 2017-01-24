package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.ExpectedRevenue;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NextAction;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Social;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/24/2017.
 */

public class OpportunityItem {
    public String _id;
    public String skype;
    public Social social;
    public String projectType;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public String convertedDate;
    public boolean isConverted;
    public String source;
    public String campaign;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public int sequence;
    public String whoCanRW;
    public Workflow workflow;
    public String reffered;
    public boolean optout;
    public boolean active;
    public String color;
    public Categories categories;
    public String priority;
    public String expectedClosing;
    public NextAction nextAction;
    public String internalNotes;
    public SalesPerson salesPerson;
    public String func;
    public Phone phones;
    public String email;
    public Name contactName;
    public Address address;
    public ArrayList<String> tags;
    public String customer;
    public String company;
    public String tempCompanyField;
    public String creationDate;
    public String jobPosition;
    public ExpectedRevenue expectedRevenue;
    public String name;
    public boolean isOpportunitie;
}
