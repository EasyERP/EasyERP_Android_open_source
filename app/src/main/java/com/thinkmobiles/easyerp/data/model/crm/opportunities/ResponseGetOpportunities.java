package com.thinkmobiles.easyerp.data.model.crm.opportunities;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.opportunities.list_item.OpportunityListItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/30/2017.
 */

public class ResponseGetOpportunities implements Parcelable {
    public int total;
    public ArrayList<OpportunityListItem> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeTypedList(this.data);
    }

    public ResponseGetOpportunities() {
    }

    protected ResponseGetOpportunities(Parcel in) {
        this.total = in.readInt();
        this.data = in.createTypedArrayList(OpportunityListItem.CREATOR);
    }

    public static final Parcelable.Creator<ResponseGetOpportunities> CREATOR = new Parcelable.Creator<ResponseGetOpportunities>() {
        @Override
        public ResponseGetOpportunities createFromParcel(Parcel source) {
            return new ResponseGetOpportunities(source);
        }

        @Override
        public ResponseGetOpportunities[] newArray(int size) {
            return new ResponseGetOpportunities[size];
        }
    };
}
