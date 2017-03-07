package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

/**
 * Created by Lynx on 3/7/2017.
 */

public class WarehouseInfo implements Parcelable {
    public String _id;
    public String account;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public boolean main;
    public boolean isOwn;
    public Address address;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.account);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeByte(this.main ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOwn ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.name);
    }

    public WarehouseInfo() {
    }

    protected WarehouseInfo(Parcel in) {
        this._id = in.readString();
        this.account = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.main = in.readByte() != 0;
        this.isOwn = in.readByte() != 0;
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<WarehouseInfo> CREATOR = new Parcelable.Creator<WarehouseInfo>() {
        @Override
        public WarehouseInfo createFromParcel(Parcel source) {
            return new WarehouseInfo(source);
        }

        @Override
        public WarehouseInfo[] newArray(int size) {
            return new WarehouseInfo[size];
        }
    };
}
