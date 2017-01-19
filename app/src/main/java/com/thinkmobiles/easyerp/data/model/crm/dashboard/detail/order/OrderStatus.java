package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus_Dev on 1/19/2017.
 */

public class OrderStatus implements Parcelable {

    /**
     {
        "shippingStatus": "NOR",
        "fulfillStatus": "NOT",
        "allocateStatus": "NOR"
     }
     */

    public String shippingStatus;
    public String fulfillStatus;
    public String allocateStatus;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shippingStatus);
        dest.writeString(this.fulfillStatus);
        dest.writeString(this.allocateStatus);
    }

    public OrderStatus() {
    }

    protected OrderStatus(Parcel in) {
        this.shippingStatus = in.readString();
        this.fulfillStatus = in.readString();
        this.allocateStatus = in.readString();
    }

    public static final Parcelable.Creator<OrderStatus> CREATOR = new Parcelable.Creator<OrderStatus>() {
        @Override
        public OrderStatus createFromParcel(Parcel source) {
            return new OrderStatus(source);
        }

        @Override
        public OrderStatus[] newArray(int size) {
            return new OrderStatus[size];
        }
    };
}
