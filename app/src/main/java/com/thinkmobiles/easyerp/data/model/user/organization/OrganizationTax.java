package com.thinkmobiles.easyerp.data.model.user.organization;


import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

public class OrganizationTax implements Parcelable {

    /**
     * carriedTax: {
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
     */

    public String _id;
    public int code;
    public String type;
    public CreatedEditedBy createdBy;
    public CreatedEditedUserString editedBy;
//    public Object payMethod;
    public boolean budgeted;
    public String category;
//    public Object subAccount;
    public String name;
    public String account;


    public OrganizationTax() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeInt(this.code);
        dest.writeString(this.type);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeByte(this.budgeted ? (byte) 1 : (byte) 0);
        dest.writeString(this.category);
        dest.writeString(this.name);
        dest.writeString(this.account);
    }

    protected OrganizationTax(Parcel in) {
        this._id = in.readString();
        this.code = in.readInt();
        this.type = in.readString();
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.budgeted = in.readByte() != 0;
        this.category = in.readString();
        this.name = in.readString();
        this.account = in.readString();
    }

    public static final Creator<OrganizationTax> CREATOR = new Creator<OrganizationTax>() {
        @Override
        public OrganizationTax createFromParcel(Parcel source) {
            return new OrganizationTax(source);
        }

        @Override
        public OrganizationTax[] newArray(int size) {
            return new OrganizationTax[size];
        }
    };
}
