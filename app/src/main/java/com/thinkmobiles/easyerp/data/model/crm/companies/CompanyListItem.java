package com.thinkmobiles.easyerp.data.model.crm.companies;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.CreatedEditedBy;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompanyListItem implements Parcelable {
    public String id;
    public Address address; //only country
    public CreatedEditedBy createdBy;
    public CreatedEditedBy editedBy;
    public String email;
    public String fullName;
    public Name name;
    public Phone phones;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeString(this.email);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.phones, flags);
    }

    public CompanyListItem() {
    }

    protected CompanyListItem(Parcel in) {
        this.id = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedBy.class.getClassLoader());
        this.email = in.readString();
        this.fullName = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.phones = in.readParcelable(Phone.class.getClassLoader());
    }

    public static final Parcelable.Creator<CompanyListItem> CREATOR = new Parcelable.Creator<CompanyListItem>() {
        @Override
        public CompanyListItem createFromParcel(Parcel source) {
            return new CompanyListItem(source);
        }

        @Override
        public CompanyListItem[] newArray(int size) {
            return new CompanyListItem[size];
        }
    };
}
