package com.thinkmobiles.easyerp.data.model.inventory.transfers.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.InventoryStatus;
import com.thinkmobiles.easyerp.data.model.inventory.transfers.WarehouseInfo;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/9/2017.
 */

public class ResponseGetTransferDetails implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String description;

    public WarehouseInfo warehouseTo;
    public InventoryStatus status;
    public ArrayList<TransferRowItem> orderRows;
    public ArrayList<AttachmentItem> attachments;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public String date;
    public int weight;
    public double shippingCost;
    public int boxes;
    public String reference;
    public String shippingMethod;
    public WarehouseInfo warehouse;

    public ResponseGetTransferDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.warehouseTo, flags);
        dest.writeParcelable(this.status, flags);
        dest.writeTypedList(this.orderRows);
        dest.writeTypedList(this.attachments);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
        dest.writeInt(this.weight);
        dest.writeDouble(this.shippingCost);
        dest.writeInt(this.boxes);
        dest.writeString(this.reference);
        dest.writeString(this.shippingMethod);
        dest.writeParcelable(this.warehouse, flags);
    }

    protected ResponseGetTransferDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.warehouseTo = in.readParcelable(WarehouseInfo.class.getClassLoader());
        this.status = in.readParcelable(InventoryStatus.class.getClassLoader());
        this.orderRows = in.createTypedArrayList(TransferRowItem.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.date = in.readString();
        this.weight = in.readInt();
        this.shippingCost = in.readDouble();
        this.boxes = in.readInt();
        this.reference = in.readString();
        this.shippingMethod = in.readString();
        this.warehouse = in.readParcelable(WarehouseInfo.class.getClassLoader());
    }

    public static final Creator<ResponseGetTransferDetails> CREATOR = new Creator<ResponseGetTransferDetails>() {
        @Override
        public ResponseGetTransferDetails createFromParcel(Parcel source) {
            return new ResponseGetTransferDetails(source);
        }

        @Override
        public ResponseGetTransferDetails[] newArray(int size) {
            return new ResponseGetTransferDetails[size];
        }
    };
}
