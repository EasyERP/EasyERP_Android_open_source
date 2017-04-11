package com.thinkmobiles.easyerp.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Michael Soyma (Created on 4/11/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class UserFavorite implements Parcelable {

    public List<String> dashboards;
    public List<String> reports;

    public void favoriteReport(final String reportId, final boolean isFavorite) {
        reports.remove(reportId);
        if (isFavorite)
            reports.add(reportId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.dashboards);
        dest.writeStringList(this.reports);
    }

    public UserFavorite() {
    }

    protected UserFavorite(Parcel in) {
        this.dashboards = in.createStringArrayList();
        this.reports = in.createStringArrayList();
    }

    public static final Parcelable.Creator<UserFavorite> CREATOR = new Parcelable.Creator<UserFavorite>() {
        @Override
        public UserFavorite createFromParcel(Parcel source) {
            return new UserFavorite(source);
        }

        @Override
        public UserFavorite[] newArray(int size) {
            return new UserFavorite[size];
        }
    };
}
