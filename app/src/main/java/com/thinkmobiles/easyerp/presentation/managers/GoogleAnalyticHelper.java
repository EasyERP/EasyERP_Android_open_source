package com.thinkmobiles.easyerp.presentation.managers;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

/**
 * Created by Lynx on 2/15/2017.
 */
public abstract class GoogleAnalyticHelper {

    private static Tracker tracker;

    public static void init(Application application) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(application);
        tracker = analytics.newTracker(R.xml.my_custom_tracker);
    }

    public static void trackScreenView(BaseView baseView, Configuration configuration) {
        if(tracker == null || !tracker.isInitialized()) {
            Log.d("myLogs", "Google analytic not initialized");
            return;
        }

        tracker.setScreenName(baseView.getScreenName());
        tracker.send(new HitBuilders.ScreenViewBuilder()
                .setCustomDimension(1, getOrientation(configuration))
                .build());
    }

    public static void trackClick(BaseView baseView, Configuration configuration, EventType eventType, String details) {
        if(tracker == null || !tracker.isInitialized()) {
            Log.d("myLogs", "Google analytic not initialized");
            return;
        }
        tracker.setScreenName(baseView.getScreenName());

        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
        builder.setAction(eventType.toString());
        builder.setLabel(details);

        tracker.send(builder.build());
    }

    public enum EventType {
        CLICK_BUTTON("Click button"),
        CLICK_URL("Click url"),
        CLICK_SIDE_MENU_MODULE("Click side menu module"),
        CLICK_SIDE_MENU_ITEM("Click side menu item"),
        CLICK_MENU_ITEM("Click menu item"),
        CLICK_LIST_ITEM("Click list item"),
        CLICK_ATTACHMENT("Click attachment"),
        CLICK_IMAGE("Click image"),
        SET_FILTER("Set filter");

        private final String name;

        EventType(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    private interface OrientationType {
        String PORTRAIT = "Portrait";
        String LANDSCAPE = "Landscape";
    }

    private static String getOrientation(Configuration configuration) {
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                ? OrientationType.LANDSCAPE : OrientationType.PORTRAIT;
    }

}
