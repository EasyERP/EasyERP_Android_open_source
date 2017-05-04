package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;

/**
 * @author Michael Soyma (Created on 5/3/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public final class Warehouse implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String account;
    public Address address;
    public EditedBy createdBy;
    public EditedBy editedBy;
    public boolean isOwn;
    public boolean main;

    public Warehouse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.account);
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeByte(this.isOwn ? (byte) 1 : (byte) 0);
        dest.writeByte(this.main ? (byte) 1 : (byte) 0);
    }

    protected Warehouse(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.account = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.isOwn = in.readByte() != 0;
        this.main = in.readByte() != 0;
    }

    public static final Creator<Warehouse> CREATOR = new Creator<Warehouse>() {
        @Override
        public Warehouse createFromParcel(Parcel source) {
            return new Warehouse(source);
        }

        @Override
        public Warehouse[] newArray(int size) {
            return new Warehouse[size];
        }
    };
}
