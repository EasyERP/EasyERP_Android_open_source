package com.thinkmobiles.easyerp.data.model.hr.birthdays;

import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class EmployeeWithBirthday extends EmployeeItem {

    public int daysForBirth;
    public int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.daysForBirth);
        dest.writeInt(this.age);
    }

    public EmployeeWithBirthday() {
    }

    protected EmployeeWithBirthday(Parcel in) {
        super(in);
        this.daysForBirth = in.readInt();
        this.age = in.readInt();
    }

    public static final Creator<EmployeeWithBirthday> CREATOR = new Creator<EmployeeWithBirthday>() {
        @Override
        public EmployeeWithBirthday createFromParcel(Parcel source) {
            return new EmployeeWithBirthday(source);
        }

        @Override
        public EmployeeWithBirthday[] newArray(int size) {
            return new EmployeeWithBirthday[size];
        }
    };
}
