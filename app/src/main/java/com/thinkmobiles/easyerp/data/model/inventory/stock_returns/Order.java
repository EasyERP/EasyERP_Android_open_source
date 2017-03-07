package com.thinkmobiles.easyerp.data.model.inventory.stock_returns;

import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderBase;
import com.thinkmobiles.easyerp.data.model.crm.invoice.CurrencyID;
import com.thinkmobiles.easyerp.data.model.crm.invoice.PaymentInfo;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Order extends OrderBase {

    public CurrencyID currency;
    public PaymentInfo paymentInfo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeParcelable(this.paymentInfo, flags);
    }

    public Order() {
    }

    protected Order(Parcel in) {
        super(in);
        this.currency = in.readParcelable(CurrencyID.class.getClassLoader());
        this.paymentInfo = in.readParcelable(PaymentInfo.class.getClassLoader());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
