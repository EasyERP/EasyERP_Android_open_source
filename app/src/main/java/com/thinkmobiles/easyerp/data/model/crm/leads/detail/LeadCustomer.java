package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

import java.util.ArrayList;

public class LeadCustomer implements Parcelable {

    /**
     * "customer": {
     "_id": "57ce6557a3c7f97535e33da5",
     "dateBirth": "",
     "__v": 0,
     "channel": null,
     "integrationId": "",
     "companyInfo": {
     "industry": null
     },
     "editedBy": {
     "date": "2016-09-06T06:42:31.976Z",
     "user": "577a78347134263421cab3ed"
     },
     "createdBy": {
     "date": "2016-09-06T06:42:31.976Z",
     "user": "577a78347134263421cab3ed"
     },
     "history": [],
     "attachments": [],
     "notes": [],
     "groups": {
     "group": [],
     "users": [],
     "owner": null
     },
     "whoCanRW": "everyOne",
     "social": {
     "LI": "",
     "FB": ""
     },
     "color": "#4d5a75",
     "relatedUser": null,
     "salesPurchases": {
     "receiveMessages": 0,
     "language": "English",
     "reference": "",
     "active": false,
     "implementedBy": null,
     "salesTeam": null,
     "salesPerson": null,
     "isSupplier": false,
     "isCustomer": true
     },
     "title": "",
     "internalNotes": "",
     "contacts": [],
     "phones": {
     "fax": "",
     "mobile": "",
     "phone": ""
     },
     "skype": "aaronius0000",
     "jobPosition": "CEO",
     "website": "",
     "shippingAddress": {
     "name": "",
     "country": "",
     "zip": "",
     "state": "",
     "city": "",
     "street": ""
     },
     "address": {
     "country": "Select",
     "zip": "",
     "state": "",
     "city": "",
     "street": ""
     },
     "timezone": "UTC",
     "department": null,
     "company": null,
     "email": "",
     "imageSrc": "url:image/png;base64",
     "name": {
     "last": "Hurst",
     "first": "Aaron"
     },
     "isHidden": false,
     "isOwn": false,
     "type": "Person",
     "fullName": "Aaron Hurst",
     "id": "57ce6557a3c7f97535e33da5"
     },
     */

    public String _id;
    public String dateBirth;
    public int __v;
//    public String channel;
    public String integrationId;
    public CompanyInfo companyInfo;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public ArrayList<NoteHistory> history;
    public ArrayList<AttachmentItem> attachments;
    public ArrayList<NoteItem> notes;
    public Groups groups;
    public String whoCanRW;
    public Social social;
    public String color;
//    public User relatedUser;
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


    public LeadCustomer() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.dateBirth);
        dest.writeInt(this.__v);
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

    protected LeadCustomer(Parcel in) {
        this._id = in.readString();
        this.dateBirth = in.readString();
        this.__v = in.readInt();
        this.integrationId = in.readString();
        this.companyInfo = in.readParcelable(CompanyInfo.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.history = in.createTypedArrayList(NoteHistory.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.notes = in.createTypedArrayList(NoteItem.CREATOR);
        this.groups = in.readParcelable(Groups.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.social = in.readParcelable(Social.class.getClassLoader());
        this.color = in.readString();
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

    public static final Creator<LeadCustomer> CREATOR = new Creator<LeadCustomer>() {
        @Override
        public LeadCustomer createFromParcel(Parcel source) {
            return new LeadCustomer(source);
        }

        @Override
        public LeadCustomer[] newArray(int size) {
            return new LeadCustomer[size];
        }
    };
}
