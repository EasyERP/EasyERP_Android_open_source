package com.thinkmobiles.easyerp.data.model.crm.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;

import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */
public class ResponseGetInvoice implements Parcelable, IChartModel {

    public int total;
    public List<Invoice> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetInvoice() {
    }

    protected ResponseGetInvoice(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(Invoice.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetInvoice> CREATOR = new Parcelable.Creator<ResponseGetInvoice>() {
        @Override
        public ResponseGetInvoice createFromParcel(Parcel source) {
            return new ResponseGetInvoice(source);
        }

        @Override
        public ResponseGetInvoice[] newArray(int size) {
            return new ResponseGetInvoice[size];
        }
    };
}
