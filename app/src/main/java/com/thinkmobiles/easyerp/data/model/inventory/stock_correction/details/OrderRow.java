package com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details;


import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.inventory.BaseOrderRow;

/**
 * Created by samson on 10.03.17.
 */

public class OrderRow extends BaseOrderRow  {

    public FilterItem warehouse;

    public OrderRow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.warehouse, flags);
    }

    protected OrderRow(Parcel in) {
        super(in);
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
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
