package com.thinkmobiles.easyerp.data.model.hr.employees.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Name;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Phone;
import com.thinkmobiles.easyerp.data.model.crm.persons.details.CreatedEditedUserString;

/**
 * Created by Lynx on 3/13/2017.
 */

public class EmployeeItem implements Parcelable {
    @SerializedName("_id")
    public String id;
    public int total;
    public Manager manager;
    public FilterItem jobPosition;
    public FilterItem department;
    public CreatedEditedUserString createdBy;
    public CreatedEditedUserString editedBy;
    public Name name;
    public String dateBirth;
    public String skype;
    public String workEmail;
    public Phone workPhones;
    public String jobType;
    public boolean isEmployee;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.total);
        dest.writeParcelable(this.manager, flags);
        dest.writeParcelable(this.jobPosition, flags);
        dest.writeParcelable(this.department, flags);
        dest.writeParcelable(this.createdBy, flags);
        dest.writeParcelable(this.editedBy, flags);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.dateBirth);
        dest.writeString(this.skype);
        dest.writeString(this.workEmail);
        dest.writeParcelable(this.workPhones, flags);
        dest.writeString(this.jobType);
        dest.writeByte(this.isEmployee ? (byte) 1 : (byte) 0);
    }

    public EmployeeItem() {
    }

    protected EmployeeItem(Parcel in) {
        this.id = in.readString();
        this.total = in.readInt();
        this.manager = in.readParcelable(Manager.class.getClassLoader());
        this.jobPosition = in.readParcelable(FilterItem.class.getClassLoader());
        this.department = in.readParcelable(FilterItem.class.getClassLoader());
        this.createdBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.editedBy = in.readParcelable(CreatedEditedUserString.class.getClassLoader());
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.dateBirth = in.readString();
        this.skype = in.readString();
        this.workEmail = in.readString();
        this.workPhones = in.readParcelable(Phone.class.getClassLoader());
        this.jobType = in.readString();
        this.isEmployee = in.readByte() != 0;
    }

    public static final Parcelable.Creator<EmployeeItem> CREATOR = new Parcelable.Creator<EmployeeItem>() {
        @Override
        public EmployeeItem createFromParcel(Parcel source) {
            return new EmployeeItem(source);
        }

        @Override
        public EmployeeItem[] newArray(int size) {
            return new EmployeeItem[size];
        }
    };
}
