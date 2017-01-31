package com.thinkmobiles.easyerp.data.model.crm.orders.detail;


import android.os.Parcel;
import android.os.Parcelable;

public class PaymentMethod implements Parcelable {

    /**
     *
     "paymentMethod": {
     "_id": "575fb4c97330dff16a34038d",
     "name": "OTP",
     "account": "OTP USD",
     "bank": "OTP",
     "swiftCode": "",
     "address": "",
     "owner": ""
     },
     */

    public String _id;
    public String name;
    public String account;
    public String bank;
    public String swiftCode;
    public String address;
    public String owner;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.account);
        dest.writeString(this.bank);
        dest.writeString(this.swiftCode);
        dest.writeString(this.address);
        dest.writeString(this.owner);
    }

    public PaymentMethod() {
    }

    protected PaymentMethod(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.account = in.readString();
        this.bank = in.readString();
        this.swiftCode = in.readString();
        this.address = in.readString();
        this.owner = in.readString();
    }

    public static final Parcelable.Creator<PaymentMethod> CREATOR = new Parcelable.Creator<PaymentMethod>() {
        @Override
        public PaymentMethod createFromParcel(Parcel source) {
            return new PaymentMethod(source);
        }

        @Override
        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }
    };
}
