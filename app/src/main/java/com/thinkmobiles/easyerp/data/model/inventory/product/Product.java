package com.thinkmobiles.easyerp.data.model.inventory.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.ProductInfo;

import java.util.List;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Product implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String imageSrc;
    public String ProductTypesId;
    public String ProductTypesName;
    public EditedBy createdBy;
    public String groupId;
    public ProductInfo info;
    public List<FilterItem> ProductCategories;
    public ProductVariantsCount variantsCount;

    public Product() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageSrc);
        dest.writeString(this.ProductTypesId);
        dest.writeString(this.ProductTypesName);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.groupId);
        dest.writeParcelable(this.info, flags);
        dest.writeTypedList(this.ProductCategories);
        dest.writeParcelable(this.variantsCount, flags);
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.imageSrc = in.readString();
        this.ProductTypesId = in.readString();
        this.ProductTypesName = in.readString();
        this.createdBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.groupId = in.readString();
        this.info = in.readParcelable(ProductInfo.class.getClassLoader());
        this.ProductCategories = in.createTypedArrayList(FilterItem.CREATOR);
        this.variantsCount = in.readParcelable(ProductVariantsCount.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
