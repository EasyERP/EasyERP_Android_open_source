package com.thinkmobiles.easyerp.data.model.crm.invoice.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.invoice.Currency;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;

/**
 * @author Alex Michenko (Created on 07.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class InvoicePayment implements Parcelable {

    /**
     * payments: [
     {
     _id: "58887594b04addfb04856864",
     paymentRef: "",
     createdBy: {
     date: "2017-01-25T09:53:24.609Z",
     user: {
     _id: "585cdc6ed210f7ec05c45f1f",
     login: "testAdmin"
     }
     },
     currency: {
     rate: 0.93738,
     _id: {
     _id: "EUR",
     symbol: "€"
     }
     },
     name: "PP_42",
     date: "2017-01-25T09:53:24.000Z",
     paidAmount: 9737400
     }
     ],
     */

    @SerializedName("_id")
    public String id;
    public String paymentRef;
    public CreatedEditedBy createdBy;
    public Currency currency;
    public String name;
    public String date;
    public Double paidAmount;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.paymentRef);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.currency, flags);
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeValue(this.paidAmount);
    }

    public InvoicePayment() {
    }

    protected InvoicePayment(Parcel in) {
        this.id = in.readString();
        this.paymentRef = in.readString();
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.name = in.readString();
        this.date = in.readString();
        this.paidAmount = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<InvoicePayment> CREATOR = new Parcelable.Creator<InvoicePayment>() {
        @Override
        public InvoicePayment createFromParcel(Parcel source) {
            return new InvoicePayment(source);
        }

        @Override
        public InvoicePayment[] newArray(int size) {
            return new InvoicePayment[size];
        }
    };
}
