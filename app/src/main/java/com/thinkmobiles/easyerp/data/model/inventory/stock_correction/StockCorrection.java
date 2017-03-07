package com.thinkmobiles.easyerp.data.model.inventory.stock_correction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class StockCorrection implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String description;
    public int total;
    public CreatedEditedBy createdBy;
    public CreatedEditedBy editedBy;
    public FilterItem warehouse;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeInt(this.total);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.warehouse, flags);
    }

    public StockCorrection() {
    }

    protected StockCorrection(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.total = in.readInt();
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.warehouse = in.readParcelable(FilterItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<StockCorrection> CREATOR = new Parcelable.Creator<StockCorrection>() {
        @Override
        public StockCorrection createFromParcel(Parcel source) {
            return new StockCorrection(source);
        }

        @Override
        public StockCorrection[] newArray(int size) {
            return new StockCorrection[size];
        }
    };
}
