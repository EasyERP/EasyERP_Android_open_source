package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;


import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;

import java.util.ArrayList;

public class Company implements Parcelable {

    //
    /**
     * duplicate of {@link LeadCustomer}
     * "company": {
        "_id": "5877a4243336a81d081bd4a9",
        "__v": 0,
        "channel": null,
        "integrationId": "",
        "companyInfo": {
            "industry": null
        },
     */

    public String _id;
    public int __v;
    public String channel;
    public String integrationId;
    public CompanyInfo companyInfo;
    public EditedBy editedBy;
    public EditedBy createdBy;
    public ArrayList<NoteHistory> history;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public Groups groups;
    public String whoCanRW;
    public Social social;
    public String color; //"#4d5a75"
    public User relatedUser;
    public SalesPurchases salesPurchases;
    public String title;
    public String internalNotes;
//    public ArrayList<> contacts;
    public Phone phones;
    public String skype;
    public String jobPosition;
    public String website;
    public Address shippingAddress;
    public Address address;
    public String timezone;
//    public String department;
//    public String company;
    public String email;
    public String imageSrc;
    public Name name;
    public boolean isHidden;
    public boolean isOwn;
    public String type;
    public String fullName;
    public String id;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeInt(this.__v);
        dest.writeString(this.channel);
        dest.writeString(this.integrationId);
        dest.writeParcelable(this.companyInfo, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeTypedList(this.history);
        dest.writeTypedList(this.attachments);
        dest.writeTypedList(this.notes);
        dest.writeParcelable(this.groups, flags);
        dest.writeString(this.whoCanRW);
        dest.writeParcelable(this.social, flags);
        dest.writeString(this.color);
        dest.writeParcelable(this.relatedUser, flags);
        dest.writeParcelable(this.salesPurchases, flags);
        dest.writeString(this.title);
        dest.writeString(this.internalNotes);
        dest.writeParcelable(this.phones, flags);
        dest.writeString(this.skype);
        dest.writeString(this.jobPosition);
        dest.writeString(this.website);
        dest.writeParcelable(this.shippingAddress, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.timezone);
        dest.writeString(this.email);
        dest.writeString(this.imageSrc);
        dest.writeParcelable(this.name, flags);
        dest.writeByte(this.isHidden ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOwn ? (byte) 1 : (byte) 0);
        dest.writeString(this.type);
        dest.writeString(this.fullName);
        dest.writeString(this.id);
    }

    public Company() {
    }

    protected Company(Parcel in) {
        this._id = in.readString();
        this.__v = in.readInt();
        this.channel = in.readString();
        this.integrationId = in.readString();
        this.companyInfo = in.readParcelable(CompanyInfo.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.history = in.createTypedArrayList(NoteHistory.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.groups = in.readParcelable(Groups.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.color = in.readString();
        this.relatedUser = in.readParcelable(User.class.getClassLoader());
        this.salesPurchases = in.readParcelable(SalesPurchases.class.getClassLoader());
        this.title = in.readString();
        this.internalNotes = in.readString();
        this.phones = in.readParcelable(Phone.class.getClassLoader());
        this.skype = in.readString();
        this.jobPosition = in.readString();
        this.website = in.readString();
        this.shippingAddress = in.readParcelable(Address.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.timezone = in.readString();
        this.email = in.readString();
        this.imageSrc = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.isHidden = in.readByte() != 0;
        this.isOwn = in.readByte() != 0;
        this.type = in.readString();
        this.fullName = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}
