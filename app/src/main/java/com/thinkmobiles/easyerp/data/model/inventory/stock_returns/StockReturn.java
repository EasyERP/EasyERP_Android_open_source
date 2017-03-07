package com.thinkmobiles.easyerp.data.model.inventory.stock_returns;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class StockReturn implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public Order order;
    public StockReturnStatus status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.order, flags);
        dest.writeParcelable(this.status, flags);
    }

    public StockReturn() {
    }

    protected StockReturn(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.order = in.readParcelable(Order.class.getClassLoader());
        this.status = in.readParcelable(StockReturnStatus.class.getClassLoader());
    }

    public static final Parcelable.Creator<StockReturn> CREATOR = new Parcelable.Creator<StockReturn>() {
        @Override
        public StockReturn createFromParcel(Parcel source) {
            return new StockReturn(source);
        }

        @Override
        public StockReturn[] newArray(int size) {
            return new StockReturn[size];
        }
    };
}
