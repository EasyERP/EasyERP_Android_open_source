package com.thinkmobiles.easyerp.data.model.crm.persons;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

public class ResponseGetPersons implements Parcelable {
    public int total;
    public ArrayList<PersonModel> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetPersons() {
    }

    protected ResponseGetPersons(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(PersonModel.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetPersons> CREATOR = new Parcelable.Creator<ResponseGetPersons>() {
        @Override
        public ResponseGetPersons createFromParcel(Parcel source) {
            return new ResponseGetPersons(source);
        }

        @Override
        public ResponseGetPersons[] newArray(int size) {
            return new ResponseGetPersons[size];
        }
    };
}
