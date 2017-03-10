package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.inventory.transfers.details.TransferItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/7/2017.
 */

public class ResponseGetTransfers implements Parcelable {
    public int total;
    public ArrayList<TransferItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetTransfers() {
    }

    protected ResponseGetTransfers(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(TransferItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetTransfers> CREATOR = new Parcelable.Creator<ResponseGetTransfers>() {
        @Override
        public ResponseGetTransfers createFromParcel(Parcel source) {
            return new ResponseGetTransfers(source);
        }

        @Override
        public ResponseGetTransfers[] newArray(int size) {
            return new ResponseGetTransfers[size];
        }
    };
}
