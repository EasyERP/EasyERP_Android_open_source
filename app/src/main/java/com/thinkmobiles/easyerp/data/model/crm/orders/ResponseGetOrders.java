package com.thinkmobiles.easyerp.data.model.crm.orders;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ResponseGetOrders implements Parcelable {

    public int total;
    public ArrayList<Order> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetOrders() {
    }

    protected ResponseGetOrders(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(Order.CREATOR);
    }

    public static final Creator<ResponseGetOrders> CREATOR = new Creator<ResponseGetOrders>() {
        @Override
        public ResponseGetOrders createFromParcel(Parcel source) {
            return new ResponseGetOrders(source);
        }

        @Override
        public ResponseGetOrders[] newArray(int size) {
            return new ResponseGetOrders[size];
        }
    };
}
