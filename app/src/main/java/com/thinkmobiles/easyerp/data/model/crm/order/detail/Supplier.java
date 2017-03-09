package com.thinkmobiles.easyerp.data.model.crm.order.detail;

import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;


public class Supplier extends SalesPerson {

    /**
     * supplier: {
     _id: "57ced67ceab5298410a81098",
     __v: 0,
     channel: null,
     integrationId: "",
     companyInfo: {
     industry: null
     },
     editedBy: {
     date: "2016-09-06T14:55:34.559Z",
     user: "57ce822ceab5298410a81010"
     },
     createdBy: {
     date: "2016-09-06T14:45:16.491Z",
     user: "57ce822ceab5298410a81010"
     },
     history: [ ],
     attachments: [ ],
     notes: [ ],
     groups: {
     group: [ ],
     users: [ ],
     owner: null
     },
     whoCanRW: "everyOne",
     social: {
     LI: "https://www.[].com/netmedia",
     FB: "https://facebook.com/netmedia"
     },
     color: "#4d5a75",
     relatedUser: null,
     salesPurchases: {
     receiveMessages: 0,
     language: "English",
     reference: "",
     active: true,
     implementedBy: null,
     salesTeam: null,
     salesPerson: "57cd4676e46029380f3cb403",
     isSupplier: false,
     isCustomer: true
     },
     title: "",
     internalNotes: "",
     contacts: [ ],
     phones: {
     fax: "",
     mobile: "+1 310-191-1266",
     phone: "+1 310-285-1000"
     },
     skype: "",
     jobPosition: "",
     website: "www.netmedia.com",
     shippingAddress: {
     name: "",
     country: "",
     zip: "",
     state: "",
     city: "",
     street: ""
     },
     address: {
     country: "United States",
     zip: "90011",
     state: "California",
     city: "Los Angeles",
     street: "2000 Alameda St, Los Angeles, CA 90058, United States"
     },
     timezone: "UTC",
     department: null,
     company: null,
     email: "info@netmedia.com",
     imageSrc: "data:image/jpeg;base64,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",
     name: {
     last: "",
     first: "NetMedia"
     },
     isHidden: false,
     isOwn: false,
     type: "Company",
     fullName: "NetMedia ",
     id: "57ced67ceab5298410a81098"
     },
     */
    public Address address;
    public String email;


    public Supplier() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.email);
    }

    protected Supplier(Parcel in) {
        super(in);
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Creator<Supplier> CREATOR = new Creator<Supplier>() {
        @Override
        public Supplier createFromParcel(Parcel source) {
            return new Supplier(source);
        }

        @Override
        public Supplier[] newArray(int size) {
            return new Supplier[size];
        }
    };
}
