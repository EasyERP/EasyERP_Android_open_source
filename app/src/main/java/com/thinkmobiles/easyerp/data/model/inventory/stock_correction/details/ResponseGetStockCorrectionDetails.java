package com.thinkmobiles.easyerp.data.model.inventory.stock_correction.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.OrderRow;

import java.util.ArrayList;

/**
 * Created by samson on 10.03.17.
 */

public class ResponseGetStockCorrectionDetails implements Parcelable {

    @SerializedName("_id")
    public String _id;
    public String description;
    public ArrayList<OrderRow> orderRows;
    public String order;
    public CreatedEditedBy createdBy;
    public String date;
    public FilterItem warehouse;

    public ResponseGetStockCorrectionDetails() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.description);
        dest.writeTypedList(this.orderRows);
        dest.writeString(this.order);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeString(this.date);
        dest.writeParcelable(this.warehouse, flags);
    }

    protected ResponseGetStockCorrectionDetails(Parcel in) {
        this._id = in.readString();
        this.description = in.readString();
        this.orderRows = in.createTypedArrayList(OrderRow.CREATOR);
        this.order = in.readString();
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.date = in.readString();
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
    }

    public static final Creator<ResponseGetStockCorrectionDetails> CREATOR = new Creator<ResponseGetStockCorrectionDetails>() {
        @Override
        public ResponseGetStockCorrectionDetails createFromParcel(Parcel source) {
            return new ResponseGetStockCorrectionDetails(source);
        }

        @Override
        public ResponseGetStockCorrectionDetails[] newArray(int size) {
            return new ResponseGetStockCorrectionDetails[size];
        }
    };
}
