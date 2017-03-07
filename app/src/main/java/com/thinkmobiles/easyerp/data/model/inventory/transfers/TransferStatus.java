package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 3/7/2017.
 */

public class TransferStatus implements Parcelable {
    /**
     * printedById: "52203e707d4dba8813000003",
     shippedById: null,
     packedById: null,
     receivedById: null,
     printedOn: "2017-03-07T09:30:34.794Z",
     printed: false
     */

    public String printedById;
    public String shippedById;
    public String packedById;
    public String receivedById;
    public String printedOn;
    public boolean printed;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.printedById);
        dest.writeString(this.shippedById);
        dest.writeString(this.packedById);
        dest.writeString(this.receivedById);
        dest.writeString(this.printedOn);
        dest.writeByte(this.printed ? (byte) 1 : (byte) 0);
    }

    public TransferStatus() {
    }

    protected TransferStatus(Parcel in) {
        this.printedById = in.readString();
        this.shippedById = in.readString();
        this.packedById = in.readString();
        this.receivedById = in.readString();
        this.printedOn = in.readString();
        this.printed = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TransferStatus> CREATOR = new Parcelable.Creator<TransferStatus>() {
        @Override
        public TransferStatus createFromParcel(Parcel source) {
            return new TransferStatus(source);
        }

        @Override
        public TransferStatus[] newArray(int size) {
            return new TransferStatus[size];
        }
    };
}
