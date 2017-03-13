package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class ChannelResult implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String lastSync;
    public boolean connected;
    public BankAccount bankAccount;
//    public PriceListInfo priceList;
    public String consumerSecret;
    public String consumerKey;
    public String secret;
    public String token;
    public boolean active;
    public boolean updateShippingMethod;
    public boolean updateShippingStatus;
    public String baseUrl;
    public String password;
    public String username;
    public String user;
    public String type;
    public String dbName;
    public String channelName;
    public WarehouseSettings warehouseSettings;


    public ChannelResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.lastSync);
        dest.writeByte(this.connected ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.bankAccount, flags);
        dest.writeString(this.consumerSecret);
        dest.writeString(this.consumerKey);
        dest.writeString(this.secret);
        dest.writeString(this.token);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeByte(this.updateShippingMethod ? (byte) 1 : (byte) 0);
        dest.writeByte(this.updateShippingStatus ? (byte) 1 : (byte) 0);
        dest.writeString(this.baseUrl);
        dest.writeString(this.password);
        dest.writeString(this.username);
        dest.writeString(this.user);
        dest.writeString(this.type);
        dest.writeString(this.dbName);
        dest.writeString(this.channelName);
        dest.writeParcelable(this.warehouseSettings, flags);
    }

    protected ChannelResult(Parcel in) {
        this.id = in.readString();
        this.lastSync = in.readString();
        this.connected = in.readByte() != 0;
        this.bankAccount = in.readParcelable(BankAccount.class.getClassLoader());
        this.consumerSecret = in.readString();
        this.consumerKey = in.readString();
        this.secret = in.readString();
        this.token = in.readString();
        this.active = in.readByte() != 0;
        this.updateShippingMethod = in.readByte() != 0;
        this.updateShippingStatus = in.readByte() != 0;
        this.baseUrl = in.readString();
        this.password = in.readString();
        this.username = in.readString();
        this.user = in.readString();
        this.type = in.readString();
        this.dbName = in.readString();
        this.channelName = in.readString();
        this.warehouseSettings = in.readParcelable(WarehouseSettings.class.getClassLoader());
    }

    public static final Creator<ChannelResult> CREATOR = new Creator<ChannelResult>() {
        @Override
        public ChannelResult createFromParcel(Parcel source) {
            return new ChannelResult(source);
        }

        @Override
        public ChannelResult[] newArray(int size) {
            return new ChannelResult[size];
        }
    };
}
