package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.data.model.LibraryInfo;
import com.thinkmobiles.easyerp.presentation.screens.about.tabs.libraries.LibrariesContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/23/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class LibrariesRepository implements LibrariesContract.LibrariesModel {

    @Override
    public ArrayList<LibraryInfo> getLibraries() {

        ArrayList<LibraryInfo> dependencies = new ArrayList<>();

        dependencies.add(new LibraryInfo("SupportDesign",             "25.0.1",   "AOSP", "Apache License 2.0", "https://source.android.com"));
        dependencies.add(new LibraryInfo("AndroidSupportCardView",    "25.0.1",   "AOSP", "Apache License 2.0", "https://source.android.com"));
        dependencies.add(new LibraryInfo("AndroidSupportPercent",     "25.0.1",   "AOSP", "Apache License 2.0", "https://source.android.com"));
        dependencies.add(new LibraryInfo("AndroidAnnotations",        "4.2.0",    "androidannotations", "Apache License 2.0", "https://github.com/androidannotations/androidannotations"));
        dependencies.add(new LibraryInfo("RecyclerAdapters",          "0.2.6",    "Alex Michenko", "Apache License 2.0", "https://github.com/alex-michenko/RecyclerAdapters"));
        dependencies.add(new LibraryInfo("RxJava",                    "1.1.6",    "ReactiveX", "Apache License 2.0", "https://github.com/ReactiveX/RxJava"));
        dependencies.add(new LibraryInfo("RxBinding",                 "0.2.0",    "JakeWharton", "Apache License 2.0", "https://github.com/JakeWharton/RxBinding"));
        dependencies.add(new LibraryInfo("Retrofit|ConverterGson",    "2.1.0",    "Square", "Apache License 2.0", "https://github.com/square/retrofit/wiki/Converters"));
        dependencies.add(new LibraryInfo("Retrofit|AdapterRxJava",    "2.1.0",    "Square", "Apache License 2.0", "https://mvnrepository.com/artifact/com.squareup.retrofit2/adapter-rxjava/2.0.0-beta3"));
        dependencies.add(new LibraryInfo("Logging-interceptor",       "3.4.1",    "Square", "Apache License 2.0", "https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor"));
        dependencies.add(new LibraryInfo("Picasso",                   "2.5.2",    "Square", "Apache License 2.0", "https://github.com/square/picasso"));
        dependencies.add(new LibraryInfo("MPAndroidChart",            "2.0.8",    "PhilJay", "Apache License 2.0", "https://github.com/PhilJay/MPAndroidChart"));
        dependencies.add(new LibraryInfo("FlowLayout",                "1.0",      "ismaeltoe", "Apache License 2.0", "https://github.com/ismaeltoe/flowlayout"));
        dependencies.add(new LibraryInfo("CircleIndicator",           "1.3.0",    "angakeur", "Apache License 2.0", "https://github.com/ongakuer/CircleIndicator"));

        dependencies.add(new LibraryInfo("Stetho",                    "1.4.2",    "Facebook", "BSD", "http://facebook.github.io/stetho/"));
        dependencies.add(new LibraryInfo("Crashlytic",                "2.6.6",    "Twitter", "Apache License 2.0", "http://try.crashlytics.com"));
        dependencies.add(new LibraryInfo("Google Analytics",          "10.0.1",   "Google Inc.", "Apache License 2.0", "https://developers.google.com/analytics/devguides/collection/android"));
        dependencies.add(new LibraryInfo("UXCam",                     "2.5.7",    "UXCam Inc.", "MIT", "https://uxcam.com/"));

        return dependencies;
    }
}
