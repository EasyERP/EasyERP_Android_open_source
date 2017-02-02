package com.thinkmobiles.easyerp.data.model.crm.common.alphabet;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class ResponseGetAlphabet implements Parcelable {
    public ArrayList<AlphabetItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ResponseGetAlphabet() {
    }

    protected ResponseGetAlphabet(Parcel in) {
        this.data = in.createTypedArrayList(AlphabetItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetAlphabet> CREATOR = new Parcelable.Creator<ResponseGetAlphabet>() {
        @Override
        public ResponseGetAlphabet createFromParcel(Parcel source) {
            return new ResponseGetAlphabet(source);
        }

        @Override
        public ResponseGetAlphabet[] newArray(int size) {
            return new ResponseGetAlphabet[size];
        }
    };
}
