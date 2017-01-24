package com.thinkmobiles.easyerp.data.model.crm.persons.alphabet;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.AlphabetItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class ResponseGetPersonsAlphabet implements Parcelable {
    public ArrayList<AlphabetItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ResponseGetPersonsAlphabet() {
    }

    protected ResponseGetPersonsAlphabet(Parcel in) {
        this.data = in.createTypedArrayList(AlphabetItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetPersonsAlphabet> CREATOR = new Parcelable.Creator<ResponseGetPersonsAlphabet>() {
        @Override
        public ResponseGetPersonsAlphabet createFromParcel(Parcel source) {
            return new ResponseGetPersonsAlphabet(source);
        }

        @Override
        public ResponseGetPersonsAlphabet[] newArray(int size) {
            return new ResponseGetPersonsAlphabet[size];
        }
    };
}
