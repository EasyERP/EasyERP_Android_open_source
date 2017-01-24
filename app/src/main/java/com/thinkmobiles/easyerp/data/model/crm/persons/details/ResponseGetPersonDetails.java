package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import android.os.Parcel;
import android.os.Parcelable;

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

public class ResponseGetPersonDetails implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.fullName);
        dest.writeString(this.imageSrc);
        dest.writeString(this.email);
        dest.writeString(this.jobPosition);
        dest.writeString(this.skype);
        dest.writeString(this.dateBirth);
        dest.writeString(this.type);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeParcelable(this.social, flags);
        dest.writeParcelable(this.salesPurchases, flags);
        dest.writeParcelable(this.phones, flags);
        dest.writeParcelable(this.shippingAddress, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.company, flags);
        dest.writeParcelable(this.name, flags);
        dest.writeTypedList(this.opportunities);
    }

    public ResponseGetPersonDetails() {
    }

    protected ResponseGetPersonDetails(Parcel in) {
        this.id = in.readString();
        this.fullName = in.readString();
        this.imageSrc = in.readString();
        this.email = in.readString();
        this.jobPosition = in.readString();
        this.skype = in.readString();
        this.dateBirth = in.readString();
        this.type = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.salesPurchases = in.readParcelable(SalesPurchases.class.getClassLoader());
        this.phones = in.readParcelable(Phone.class.getClassLoader());
        this.shippingAddress = in.readParcelable(Address.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.company = in.readParcelable(Company.class.getClassLoader());
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.opportunities = in.createTypedArrayList(OpportunityItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetPersonDetails> CREATOR = new Parcelable.Creator<ResponseGetPersonDetails>() {
        @Override
        public ResponseGetPersonDetails createFromParcel(Parcel source) {
            return new ResponseGetPersonDetails(source);
        }

        @Override
        public ResponseGetPersonDetails[] newArray(int size) {
            return new ResponseGetPersonDetails[size];
        }
    };
}
