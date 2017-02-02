package com.thinkmobiles.easyerp.data.model.crm.invoice;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */

public class Invoice implements Parcelable, IChartModel, Comparable<Invoice> {

    @SerializedName("_id")
    public String id;
    public SalesPerson salesPerson;
    public Workflow workflow;
    public SalesPerson supplier;
    public Currency currency;
    public SalesPerson journal;
    public PaymentInfo paymentInfo;
    public String invoiceDate;
    public String name;
    public String paymentDate;
    public boolean approved;
    public boolean removable;
    public Double paid;
    public EditedBy editedBy;

    public Double total;
    public Integer count;
    public Double sum;

    public Invoice() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.salesPerson, flags);
        dest.writeParcelable(this.workflow, flags);
        dest.writeParcelable(this.supplier, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeParcelable(this.journal, flags);
        dest.writeParcelable(this.paymentInfo, flags);
        dest.writeString(this.invoiceDate);
        dest.writeString(this.name);
        dest.writeString(this.paymentDate);
        dest.writeByte(this.approved ? (byte) 1 : (byte) 0);
        dest.writeByte(this.removable ? (byte) 1 : (byte) 0);
        dest.writeValue(this.paid);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeValue(this.total);
        dest.writeValue(this.count);
        dest.writeValue(this.sum);
    }

    protected Invoice(Parcel in) {
        this.id = in.readString();
        this.salesPerson = in.readParcelable(com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson.class.getClassLoader());
        this.workflow = in.readParcelable(Workflow.class.getClassLoader());
        this.supplier = in.readParcelable(com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.journal = in.readParcelable(com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson.class.getClassLoader());
        this.paymentInfo = in.readParcelable(PaymentInfo.class.getClassLoader());
        this.invoiceDate = in.readString();
        this.name = in.readString();
        this.paymentDate = in.readString();
        this.approved = in.readByte() != 0;
        this.removable = in.readByte() != 0;
        this.paid = (Double) in.readValue(Double.class.getClassLoader());
        this.editedBy = in.readParcelable(EditedBy.class.getClassLoader());
        this.total = (Double) in.readValue(Double.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sum = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Invoice> CREATOR = new Creator<Invoice>() {
        @Override
        public Invoice createFromParcel(Parcel source) {
            return new Invoice(source);
        }

        @Override
        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

    @Override
    public int compareTo(@NonNull Invoice invoice) {
        return sum > invoice.sum ? 1 : (sum < invoice.sum ? -1 : 0);
    }

}
