package com.thinkmobiles.easyerp.data.model.inventory.product.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class BankAccount implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String account;
    public String address;
    public String bank;
    public String chartAccount;
    public String currency;
    public String fullName;
    public String name;
    public String owner;
    public String swiftCode;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.account);
        dest.writeString(this.address);
        dest.writeString(this.bank);
        dest.writeString(this.chartAccount);
        dest.writeString(this.currency);
        dest.writeString(this.fullName);
        dest.writeString(this.name);
        dest.writeString(this.owner);
        dest.writeString(this.swiftCode);
    }

    public BankAccount() {
    }

    protected BankAccount(Parcel in) {
        this.id = in.readString();
        this.account = in.readString();
        this.address = in.readString();
        this.bank = in.readString();
        this.chartAccount = in.readString();
        this.currency = in.readString();
        this.fullName = in.readString();
        this.name = in.readString();
        this.owner = in.readString();
        this.swiftCode = in.readString();
    }

    public static final Parcelable.Creator<BankAccount> CREATOR = new Parcelable.Creator<BankAccount>() {
        @Override
        public BankAccount createFromParcel(Parcel source) {
            return new BankAccount(source);
        }

        @Override
        public BankAccount[] newArray(int size) {
            return new BankAccount[size];
        }
    };
}
