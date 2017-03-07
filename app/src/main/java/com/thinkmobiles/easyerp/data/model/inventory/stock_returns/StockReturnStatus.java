package com.thinkmobiles.easyerp.data.model.inventory.stock_returns;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.User;

/**
 * @author Michael Soyma (Created on 3/6/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class StockReturnStatus implements Parcelable {

    public String receivedOn;
    public User receivedById;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.receivedOn);
        dest.writeParcelable(this.receivedById, flags);
    }

    public StockReturnStatus() {
    }

    protected StockReturnStatus(Parcel in) {
        this.receivedOn = in.readString();
        this.receivedById = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<StockReturnStatus> CREATOR = new Parcelable.Creator<StockReturnStatus>() {
        @Override
        public StockReturnStatus createFromParcel(Parcel source) {
            return new StockReturnStatus(source);
        }

        @Override
        public StockReturnStatus[] newArray(int size) {
            return new StockReturnStatus[size];
        }
    };
}
