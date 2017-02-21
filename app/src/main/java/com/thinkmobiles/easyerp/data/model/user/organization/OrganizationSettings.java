package com.thinkmobiles.easyerp.data.model.user.organization;


import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.invoice.CurrencyID;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;

public class OrganizationSettings implements Parcelable {

    /**
     * {
     url: {
     _id: "581b46939a01c15a2e3a5302",
     startDate: "2014-08-01T00:00:00.000Z",
     name: "EasyERP Inc",
     __v: 0,
     discount: null,
     shipping: null,
     carriedTax: {
     _id: "565eb53a6aa50532e5df0bdd",
     code: 111200,
     type: "57da4d62713d3fe825f074bd",
     createdBy: {
     date: "2017-02-01T10:18:31.256Z",
     user: null
     },
     editedBy: {
     user: null
     },
     payMethod: null,
     budgeted: false,
     category: "5810c75b2b225158086d7f88",
     subAccount: null,
     name: "111200 Tax Received",
     account: "Tax Received"
     },
     payableTax: null,
     purchaseTax: {
     _id: "565eb53a6aa50532e5df0bdd",
     code: 111200,
     type: "57da4d62713d3fe825f074bd",
     createdBy: {
     date: "2017-02-01T10:18:31.255Z",
     user: null
     },
     editedBy: {
     user: null
     },
     payMethod: null,
     budgeted: false,
     category: "5810c75b2b225158086d7f88",
     subAccount: null,
     name: "111200 Tax Received",
     account: "Tax Received"
     },
     salesTax: {
     _id: "565eb53a6aa50532e5df0bc8",
     code: 100000,
     type: "57da4d62713d3fe825f074b2",
     parent: "5810c75b2b225158086d7f82",
     createdBy: {
     date: "2017-02-01T10:18:31.259Z",
     user: null
     },
     editedBy: {
     date: "2015-12-02T14:19:59.504Z",
     user: "52203e707d4dba8813000003"
     },
     payMethod: null,
     budgeted: false,
     category: "5810c75b2b225158086d7f82",
     subAccount: null,
     name: "100000 Fixed Asset Account",
     account: "Fixed Asset Account"
     },
     contactName: "Test",
     user: null,
     language: null,
     defaultEmail: false,
     contact: {
     _id: "52203e707d4dba8813000003",
     __v: 0,
     attachments: [ ],
     lastAccess: "2016-09-21T10:36:43.588Z",
     profile: 1387275598000,
     imports: {
     importedCount: 0,
     conflictedItems: [ ],
     skipped: [ ],
     delimiter: ",",
     comparingField: "",
     type: "",
     stage: 1,
     filePath: "",
     fileName: ""
     },
     relatedEmployee: "55b92ad221e4b7c40f00004f",
     savedFilters: [ ],
     kanbanSettings: {
     tasks: {
     foldWorkflows: [
     "528ce3caf3f67bc40b000013",
     "528ce3acf3f67bc40b000012",
     "528ce30cf3f67bc40b00000f",
     "528ce35af3f67bc40b000010"
     ],
     countPerPage: 10
     },
     applications: {
     foldWorkflows: [
     "Empty"
     ],
     countPerPage: 10
     },
     opportunities: {
     foldWorkflows: [
     "528cdef4f3f67bc40b00000a",
     "528cdf1cf3f67bc40b00000b"
     ],
     countPerPage: 40
     }
     },
     credentials: {
     access_token: "",
     refresh_token: ""
     },
     pass: "1a3ec659aab157754bd3dcc4f09d6773ec0b4f056c44342b9026d144ba7909a8",
     email: "info@thinkmobiles.com",
     login: "admin",
     imageSrc: "url:image/jpeg;base64,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"
     },
     industry: {
     _id: "574c54e22b7598157b94f10f",
     name: "Alternative Dispute Resolution",
     __v: 0
     },
     currency: {
     _id: "USD",
     active: true,
     decPlace: 2,
     symbol: "$",
     name: "USD"
     },
     website: "www.google.ua",
     phone: "+38 096 709 86 15",
     imageSrc: "url:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAAAAACPAi4CAAAACXBIWXMAAABIAAAASABGyWs+AAAACXZwQWcAAABAAAAAQADq8/hgAAAEaElEQVRYw82X6XLbNhCA+f4PVomk5MRyHDtp63oEgDcl3vfRBQhQIEVKSvsnO+OxRBEfFnthV+n/pyi/NaCryzzL8rJu/wOgzQPXJBgjhDExnXPW/Aqgy30DI0yIwYQQ4Bhe2j0I6BIbI1jL9meC2TdkRu0jgMxCGN5H2HT8IIzjKPAdE9NngEjuAhqfv3rOpe3aIrDAFoB1qtuA3ADlMXKuz9vlLqZokt4CxPAOQXa2bPDCRVSJYB0QIDA4ibp+TVKDbuCvAeh6YpX9DWkcUGJCkAARXW9UfXeL0PmUcF4CZBA4cALv5nqQM+yD4mtATQMOGMi9RzghiKriCuBiAzsB1e8uwUUGtroZIAEsqfqHCI2JjdGZHNDSZzHYb0boQK4JOTVXNQFEoJXDPskEvrYTrJHgIwOdZEBrggXzfkbo+sY7Hp0Fx9bUYbUEAAtgV/waHAcCnOew3arbLy5lVXGSXIrKGQkrKKMLcnHsPjEGAla1PYi+/YCV37e7DRp1qUDjwREK1wjbo56hezRoPLxt9lzUg+m96Hvtz3BMcU9syQAxKBSJ/c2Nqv0Em5C/97q+BdGoEuoORN98CkAqzsAAPh690vdv2tOOEcx/dodP0zq+qjpoQQF7/Vno2UA0OgLQQbUZI6t/1+BlRgAlyywvqtNXja0HFQ7jGVwoUA0HUBNcMvRdpW8PpzDPYRAERfmNE/TDuE8Ajis4oJAiUwB2+g+am3YEEmT5kz4HgOdRygHUIPEMsFf/YvXJYoSKbPczQI4HwysSbKKBdk4dLAhJsptrUHK1lSERUDYD6E9pGLsjoXzRZgAIJVaYBCCfA57zMBoJYfV9CXDigHhRgww2Hgngh4UjnCUbJAs2CEdCkl25kbou5ABh0KkXPupA6IB8fOUF4TpFOs5Eg50eFSOBfOz0GYCWoJwDoJzwcjQBfM2rMAjD0CEsL/Qp4ISG/FHkuJ4A9toXv66KomosMMNAuAA6GxOWPwqP64sb3kTm7HX1Fbsued9BXjACZKNIphLz/FF4WIps6vqff+jaIFAONiBbTf1hDITti5RLg+cYoDOxqJFwxb0dXmT5Bn/Pn8wOh9dQnMASK4aaSGuk+G24DObCbm5XzkXs9RdASTuytUZO6Czdm2BCA2cSgNbIWedxk0AV4FVYEYFJpLK4SuA3DrsceQEQl6svXy33CKfxIrwAanqZBA8R4AAQWeUMwJ6CZ7t7BIh6utfos0uLwxqP7BECMaTUuQCoawhO+9sSUWtjs1kA9I1Fm8DoNiCl64nUCsp9Ym1SgncjoLoz7YTl9dNOtbGRYSAjWbMDNPKw3py0otNeufVYN2wvzha5g6iGzlTDebsfEdbtW9EsLOvYZs06Dmbsq4GjcoeBgThBWtRN2zZ1mYUuGZ7axfz9hZEns+mMQ+ckzIYm/gn+WQvWWRq6uoxuSNi4RWWAYGfRuCtjXx25Bh25MGaTFzaccCVX1wfPtkiCk+e6nh/ExXps/N6z80PyL8wPTYgPwzDiAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDExLTAxLTE5VDAzOjU5OjAwKzAxOjAwaFry6QAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxMC0xMi0yMVQxNDozMDo0NCswMTowMGxOe/8AAAAZdEVYdFNvZnR3YXJlAEFkb2JlIEltYWdlUmVhZHlxyWU8AAAAAElFTkSuQmCC",
     address: {
     country: "Ukraine",
     fax: "",
     zip: "88000",
     state: "Transcarpation",
     city: "47 Shandora Petefi sq., 7th floor",
     street: "Uzhhorod"
     }
     }
     }
     */

