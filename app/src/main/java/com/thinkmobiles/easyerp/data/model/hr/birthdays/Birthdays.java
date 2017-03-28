package com.thinkmobiles.easyerp.data.model.hr.birthdays;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class Birthdays implements Parcelable {

    public List<EmployeeWithBirthday> weekly;
    public List<EmployeeWithBirthday> nextweek;
    public List<EmployeeWithBirthday> monthly;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.weekly);
        dest.writeTypedList(this.nextweek);
        dest.writeTypedList(this.monthly);
    }

    public Birthdays() {
    }

    protected Birthdays(Parcel in) {
        this.weekly = in.createTypedArrayList(EmployeeWithBirthday.CREATOR);
        this.nextweek = in.createTypedArrayList(EmployeeWithBirthday.CREATOR);
        this.monthly = in.createTypedArrayList(EmployeeWithBirthday.CREATOR);
    }

    public static final Parcelable.Creator<Birthdays> CREATOR = new Parcelable.Creator<Birthdays>() {
        @Override
        public Birthdays createFromParcel(Parcel source) {
            return new Birthdays(source);
        }

        @Override
        public Birthdays[] newArray(int size) {
            return new Birthdays[size];
        }
    };
}