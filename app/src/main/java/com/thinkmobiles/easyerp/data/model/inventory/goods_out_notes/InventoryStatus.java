package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 3/6/2017.
 */

public class InventoryStatus implements Parcelable {
    public boolean shipped;
    public boolean picked;
    public boolean packed;
    public boolean printed;
    public boolean received;
    public String receivedById;
    public String receivedOn;
    public String shippedOn;
    public String pickedOn;
    public String packedOn;
    public String printedOn;
    public String printedById;
    public String shippedById;
    public String packedById;
    public String pickedById;

    public InventoryStatus() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.shipped ? (byte) 1 : (byte) 0);
        dest.writeByte(this.picked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.packed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.printed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.received ? (byte) 1 : (byte) 0);
        dest.writeString(this.receivedById);
        dest.writeString(this.receivedOn);
        dest.writeString(this.shippedOn);
        dest.writeString(this.pickedOn);
        dest.writeString(this.packedOn);
        dest.writeString(this.printedOn);
        dest.writeString(this.printedById);
        dest.writeString(this.shippedById);
        dest.writeString(this.packedById);
        dest.writeString(this.pickedById);
    }

    protected InventoryStatus(Parcel in) {
        this.shipped = in.readByte() != 0;
        this.picked = in.readByte() != 0;
        this.packed = in.readByte() != 0;
        this.printed = in.readByte() != 0;
        this.received = in.readByte() != 0;
        this.receivedById = in.readString();
        this.receivedOn = in.readString();
        this.shippedOn = in.readString();
        this.pickedOn = in.readString();
        this.packedOn = in.readString();
        this.printedOn = in.readString();
        this.printedById = in.readString();
        this.shippedById = in.readString();
        this.packedById = in.readString();
        this.pickedById = in.readString();
    }

    public static final Creator<InventoryStatus> CREATOR = new Creator<InventoryStatus>() {
        @Override
        public InventoryStatus createFromParcel(Parcel source) {
            return new InventoryStatus(source);
        }

        @Override
        public InventoryStatus[] newArray(int size) {
            return new InventoryStatus[size];
        }
    };
}
