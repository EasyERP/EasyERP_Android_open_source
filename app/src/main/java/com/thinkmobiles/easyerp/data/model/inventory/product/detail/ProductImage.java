package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class ProductImage implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String integrationId;
    public String channel;
    public String product;
    public String imageSrc;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.integrationId);
        dest.writeString(this.channel);
        dest.writeString(this.product);
        dest.writeString(this.imageSrc);
    }

    public ProductImage() {
    }

    protected ProductImage(Parcel in) {
        this.id = in.readString();
        this.integrationId = in.readString();
        this.channel = in.readString();
        this.product = in.readString();
        this.imageSrc = in.readString();
    }

    public static final Parcelable.Creator<ProductImage> CREATOR = new Parcelable.Creator<ProductImage>() {
        @Override
        public ProductImage createFromParcel(Parcel source) {
            return new ProductImage(source);
        }

        @Override
        public ProductImage[] newArray(int size) {
            return new ProductImage[size];
        }
    };
}
