package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;
import com.thinkmobiles.easyerp.data.model.crm.leads.EditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.Workflow;

/**
 * Created by Asus_Dev on 1/19/2017.
 */

public class InvoiceItem implements Parcelable, IChartModel {

    @SerializedName("_id")
    public String id;
    public com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson salesPerson;
    public Workflow workflow;
    public com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson supplier;
    public Currency currency;
    public com.thinkmobiles.easyerp.data.model.crm.leads.detail.SalesPerson journal;
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

    public InvoiceItem() {
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

    protected InvoiceItem(Parcel in) {
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

    public static final Creator<InvoiceItem> CREATOR = new Creator<InvoiceItem>() {
        @Override
        public InvoiceItem createFromParcel(Parcel source) {
            return new InvoiceItem(source);
        }

        @Override
        public InvoiceItem[] newArray(int size) {
            return new InvoiceItem[size];
        }
    };
}
