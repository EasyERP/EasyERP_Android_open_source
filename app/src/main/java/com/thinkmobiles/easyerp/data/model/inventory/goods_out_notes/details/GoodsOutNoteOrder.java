package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderBase;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.Supplier;

/**
 * Created by samson on 07.03.17.
 */

public class GoodsOutNoteOrder extends OrderBase implements Parcelable {

    public Supplier supplier;

    public GoodsOutNoteOrder() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.supplier, flags);
    }

    protected GoodsOutNoteOrder(Parcel in) {
        super(in);
        this.supplier = in.readParcelable(Supplier.class.getClassLoader());
    }

    public static final Creator<GoodsOutNoteOrder> CREATOR = new Creator<GoodsOutNoteOrder>() {
        @Override
        public GoodsOutNoteOrder createFromParcel(Parcel source) {
            return new GoodsOutNoteOrder(source);
        }

        @Override
        public GoodsOutNoteOrder[] newArray(int size) {
            return new GoodsOutNoteOrder[size];
        }
    };
}
