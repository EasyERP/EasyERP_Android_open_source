package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class OrderTaxCode implements Parcelable {

    /**
     * "taxCode": {
     "_id": "5889f706a83fa1b318c61fee",
     "rate": 0.1577,
     "fullName": "TST Test Demo tax 15.77%"
     }
     */

    public String _id;
    public Double rate;
    public String fullName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeValue(this.rate);
        dest.writeString(this.fullName);
    }

    public OrderTaxCode() {
    }

    protected OrderTaxCode(Parcel in) {
        this._id = in.readString();
        this.rate = (Double) in.readValue(Double.class.getClassLoader());
        this.fullName = in.readString();
    }

    public static final Parcelable.Creator<OrderTaxCode> CREATOR = new Parcelable.Creator<OrderTaxCode>() {
        @Override
        public OrderTaxCode createFromParcel(Parcel source) {
            return new OrderTaxCode(source);
        }

        @Override
        public OrderTaxCode[] newArray(int size) {
            return new OrderTaxCode[size];
        }
    };
}
