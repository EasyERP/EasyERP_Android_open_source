package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NextAction;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Social;
import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.ExpectedRevenue;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/24/2017.
 */

public class OpportunityItem implements Parcelable {
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

    public OpportunityItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.skype);
        dest.writeParcelable(this.social, flags);
        dest.writeString(this.projectType);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeString(this.convertedDate);
        dest.writeByte(this.isConverted ? (byte) 1 : (byte) 0);
        dest.writeString(this.source);
        dest.writeString(this.campaign);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeInt(this.sequence);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.workflow, flags);
        dest.writeString(this.reffered);
        dest.writeByte(this.optout ? (byte) 1 : (byte) 0);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeString(this.color);
        dest.writeParcelable(this.categories, flags);
        dest.writeString(this.priority);
        dest.writeString(this.expectedClosing);
        dest.writeParcelable(this.nextAction, flags);
        dest.writeString(this.internalNotes);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeString(this.func);
        dest.writeParcelable(this.phones, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.contactName, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeStringList(this.tags);
        dest.writeString(this.customer);
        dest.writeString(this.company);
        dest.writeString(this.tempCompanyField);
        dest.writeString(this.creationDate);
        dest.writeString(this.jobPosition);
        dest.writeParcelable(this.expectedRevenue, flags);
        dest.writeString(this.name);
        dest.writeByte(this.isOpportunitie ? (byte) 1 : (byte) 0);
    }

    protected OpportunityItem(Parcel in) {
        this._id = in.readString();
        this.skype = in.readString();
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.projectType = in.readString();
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.convertedDate = in.readString();
        this.isConverted = in.readByte() != 0;
        this.source = in.readString();
        this.campaign = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.sequence = in.readInt();
        this.whoCanRW = in.readString();
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.reffered = in.readString();
        this.optout = in.readByte() != 0;
        this.active = in.readByte() != 0;
        this.color = in.readString();
        this.categories = in.readParcelable(Categories.class.getClassLoader());
        this.priority = in.readString();
        this.expectedClosing = in.readString();
        this.nextAction = in.readParcelable(NextAction.class.getClassLoader());
        this.internalNotes = in.readString();
        this.salesPerson = in.readParcelable(SalesPerson.class.getClassLoader());
        this.func = in.readString();
        this.phones = in.readParcelable(Phone.class.getClassLoader());
        this.email = in.readString();
        this.contactName = in.readParcelable(Name.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.customer = in.readString();
        this.company = in.readString();
        this.tempCompanyField = in.readString();
        this.creationDate = in.readString();
        this.jobPosition = in.readString();
        this.expectedRevenue = in.readParcelable(ExpectedRevenue.class.getClassLoader());
        this.name = in.readString();
        this.isOpportunitie = in.readByte() != 0;
    }

    public static final Creator<OpportunityItem> CREATOR = new Creator<OpportunityItem>() {
        @Override
        public OpportunityItem createFromParcel(Parcel source) {
            return new OpportunityItem(source);
        }

        @Override
        public OpportunityItem[] newArray(int size) {
            return new OpportunityItem[size];
        }
    };
}
