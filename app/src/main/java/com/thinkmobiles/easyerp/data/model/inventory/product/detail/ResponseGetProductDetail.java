package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;

import java.util.ArrayList;

/**
 * Created by samson on 13.03.17.
 */

public class ResponseGetProductDetail implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String groupId;
    public ArrayList<ProductVariant> variantsArray;
    public ArrayList<ImageItem> images;
    public ArrayList<ProductValue> currentValues;
    public ArrayList<ProductId> valuesIds;

    //from Rx
    public SalesChannel channels;
    public StockInventory stockInventory;
    public ArrayList<FilterItem> productTypes;


    public ResponseGetProductDetail() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.groupId);
        dest.writeTypedList(this.variantsArray);
        dest.writeTypedList(this.images);
        dest.writeTypedList(this.currentValues);
        dest.writeTypedList(this.valuesIds);
        dest.writeParcelable(this.channels, flags);
        dest.writeParcelable(this.stockInventory, flags);
        dest.writeTypedList(this.productTypes);
    }

    protected ResponseGetProductDetail(Parcel in) {
        this.id = in.readString();
        this.groupId = in.readString();
        this.variantsArray = in.createTypedArrayList(ProductVariant.CREATOR);
        this.images = in.createTypedArrayList(ImageItem.CREATOR);
        this.currentValues = in.createTypedArrayList(ProductValue.CREATOR);
        this.valuesIds = in.createTypedArrayList(ProductId.CREATOR);
        this.channels = in.readParcelable(SalesChannel.class.getClassLoader());
        this.stockInventory = in.readParcelable(StockInventory.class.getClassLoader());
        this.productTypes = in.createTypedArrayList(FilterItem.CREATOR);
    }

    public static final Creator<ResponseGetProductDetail> CREATOR = new Creator<ResponseGetProductDetail>() {
        @Override
        public ResponseGetProductDetail createFromParcel(Parcel source) {
            return new ResponseGetProductDetail(source);
        }

        @Override
        public ResponseGetProductDetail[] newArray(int size) {
            return new ResponseGetProductDetail[size];
        }
    };
}
