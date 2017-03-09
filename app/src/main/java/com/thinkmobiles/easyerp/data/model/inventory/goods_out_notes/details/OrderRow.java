package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.OrderTax;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.SubProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samson on 06.03.17.
 */

public class OrderRow implements Parcelable {


    @SerializedName("_id")
    public String id;
    public String description;
    public Float totalTaxes;
    public String channel;
    public String creditAccount;
    public String debitAccount;
    public String creationDate;
    public int nominalCode;
    public int subTotal;
    public String costPrice;
    public int unitPrice;
    public List<OrderTax> taxes;
    public int quantity;
    public String warehouse;
    public String order;
    public SubProduct product;
    public int shipped;
    public int selectedQuantity;
    public ArrayList<LocationsDeliver> locationsDeliver;



    public OrderRow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeValue(this.totalTaxes);
        dest.writeString(this.channel);
        dest.writeString(this.creditAccount);
        dest.writeString(this.debitAccount);
        dest.writeString(this.creationDate);
        dest.writeInt(this.nominalCode);
        dest.writeInt(this.subTotal);
        dest.writeString(this.costPrice);
        dest.writeInt(this.unitPrice);
        dest.writeTypedList(this.taxes);
        dest.writeInt(this.quantity);
        dest.writeString(this.warehouse);
        dest.writeString(this.order);
        dest.writeParcelable(this.product, flags);
        dest.writeInt(this.shipped);
        dest.writeInt(this.selectedQuantity);
        dest.writeTypedList(this.locationsDeliver);
    }

    protected OrderRow(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.totalTaxes = (Float) in.readValue(Float.class.getClassLoader());
        this.channel = in.readString();
        this.creditAccount = in.readString();
        this.debitAccount = in.readString();
        this.creationDate = in.readString();
        this.nominalCode = in.readInt();
        this.subTotal = in.readInt();
        this.costPrice = in.readString();
        this.unitPrice = in.readInt();
        this.taxes = in.createTypedArrayList(OrderTax.CREATOR);
        this.quantity = in.readInt();
        this.warehouse = in.readString();
        this.order = in.readString();
        this.product = in.readParcelable(SubProduct.class.getClassLoader());
        this.shipped = in.readInt();
        this.selectedQuantity = in.readInt();
        this.locationsDeliver = in.createTypedArrayList(LocationsDeliver.CREATOR);
    }

    public static final Creator<OrderRow> CREATOR = new Creator<OrderRow>() {
        @Override
        public OrderRow createFromParcel(Parcel source) {
            return new OrderRow(source);
        }

        @Override
        public OrderRow[] newArray(int size) {
            return new OrderRow[size];
        }
    };
}
