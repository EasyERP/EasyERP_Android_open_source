package com.thinkmobiles.easyerp.data.model.inventory.product.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samson on 13.03.17.
 */

public class Variant implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String optionId;
    public String value;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.optionId);
        dest.writeString(this.value);
    }

    public Variant() {
    }

    protected Variant(Parcel in) {
        this.id = in.readString();
        this.optionId = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<Variant> CREATOR = new Parcelable.Creator<Variant>() {
        @Override
        public Variant createFromParcel(Parcel source) {
            return new Variant(source);
        }

        @Override
        public Variant[] newArray(int size) {
            return new Variant[size];
        }
    };
}
