package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.InventoryStatus;

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
    public ArrayList<OrderRowItem> orderRows;
    public ArrayList<AttachmentItem> attachments;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public String date;
    public int weight;
    public int shippingCost;
    public int boxes;
    public String reference;
    public WarehouseInfo warehouse;

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
        dest.writeList(this.orderRows);
        dest.writeTypedList(this.attachments);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
        dest.writeInt(this.weight);
        dest.writeInt(this.shippingCost);
        dest.writeInt(this.boxes);
        dest.writeString(this.reference);
        dest.writeParcelable(this.warehouse, flags);
    }

    public ResponseGetTransferDetails() {
    }

    protected ResponseGetTransferDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.warehouseTo = in.readParcelable(WarehouseInfo.class.getClassLoader());
        this.status = in.readParcelable(InventoryStatus.class.getClassLoader());
        this.orderRows = new ArrayList<OrderRowItem>();
        in.readList(this.orderRows, OrderRowItem.class.getClassLoader());
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.date = in.readString();
        this.weight = in.readInt();
        this.shippingCost = in.readInt();
        this.boxes = in.readInt();
        this.reference = in.readString();
        this.warehouse = in.readParcelable(WarehouseInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseGetTransferDetails> CREATOR = new Parcelable.Creator<ResponseGetTransferDetails>() {
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
