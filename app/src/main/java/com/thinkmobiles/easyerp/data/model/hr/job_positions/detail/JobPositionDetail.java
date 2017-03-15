package com.thinkmobiles.easyerp.data.model.hr.job_positions.detail;

import android.os.Parcel;

import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;

/**
 * @author Michael Soyma (Created on 3/15/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class JobPositionDetail extends JobPosition {

    public Groups groups;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.groups, flags);
    }

    public JobPositionDetail() {
    }

    protected JobPositionDetail(Parcel in) {
        super(in);
        this.groups = in.readParcelable(Groups.class.getClassLoader());
    }

    public static final Creator<JobPositionDetail> CREATOR = new Creator<JobPositionDetail>() {
        @Override
        public JobPositionDetail createFromParcel(Parcel source) {
            return new JobPositionDetail(source);
        }

        @Override
        public JobPositionDetail[] newArray(int size) {
            return new JobPositionDetail[size];
        }
    };
}
