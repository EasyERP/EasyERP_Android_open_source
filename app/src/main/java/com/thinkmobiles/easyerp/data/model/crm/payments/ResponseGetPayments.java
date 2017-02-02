package com.thinkmobiles.easyerp.data.model.crm.payments;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ResponseGetPayments implements Parcelable {

    public int total;
    public List<Payment> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetPayments() {
    }

    protected ResponseGetPayments(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(Payment.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetPayments> CREATOR = new Parcelable.Creator<ResponseGetPayments>() {
        @Override
        public ResponseGetPayments createFromParcel(Parcel source) {
            return new ResponseGetPayments(source);
        }

        @Override
        public ResponseGetPayments[] newArray(int size) {
            return new ResponseGetPayments[size];
        }
    };
}
