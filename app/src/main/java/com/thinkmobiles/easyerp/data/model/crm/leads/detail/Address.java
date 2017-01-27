package com.thinkmobiles.easyerp.data.model.crm.leads.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    /**
     * "address": {
     "country": "Afghanistan",
     "zip": "75446",
     "state": "Transcarpathia",
     "city": "Birmingham",
     "street": "Notsafe str. 6"
     },
     */

    public String name;
    public String country;
    public String zip;
    public String state;
    public String city;
    public String street;


    public Address() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.country);
        dest.writeString(this.zip);
        dest.writeString(this.state);
        dest.writeString(this.city);
        dest.writeString(this.street);
    }

    protected Address(Parcel in) {
        this.name = in.readString();
        this.country = in.readString();
        this.zip = in.readString();
        this.state = in.readString();
        this.city = in.readString();
        this.street = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
