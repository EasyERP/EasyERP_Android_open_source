package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderBase;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

/**
 * Created by Lynx on 3/6/2017.
 */

public class GoodsOutNoteItem implements Parcelable {

    @SerializedName("_id")
    public String id;
    public int total;
    public String name;
    public OrderBase order;
    public InventoryStatus status;
    public FilterItem warehouse;
    public Workflow workflow;
    public FilterItem customer;
    public CreatedEditedUserString createdBy;
    public String date;
    public boolean shipped;
    public boolean printed;
    public boolean picked;
    public boolean packed;

    public GoodsOutNoteItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.total);
        dest.writeString(this.name);
        dest.writeParcelable(this.order, flags);
        dest.writeParcelable(this.status, flags);
        dest.writeParcelable(this.warehouse, flags);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.customer, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
        dest.writeByte(this.shipped ? (byte) 1 : (byte) 0);
        dest.writeByte(this.printed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.picked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.packed ? (byte) 1 : (byte) 0);
    }

    protected GoodsOutNoteItem(Parcel in) {
        this.id = in.readString();
        this.total = in.readInt();
        this.name = in.readString();
        this.order = in.readParcelable(OrderBase.class.getClassLoader());
        this.status = in.readParcelable(InventoryStatus.class.getClassLoader());
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.customer = in.readParcelable(FilterItem.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.date = in.readString();
        this.shipped = in.readByte() != 0;
        this.printed = in.readByte() != 0;
        this.picked = in.readByte() != 0;
        this.packed = in.readByte() != 0;
    }

    public static final Creator<GoodsOutNoteItem> CREATOR = new Creator<GoodsOutNoteItem>() {
        @Override
        public GoodsOutNoteItem createFromParcel(Parcel source) {
            return new GoodsOutNoteItem(source);
        }

        @Override
        public GoodsOutNoteItem[] newArray(int size) {
            return new GoodsOutNoteItem[size];
        }
    };
}
