package com.thinkmobiles.easyerp.data.model.crm.leads.details;

import android.os.Parcel;
import android.os.Parcelable;


public class LeadPhone implements Parcelable {

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

    public LeadPhone() {
    }

    protected LeadPhone(Parcel in) {
        this.fax = in.readString();
        this.phone = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<LeadPhone> CREATOR = new Parcelable.Creator<LeadPhone>() {
        @Override
        public LeadPhone createFromParcel(Parcel source) {
            return new LeadPhone(source);
        }

        @Override
        public LeadPhone[] newArray(int size) {
            return new LeadPhone[size];
        }
    };
}
