package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */

public class PaymentInfo implements Parcelable {

    /**
     {
        "taxes": 10,
        "discount": null,
        "unTaxed": 1000,
        "balance": 0,
        "total": 1010
     }
     */

    public Double taxes;
    public Double discount;
    public Double unTaxed;
    public Double balance;
    public Double total;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.taxes);
        dest.writeValue(this.discount);
        dest.writeValue(this.unTaxed);
        dest.writeValue(this.balance);
        dest.writeValue(this.total);
    }

    public PaymentInfo() {
    }

    protected PaymentInfo(Parcel in) {
        this.taxes = (Double) in.readValue(Double.class.getClassLoader());
        this.discount = (Double) in.readValue(Double.class.getClassLoader());
        this.unTaxed = (Double) in.readValue(Double.class.getClassLoader());
        this.balance = (Double) in.readValue(Double.class.getClassLoader());
        this.total = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<PaymentInfo> CREATOR = new Parcelable.Creator<PaymentInfo>() {
        @Override
        public PaymentInfo createFromParcel(Parcel source) {
            return new PaymentInfo(source);
        }

        @Override
        public PaymentInfo[] newArray(int size) {
            return new PaymentInfo[size];
        }
    };

}
