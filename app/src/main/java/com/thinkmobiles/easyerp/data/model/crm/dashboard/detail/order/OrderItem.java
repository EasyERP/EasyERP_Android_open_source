package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;

import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */

public class OrderItem extends OrderBase implements Parcelable, IChartModel {

    public List<OrderStatus> status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.status);
    }

    public OrderItem() {
    }

    protected OrderItem(Parcel in) {
        super(in);
        this.status = in.createTypedArrayList(OrderStatus.CREATOR);
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
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
