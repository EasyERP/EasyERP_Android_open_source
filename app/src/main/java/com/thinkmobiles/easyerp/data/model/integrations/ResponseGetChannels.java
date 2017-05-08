package com.thinkmobiles.easyerp.data.model.integrations;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ResponseGetChannels implements Parcelable {

    public ArrayList<Channel> result;
    public Stats stats;

    public ArrayList<Channel> getPackedChannels() {
        for (Channel channel: result) {
            channel.conflictProducts = getCountInStats(channel.id, stats.conflictProducts);
            channel.importedOrders = getCountInStats(channel.id, stats.importedOrders);
            channel.importedProducts = getCountInStats(channel.id, stats.importedProducts);
            channel.unlinkedOrders = getCountInStats(channel.id, stats.unlinkedOrders);
        }
        return result;
    }

    private int getCountInStats(final String channelId, final List<StatsCount> stats) {
        for (StatsCount statsCount: stats)
            if (channelId.equals(statsCount.id))
                return statsCount.count;
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.result);
        dest.writeParcelable(this.stats, flags);
    }

    public ResponseGetChannels() {
    }

    protected ResponseGetChannels(Parcel in) {
        this.result = in.createTypedArrayList(Channel.CREATOR);
        this.stats = in.readParcelable(Stats.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResponseGetChannels> CREATOR = new Parcelable.Creator<ResponseGetChannels>() {
        @Override
        public ResponseGetChannels createFromParcel(Parcel source) {
            return new ResponseGetChannels(source);
        }

        @Override
        public ResponseGetChannels[] newArray(int size) {
            return new ResponseGetChannels[size];
        }
    };
}
