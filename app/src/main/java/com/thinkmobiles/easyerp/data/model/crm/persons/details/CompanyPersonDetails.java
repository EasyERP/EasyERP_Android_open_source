package com.thinkmobiles.easyerp.data.model.crm.persons.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;

/**
 * Created by Lynx on 1/25/2017.
 */

public class CompanyPersonDetails implements Parcelable {

    public Phone phones;
    public Address address;
    public String email;
    public String imageSrc;
    public String fullName;
    public String website;
    public String id;

    public CompanyPersonDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.phones, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.email);
        dest.writeString(this.imageSrc);
        dest.writeString(this.fullName);
        dest.writeString(this.website);
        dest.writeString(this.id);
    }

    protected CompanyPersonDetails(Parcel in) {
        this.phones = in.readParcelable(Phone.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.email = in.readString();
        this.imageSrc = in.readString();
        this.fullName = in.readString();
        this.website = in.readString();
        this.id = in.readString();
    }

    public static final Creator<CompanyPersonDetails> CREATOR = new Creator<CompanyPersonDetails>() {
        @Override
        public CompanyPersonDetails createFromParcel(Parcel source) {
            return new CompanyPersonDetails(source);
        }

        @Override
        public CompanyPersonDetails[] newArray(int size) {
            return new CompanyPersonDetails[size];
        }
    };
}
