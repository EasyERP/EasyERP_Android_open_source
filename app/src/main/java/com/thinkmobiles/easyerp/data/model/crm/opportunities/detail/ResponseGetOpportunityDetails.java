package com.thinkmobiles.easyerp.data.model.crm.opportunities.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadCompany;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.ExpectedRevenue;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/1/2017.
 */

public class ResponseGetOpportunityDetails implements Parcelable {
    @SerializedName("_id")
    public String id;
    public String name;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public Workflow workflow;
    public String priority;
    public String expectedClosing;  //"2017-01-31T00:00:00.000Z"
    public SalesPerson salesPerson;
    public ArrayList<TagItem> tags;
    public Customer customer;
    public LeadCompany company;
    public ExpectedRevenue expectedRevenue;

    public ResponseGetOpportunityDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.priority);
        dest.writeString(this.expectedClosing);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeTypedList(this.tags);
        dest.writeParcelable(this.customer, flags);
        dest.writeParcelable(this.company, flags);
        dest.writeParcelable(this.expectedRevenue, flags);
    }

    protected ResponseGetOpportunityDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.priority = in.readString();
        this.expectedClosing = in.readString();
        this.salesPerson = in.readParcelable(SalesPerson.class.getClassLoader());
        this.tags = in.createTypedArrayList(TagItem.CREATOR);
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        this.company = in.readParcelable(LeadCompany.class.getClassLoader());
        this.expectedRevenue = in.readParcelable(ExpectedRevenue.class.getClassLoader());
    }

    public static final Creator<ResponseGetOpportunityDetails> CREATOR = new Creator<ResponseGetOpportunityDetails>() {
        @Override
        public ResponseGetOpportunityDetails createFromParcel(Parcel source) {
            return new ResponseGetOpportunityDetails(source);
        }

        @Override
        public ResponseGetOpportunityDetails[] newArray(int size) {
            return new ResponseGetOpportunityDetails[size];
        }
    };
}
