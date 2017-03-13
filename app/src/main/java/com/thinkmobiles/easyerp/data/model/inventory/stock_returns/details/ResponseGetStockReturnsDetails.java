package com.thinkmobiles.easyerp.data.model.inventory.stock_returns.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;
import com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details.OrderRow;
import com.thinkmobiles.easyerp.data.model.inventory.stock_returns.StockReturnStatus;

import java.util.ArrayList;

/**
 * Created by samson on 09.03.17.
 */

public class ResponseGetStockReturnsDetails implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String releaseDate;
    public String description;
    public ArrayList<String> journalEntrySources;
    public StockReturnStatus status;
    public String channel;
    public ArrayList<OrderRow> orderRows;
    public ArrayList<AttachmentItem> attachments;
    public String order;
    public CreatedEditedUserString editedBy;
    public CreatedEditedUserString createdBy;
    public String date;
    public int weight;
    public int shippingCost;
    public String shippingMethod;
    public int boxes;
    public String reference;
    public String warehouse;


    public ResponseGetStockReturnsDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.releaseDate);
        dest.writeString(this.description);
        dest.writeStringList(this.journalEntrySources);
        dest.writeParcelable(this.status, flags);
        dest.writeString(this.channel);
        dest.writeTypedList(this.orderRows);
        dest.writeTypedList(this.attachments);
        dest.writeString(this.order);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
        dest.writeInt(this.weight);
        dest.writeInt(this.shippingCost);
        dest.writeString(this.shippingMethod);
        dest.writeInt(this.boxes);
        dest.writeString(this.reference);
        dest.writeString(this.warehouse);
    }

    protected ResponseGetStockReturnsDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.releaseDate = in.readString();
        this.description = in.readString();
        this.journalEntrySources = in.createStringArrayList();
        this.status = in.readParcelable(StockReturnStatus.class.getClassLoader());
        this.channel = in.readString();
        this.orderRows = in.createTypedArrayList(OrderRow.CREATOR);
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.order = in.readString();
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.date = in.readString();
        this.weight = in.readInt();
        this.shippingCost = in.readInt();
        this.shippingMethod = in.readString();
        this.boxes = in.readInt();
        this.reference = in.readString();
        this.warehouse = in.readString();
    }

    public static final Creator<ResponseGetStockReturnsDetails> CREATOR = new Creator<ResponseGetStockReturnsDetails>() {
        @Override
        public ResponseGetStockReturnsDetails createFromParcel(Parcel source) {
            return new ResponseGetStockReturnsDetails(source);
        }

        @Override
        public ResponseGetStockReturnsDetails[] newArray(int size) {
            return new ResponseGetStockReturnsDetails[size];
        }
    };
}
