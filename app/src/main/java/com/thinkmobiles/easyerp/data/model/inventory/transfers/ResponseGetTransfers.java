package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;

/**
 * Created by Lynx on 3/7/2017.
 */

public class ResponseGetTransfers implements Parcelable {
    public String _id;
    public int total;
    public String name;
    public WarehouseInfo warehouse;
    public WarehouseInfo warehouseTo;
    public CreatedEditedBy createdBy;
    public TransactionStatus status;
    public String description;
    public boolean notRemovable;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeInt(this.total);
        dest.writeString(this.name);
        dest.writeParcelable(this.warehouse, flags);
        dest.writeParcelable(this.warehouseTo, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.status, flags);
        dest.writeString(this.description);
        dest.writeByte(this.notRemovable ? (byte) 1 : (byte) 0);
    }

    public ResponseGetTransfers() {
    }

    protected ResponseGetTransfers(Parcel in) {
        this._id = in.readString();
        this.total = in.readInt();
        this.name = in.readString();
        this.warehouse = in.readParcelable(WarehouseInfo.class.getClassLoader());
        this.warehouseTo = in.readParcelable(WarehouseInfo.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.status = in.readParcelable(TransactionStatus.class.getClassLoader());
        this.description = in.readString();
        this.notRemovable = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ResponseGetTransfers> CREATOR = new Parcelable.Creator<ResponseGetTransfers>() {
        @Override
        public ResponseGetTransfers createFromParcel(Parcel source) {
            return new ResponseGetTransfers(source);
        }

        @Override
        public ResponseGetTransfers[] newArray(int size) {
            return new ResponseGetTransfers[size];
        }
    };
}
