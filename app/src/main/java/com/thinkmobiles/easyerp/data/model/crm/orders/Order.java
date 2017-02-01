package com.thinkmobiles.easyerp.data.model.crm.orders;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.Currency;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.PaymentInfo;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderBase;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.order.OrderStatus;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;

/**
 * @author Michael Soyma (Created on 2/1/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class Order extends OrderBase implements Parcelable {

    public String orderDate;
    public Workflow workflow;
    public SalesPerson supplier;
    public PaymentInfo paymentInfo;
    public Currency currency;
    public OrderStatus status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.orderDate);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.supplier, flags);
        dest.writeParcelable(this.paymentInfo, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeParcelable(this.status, flags);
    }

    public Order() {
    }

    protected Order(Parcel in) {
        super(in);
        this.orderDate = in.readString();
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.supplier = in.readParcelable(SalesPerson.class.getClassLoader());
        this.paymentInfo = in.readParcelable(PaymentInfo.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.status = in.readParcelable(OrderStatus.class.getClassLoader());
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
