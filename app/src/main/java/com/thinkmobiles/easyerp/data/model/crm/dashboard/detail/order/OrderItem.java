package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;

import java.util.List;

/**
 * Created by Asus_Dev on 1/19/2017.
 */

public class OrderItem implements Parcelable, IChartModel {

    @SerializedName("_id")
    public String id;
    public Double total;
    public Integer count;
    public String name;
    public List<OrderStatus> status;

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
        dest.writeTypedList(this.status);
    }

    public OrderItem() {
    }

    protected OrderItem(Parcel in) {
        this.id = in.readString();
        this.total = (Double) in.readValue(Double.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.status = in.createTypedArrayList(OrderStatus.CREATOR);
    }

    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel source) {
            return new OrderItem(source);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };
}
