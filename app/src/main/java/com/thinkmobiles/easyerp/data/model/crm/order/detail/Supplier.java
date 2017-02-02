package com.thinkmobiles.easyerp.data.model.crm.order.detail;

import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;


public class Supplier extends SalesPerson {

    public Address address;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.address, flags);
    }

    public Supplier() {
    }

    protected Supplier(Parcel in) {
        super(in);
        this.address = in.readParcelable(Address.class.getClassLoader());
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
