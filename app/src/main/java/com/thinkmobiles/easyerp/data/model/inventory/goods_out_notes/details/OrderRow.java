package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.inventory.BaseOrderRow;

/**
 * Created by samson on 06.03.17.
 */

public class OrderRow extends BaseOrderRow implements Parcelable {


    public String warehouse;

    public OrderRow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.warehouse);
    }

    protected OrderRow(Parcel in) {
        super(in);
        this.warehouse = in.readString();
    }

    public static final Creator<OrderRow> CREATOR = new Creator<OrderRow>() {
        @Override
        public OrderRow createFromParcel(Parcel source) {
            return new OrderRow(source);
        }

        @Override
        public OrderRow[] newArray(int size) {
            return new OrderRow[size];
        }
    };
}
