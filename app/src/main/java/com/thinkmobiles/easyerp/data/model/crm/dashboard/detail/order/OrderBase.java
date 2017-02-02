package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class OrderBase implements Parcelable {

    @SerializedName("_id")
    public String id;
    public Double total;
    public Integer count;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.total);
        dest.writeValue(this.count);
        dest.writeString(this.name);
    }

    public OrderBase() {
    }

    protected OrderBase(Parcel in) {
        this.id = in.readString();
        this.total = (Double) in.readValue(Double.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Creator<OrderBase> CREATOR = new Creator<OrderBase>() {
        @Override
        public OrderBase createFromParcel(Parcel source) {
            return new OrderBase(source);
        }

        @Override
        public OrderBase[] newArray(int size) {
            return new OrderBase[size];
        }
    };
}