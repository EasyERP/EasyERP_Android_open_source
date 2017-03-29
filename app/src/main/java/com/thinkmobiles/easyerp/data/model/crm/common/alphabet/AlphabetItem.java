package com.thinkmobiles.easyerp.data.model.crm.common.alphabet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lynx on 1/20/2017.
 */

public class AlphabetItem implements Parcelable {

    public AlphabetItem(String id) {
        this.id = id;
    }

    @SerializedName("_id")
    public String id;

    public AlphabetItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    protected AlphabetItem(Parcel in) {
        this.id = in.readString();
    }

    public static final Creator<AlphabetItem> CREATOR = new Creator<AlphabetItem>() {
        @Override
        public AlphabetItem createFromParcel(Parcel source) {
            return new AlphabetItem(source);
        }

        @Override
        public AlphabetItem[] newArray(int size) {
            return new AlphabetItem[size];
        }
    };
}
