package com.thinkmobiles.easyerp.data.model.hr.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class VacationStatistic implements Parcelable {

    public int personal;
    public int education;
    public int vacation;
    public int sick;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.personal);
        dest.writeInt(this.education);
        dest.writeInt(this.vacation);
        dest.writeInt(this.sick);
    }

    public VacationStatistic() {
    }

    protected VacationStatistic(Parcel in) {
        this.personal = in.readInt();
        this.education = in.readInt();
        this.vacation = in.readInt();
        this.sick = in.readInt();
    }

    public static final Parcelable.Creator<VacationStatistic> CREATOR = new Parcelable.Creator<VacationStatistic>() {
        @Override
        public VacationStatistic createFromParcel(Parcel source) {
            return new VacationStatistic(source);
        }

        @Override
        public VacationStatistic[] newArray(int size) {
            return new VacationStatistic[size];
        }
    };
}
