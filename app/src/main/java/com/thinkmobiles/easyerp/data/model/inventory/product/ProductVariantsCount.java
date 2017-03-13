package com.thinkmobiles.easyerp.data.model.inventory.product;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 3/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ProductVariantsCount implements Parcelable {

    public int count;
    public String groupId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.groupId);
    }

    public ProductVariantsCount() {
    }

    protected ProductVariantsCount(Parcel in) {
        this.count = in.readInt();
        this.groupId = in.readString();
    }

    public static final Parcelable.Creator<ProductVariantsCount> CREATOR = new Parcelable.Creator<ProductVariantsCount>() {
        @Override
        public ProductVariantsCount createFromParcel(Parcel source) {
            return new ProductVariantsCount(source);
        }

        @Override
        public ProductVariantsCount[] newArray(int size) {
            return new ProductVariantsCount[size];
        }
    };
}
