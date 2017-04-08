package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samson on 13.03.17.
 */

public class Price implements Parcelable {

    public Float price;
    public int count;

    public Price() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.price);
        dest.writeInt(this.count);
    }

    protected Price(Parcel in) {
        this.price = (Float) in.readValue(Float.class.getClassLoader());
        this.count = in.readInt();
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
