package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class ProductVariant implements Parcelable {

    @SerializedName("_id")
    public String id;
    public ArrayList<ArrayList<Variant>> variants;
    @SerializedName("pL")
    public ArrayList<PriceList> priceList;
    public ProductInfo info;
    public String name;
    public String imageSrc;
    public boolean isBundle;
    public boolean isVariant;
    public ProductInventory inventory;
    public String whoCanRW;
    public String creationDate;
    public CreatedEditedUserString createdBy;
    public CreatedEditedUserString editedBy;
    public boolean canBeSold;
    public boolean canBeExpensed;
    public boolean eventSubscription;
    public boolean canBePurchased;
    public String groupId;
    public ArrayList<FilterItem> categories;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeSerializable(this.variants);
        dest.writeTypedList(this.priceList);
        dest.writeParcelable(this.info, flags);
        dest.writeString(this.name);
        dest.writeString(this.imageSrc);
        dest.writeByte(this.isBundle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isVariant ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.inventory, flags);
        dest.writeString(this.whoCanRW);
        dest.writeString(this.creationDate);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeByte(this.canBeSold ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canBeExpensed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.eventSubscription ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canBePurchased ? (byte) 1 : (byte) 0);
        dest.writeString(this.groupId);
        dest.writeTypedList(this.categories);
    }

    public ProductVariant() {
    }

    protected ProductVariant(Parcel in) {
        this.id = in.readString();
        this.variants = (ArrayList<ArrayList<Variant>>)in.readSerializable();
        this.priceList = in.createTypedArrayList(PriceList.CREATOR);
        this.info = in.readParcelable(ProductInfo.class.getClassLoader());
        this.name = in.readString();
        this.imageSrc = in.readString();
        this.isBundle = in.readByte() != 0;
        this.isVariant = in.readByte() != 0;
        this.inventory = in.readParcelable(ProductInventory.class.getClassLoader());
        this.whoCanRW = in.readString();
        this.creationDate = in.readString();
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.canBeSold = in.readByte() != 0;
        this.canBeExpensed = in.readByte() != 0;
        this.eventSubscription = in.readByte() != 0;
        this.canBePurchased = in.readByte() != 0;
        this.groupId = in.readString();
        this.categories = in.createTypedArrayList(FilterItem.CREATOR);
    }

    public static final Parcelable.Creator<ProductVariant> CREATOR = new Parcelable.Creator<ProductVariant>() {
        @Override
        public ProductVariant createFromParcel(Parcel source) {
            return new ProductVariant(source);
        }

        @Override
        public ProductVariant[] newArray(int size) {
            return new ProductVariant[size];
        }
    };
}
