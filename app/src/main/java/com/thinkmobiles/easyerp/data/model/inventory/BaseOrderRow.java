package com.thinkmobiles.easyerp.data.model.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.SubProduct;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;

import java.util.ArrayList;

/**
 * Created by samson on 10.03.17.
 */

public class BaseOrderRow implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String description;
    public int quantity;
    public SubProduct product;
    public int shipped;
    public int selectedQuantity;
    public ArrayList<LocationsDeliver> locationsDeliver;
    public FilterItem goodsOutNote;
    public FilterItem goodsInNote;
    public Float cost;

    public BaseOrderRow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeInt(this.quantity);
        dest.writeParcelable(this.product, flags);
        dest.writeInt(this.shipped);
        dest.writeInt(this.selectedQuantity);
        dest.writeTypedList(this.locationsDeliver);
        dest.writeParcelable(this.goodsOutNote, flags);
        dest.writeParcelable(this.goodsInNote, flags);
        dest.writeValue(this.cost);
    }

    protected BaseOrderRow(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.quantity = in.readInt();
        this.product = in.readParcelable(SubProduct.class.getClassLoader());
        this.shipped = in.readInt();
        this.selectedQuantity = in.readInt();
        this.locationsDeliver = in.createTypedArrayList(LocationsDeliver.CREATOR);
        this.goodsOutNote = in.readParcelable(FilterItem.class.getClassLoader());
        this.goodsInNote = in.readParcelable(FilterItem.class.getClassLoader());
        this.cost = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Creator<BaseOrderRow> CREATOR = new Creator<BaseOrderRow>() {
        @Override
        public BaseOrderRow createFromParcel(Parcel source) {
            return new BaseOrderRow(source);
        }

        @Override
        public BaseOrderRow[] newArray(int size) {
            return new BaseOrderRow[size];
        }
    };
}
