package com.thinkmobiles.easyerp.data.model.crm.leads.details;

import android.os.Parcel;
import android.os.Parcelable;


public class LeadCompanyInfo implements Parcelable {

    /**
     * "companyInfo": {
        "industry": null
     },
     */

    public String industry;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.industry);
    }

    public LeadCompanyInfo() {
    }

    protected LeadCompanyInfo(Parcel in) {
        this.industry = in.readString();
    }

    public static final Parcelable.Creator<LeadCompanyInfo> CREATOR = new Parcelable.Creator<LeadCompanyInfo>() {
        @Override
        public LeadCompanyInfo createFromParcel(Parcel source) {
            return new LeadCompanyInfo(source);
        }

        @Override
        public LeadCompanyInfo[] newArray(int size) {
            return new LeadCompanyInfo[size];
        }
    };
}
