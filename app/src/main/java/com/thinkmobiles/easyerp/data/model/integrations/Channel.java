package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class Channel implements Parcelable {

    @SerializedName("_id")
    public String id;
    public boolean active;
    public BankAccount bankAccount;
    public String baseUrl;
    public String channelName;
    public boolean connected;
    public String consumerKey;
    public String consumerSecret;
    public String dbName;
    public String password;
    public PriceList priceList;
    public String secret;
    public FilterItem shippingMethod;
    public String token;
    public String type;
    public boolean updateShippingMethod;
    public boolean updateShippingStatus;
    public String user;
    public String username;
    public WarehouseSettings warehouseSettings;

    public int conflictProducts;
    public int importedOrders;
    public int importedProducts;
    public int unlinkedOrders;

    public ChannelType getChannelType() {
        return ChannelType.valueOf(type.toUpperCase());
    }

    public Channel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.active ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.bankAccount, flags);
        dest.writeString(this.baseUrl);
        dest.writeString(this.channelName);
        dest.writeByte(this.connected ? (byte) 1 : (byte) 0);
        dest.writeString(this.consumerKey);
        dest.writeString(this.consumerSecret);
        dest.writeString(this.dbName);
        dest.writeString(this.password);
        dest.writeParcelable(this.priceList, flags);
        dest.writeString(this.secret);
        dest.writeParcelable(this.shippingMethod, flags);
        dest.writeString(this.token);
        dest.writeString(this.type);
        dest.writeByte(this.updateShippingMethod ? (byte) 1 : (byte) 0);
        dest.writeByte(this.updateShippingStatus ? (byte) 1 : (byte) 0);
        dest.writeString(this.user);
        dest.writeString(this.username);
        dest.writeParcelable(this.warehouseSettings, flags);
        dest.writeInt(this.conflictProducts);
        dest.writeInt(this.importedOrders);
        dest.writeInt(this.importedProducts);
        dest.writeInt(this.unlinkedOrders);
    }

    protected Channel(Parcel in) {
        this.id = in.readString();
        this.active = in.readByte() != 0;
        this.bankAccount = in.readParcelable(BankAccount.class.getClassLoader());
        this.baseUrl = in.readString();
        this.channelName = in.readString();
        this.connected = in.readByte() != 0;
        this.consumerKey = in.readString();
        this.consumerSecret = in.readString();
        this.dbName = in.readString();
        this.password = in.readString();
        this.priceList = in.readParcelable(PriceList.class.getClassLoader());
        this.secret = in.readString();
        this.shippingMethod = in.readParcelable(FilterItem.class.getClassLoader());
        this.token = in.readString();
        this.type = in.readString();
        this.updateShippingMethod = in.readByte() != 0;
        this.updateShippingStatus = in.readByte() != 0;
        this.user = in.readString();
        this.username = in.readString();
        this.warehouseSettings = in.readParcelable(WarehouseSettings.class.getClassLoader());
        this.conflictProducts = in.readInt();
        this.importedOrders = in.readInt();
        this.importedProducts = in.readInt();
        this.unlinkedOrders = in.readInt();
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