    public String _id;
    public String startDate;
    public String name;
//    public Double discount;
//    public Double shipping;
    public OrganizationTax carriedTax;
    public OrganizationTax payableTax;
    public OrganizationTax purchaseTax;
    public OrganizationTax salesTax;
    public String contactName;
//    public Object user;
//    public Object language;
    public boolean defaultEmail;
    public OrganizationContact contact;
    public FilterItem industry;
    public CurrencyID currency;
    public String website;
    public String phone;
    public String imageSrc;
    public Address address;


    public OrganizationSettings() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.startDate);
        dest.writeString(this.name);
        dest.writeParcelable(this.carriedTax, flags);
        dest.writeParcelable(this.payableTax, flags);
        dest.writeParcelable(this.purchaseTax, flags);
        dest.writeParcelable(this.salesTax, flags);
        dest.writeString(this.contactName);
        dest.writeByte(this.defaultEmail ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.contact, flags);
        dest.writeParcelable(this.industry, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeString(this.website);
        dest.writeString(this.phone);
        dest.writeString(this.imageSrc);
        dest.writeParcelable(this.address, flags);
    }

    protected OrganizationSettings(Parcel in) {
        this._id = in.readString();
        this.startDate = in.readString();
        this.name = in.readString();
        this.carriedTax = in.readParcelable(OrganizationTax.class.getClassLoader());
        this.payableTax = in.readParcelable(OrganizationTax.class.getClassLoader());
        this.purchaseTax = in.readParcelable(OrganizationTax.class.getClassLoader());
        this.salesTax = in.readParcelable(OrganizationTax.class.getClassLoader());
        this.contactName = in.readString();
        this.defaultEmail = in.readByte() != 0;
        this.contact = in.readParcelable(OrganizationContact.class.getClassLoader());
        this.industry = in.readParcelable(FilterItem.class.getClassLoader());
        this.currency = in.readParcelable(CurrencyID.class.getClassLoader());
        this.website = in.readString();
        this.phone = in.readString();
        this.imageSrc = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<OrganizationSettings> CREATOR = new Creator<OrganizationSettings>() {
        @Override
        public OrganizationSettings createFromParcel(Parcel source) {
            return new OrganizationSettings(source);
        }

        @Override
        public OrganizationSettings[] newArray(int size) {
            return new OrganizationSettings[size];
        }
    };
}
