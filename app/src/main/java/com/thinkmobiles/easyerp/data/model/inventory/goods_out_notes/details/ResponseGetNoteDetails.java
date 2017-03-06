package com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.order.detail.ResponseGetOrderDetails;

import java.util.ArrayList;

/**
 * Created by samson on 06.03.17.
 */

public class ResponseGetNoteDetails implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String name;
    public OutNoteStatus status;
    public ArrayList<OrderRow> orderRows;
    public ResponseGetOrderDetails order;
    public CreatedEditedBy editedBy;
    public String description;
    public CreatedEditedBy createdBy;
    public String date;


    public ResponseGetNoteDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.status, flags);
        dest.writeTypedList(this.orderRows);
        dest.writeParcelable(this.order, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.description);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
    }

    protected ResponseGetNoteDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.status = in.readParcelable(OutNoteStatus.class.getClassLoader());
        this.orderRows = in.createTypedArrayList(OrderRow.CREATOR);
        this.order = in.readParcelable(ResponseGetOrderDetails.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.description = in.readString();
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.date = in.readString();
    }

    public static final Creator<ResponseGetNoteDetails> CREATOR = new Creator<ResponseGetNoteDetails>() {
        @Override
        public ResponseGetNoteDetails createFromParcel(Parcel source) {
            return new ResponseGetNoteDetails(source);
        }

        @Override
        public ResponseGetNoteDetails[] newArray(int size) {
            return new ResponseGetNoteDetails[size];
        }
    };
}
