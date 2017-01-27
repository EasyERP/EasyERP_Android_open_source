package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


public class Phone implements Parcelable {

    /**
     *
     "phones": {
     "fax": "",
     "phone": "+380507770000",
     "mobile": ""
     },
     */

    public String fax;
    public String phone;
    public String mobile;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fax);
        dest.writeString(this.phone);
        dest.writeString(this.mobile);
    }

    public Phone() {
    }

    protected Phone(Parcel in) {
        this.fax = in.readString();
        this.phone = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
