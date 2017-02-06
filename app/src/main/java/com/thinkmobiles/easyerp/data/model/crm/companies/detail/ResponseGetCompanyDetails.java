package com.thinkmobiles.easyerp.data.model.crm.companies.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPurchases;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Social;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.OpportunityItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/2/2017.
 */

public class ResponseGetCompanyDetails implements Parcelable {
    public String id;
    public CreatedEditedBy editedBy;
    public CreatedEditedBy createdBy;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public Social social;
    public SalesPurchases salesPurchases;
    public ArrayList<Customer> contacts;
    public Phone phones;
    public String skype;
    public String jobPosition;
    public String website;
    public Address shippingAddress;
    public Address address;
    public String email;
    public String imageSrc;
    public Name name;
    public String fullName;
    public ArrayList<OpportunityItem> opportunities;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeParcelable(this.social, flags);
        dest.writeParcelable(this.salesPurchases, flags);
        dest.writeTypedList(this.contacts);
        dest.writeParcelable(this.phones, flags);
        dest.writeString(this.skype);
        dest.writeString(this.jobPosition);
        dest.writeString(this.website);
        dest.writeParcelable(this.shippingAddress, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.email);
        dest.writeString(this.imageSrc);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.fullName);
        dest.writeTypedList(this.opportunities);
    }

    public ResponseGetCompanyDetails() {
    }

    protected ResponseGetCompanyDetails(Parcel in) {
        this.id = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.salesPurchases = in.readParcelable(SalesPurchases.class.getClassLoader());
        this.contacts = in.createTypedArrayList(Customer.CREATOR);
        this.phones = in.readParcelable(Phone.class.getClassLoader());
        this.skype = in.readString();
        this.jobPosition = in.readString();
        this.website = in.readString();
        this.shippingAddress = in.readParcelable(Address.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.email = in.readString();
        this.imageSrc = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.fullName = in.readString();
        this.opportunities = in.createTypedArrayList(OpportunityItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetCompanyDetails> CREATOR = new Parcelable.Creator<ResponseGetCompanyDetails>() {
        @Override
        public ResponseGetCompanyDetails createFromParcel(Parcel source) {
            return new ResponseGetCompanyDetails(source);
        }

        @Override
        public ResponseGetCompanyDetails[] newArray(int size) {
            return new ResponseGetCompanyDetails[size];
        }
    };
}
