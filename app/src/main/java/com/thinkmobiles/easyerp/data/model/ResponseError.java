package com.thinkmobiles.easyerp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 1/13/2017.
 */

public class ResponseError implements Parcelable {
    public String error;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
    }

    public ResponseError() {
    }

    protected ResponseError(Parcel in) {
        this.error = in.readString();
    }

    public static final Parcelable.Creator<ResponseError> CREATOR = new Parcelable.Creator<ResponseError>() {
        @Override
        public ResponseError createFromParcel(Parcel source) {
            return new ResponseError(source);
        }

        @Override
        public ResponseError[] newArray(int size) {
            return new ResponseError[size];
        }
    };
}
