package com.thinkmobiles.easyerp.data.model.crm.payments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.invoice.CurrencyID;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.order.Order;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.PaymentMethod;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.Supplier;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Payment implements Parcelable {

    @SerializedName("_id")
    public String id;
    @SerializedName("_type")
    public String type;
    public SalesPerson assigned;
    public FilterItem bankAccount;
    public FilterItem bankExpenses;
    public CurrencyID currency;
    public String date;
    public Double differenceAmount;
    public boolean forSale;
    public Invoice invoice;
    public String name;
    public Order order;
    public Double paidAmount;
    public String paymentRef;
    public PaymentMethod paymentMethod;
    public boolean refund;
    public boolean removable;
    public Supplier supplier;
    public int total;
    public String workflow;

    public Payment() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeParcelable(this.assigned, flags);
        dest.writeParcelable(this.bankAccount, flags);
        dest.writeParcelable(this.bankExpenses, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeString(this.date);
        dest.writeValue(this.differenceAmount);
        dest.writeByte(this.forSale ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.invoice, flags);
        dest.writeString(this.name);
        dest.writeParcelable(this.order, flags);
        dest.writeValue(this.paidAmount);
        dest.writeString(this.paymentRef);
        dest.writeParcelable(this.paymentMethod, flags);
        dest.writeByte(this.refund ? (byte) 1 : (byte) 0);
        dest.writeByte(this.removable ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.supplier, flags);
        dest.writeInt(this.total);
        dest.writeString(this.workflow);
    }

    protected Payment(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.assigned = in.readParcelable(SalesPerson.class.getClassLoader());
        this.bankAccount = in.readParcelable(FilterItem.class.getClassLoader());
        this.bankExpenses = in.readParcelable(FilterItem.class.getClassLoader());
        this.currency = in.readParcelable(CurrencyID.class.getClassLoader());
        this.date = in.readString();
        this.differenceAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.forSale = in.readByte() != 0;
        this.invoice = in.readParcelable(Invoice.class.getClassLoader());
        this.name = in.readString();
        this.order = in.readParcelable(Order.class.getClassLoader());
        this.paidAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.paymentRef = in.readString();
        this.paymentMethod = in.readParcelable(PaymentMethod.class.getClassLoader());
        this.refund = in.readByte() != 0;
        this.removable = in.readByte() != 0;
        this.supplier = in.readParcelable(SalesPerson.class.getClassLoader());
        this.total = in.readInt();
        this.workflow = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}
