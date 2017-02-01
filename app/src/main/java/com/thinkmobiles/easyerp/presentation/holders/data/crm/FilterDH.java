package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import android.os.Parcel;
import android.os.Parcelable;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.crm.leads.filter.FilterItem;

/**
 * Created by samson on 26.01.17.
 */

public class FilterDH extends RecyclerDH implements Parcelable {

    public String id;
    public String name;
    public boolean selected;


    public FilterDH(FilterItem item, boolean selected) {
        this(item);
        this.selected = selected;
    }

    public FilterDH(FilterItem item) {
        this.id = item.id;
        this.name = item.name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    protected FilterDH(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.selected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<FilterDH> CREATOR = new Parcelable.Creator<FilterDH>() {
        @Override
        public FilterDH createFromParcel(Parcel source) {
            return new FilterDH(source);
        }

        @Override
        public FilterDH[] newArray(int size) {
            return new FilterDH[size];
        }
    };
}
