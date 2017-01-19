package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Asus_Dev on 1/19/2017.
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

    public Integer taxes;
    public Integer discount;
    public Integer unTaxed;
    public Integer balance;
    public Integer total;

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
        this.taxes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.discount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.unTaxed = (Integer) in.readValue(Integer.class.getClassLoader());
        this.balance = (Integer) in.readValue(Integer.class.getClassLoader());
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
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
