package com.thinkmobiles.easyerp.data.model.crm.leads.detail;

import android.os.Parcel;
import android.os.Parcelable;


public class CompanyInfo implements Parcelable {

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

    public CompanyInfo() {
    }

    protected CompanyInfo(Parcel in) {
        this.industry = in.readString();
    }

    public static final Parcelable.Creator<CompanyInfo> CREATOR = new Parcelable.Creator<CompanyInfo>() {
        @Override
        public CompanyInfo createFromParcel(Parcel source) {
            return new CompanyInfo(source);
        }

        @Override
        public CompanyInfo[] newArray(int size) {
            return new CompanyInfo[size];
        }
    };
}
