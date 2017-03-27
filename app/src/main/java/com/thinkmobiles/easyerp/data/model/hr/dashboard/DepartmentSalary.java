package com.thinkmobiles.easyerp.data.model.hr.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class DepartmentSalary implements Parcelable {

    @SerializedName("_id")
    private List<String> id;
    public int salary;

    public String getId() {
        if (id != null && id.size() > 0)
            return id.get(0);
        else return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.id);
        dest.writeInt(this.salary);
    }

    public DepartmentSalary() {
    }

    protected DepartmentSalary(Parcel in) {
        this.id = in.createStringArrayList();
        this.salary = in.readInt();
    }

    public static final Parcelable.Creator<DepartmentSalary> CREATOR = new Parcelable.Creator<DepartmentSalary>() {
        @Override
        public DepartmentSalary createFromParcel(Parcel source) {
            return new DepartmentSalary(source);
        }

        @Override
        public DepartmentSalary[] newArray(int size) {
            return new DepartmentSalary[size];
        }
    };
}
