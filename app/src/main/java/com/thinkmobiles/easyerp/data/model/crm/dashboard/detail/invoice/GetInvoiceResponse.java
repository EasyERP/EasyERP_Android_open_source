package com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;

import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/19/2017.)
 */
public class GetInvoiceResponse implements Parcelable, IChartModel {

    public int total;
    public List<InvoiceItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public GetInvoiceResponse() {
    }

    protected GetInvoiceResponse(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(InvoiceItem.CREATOR);
    }

    public static final Parcelable.Creator<GetInvoiceResponse> CREATOR = new Parcelable.Creator<GetInvoiceResponse>() {
        @Override
        public GetInvoiceResponse createFromParcel(Parcel source) {
            return new GetInvoiceResponse(source);
        }

        @Override
        public GetInvoiceResponse[] newArray(int size) {
            return new GetInvoiceResponse[size];
        }
    };
}
