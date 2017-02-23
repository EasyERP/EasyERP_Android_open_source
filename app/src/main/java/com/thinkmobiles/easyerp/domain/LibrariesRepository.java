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
        ArrayList<LibraryInfo> result = new ArrayList<>();
        result.add(new LibraryInfo("SupportDesign",             "25.0.1",   "AOSP", "Apache Version 2.0", "https://source.android.com"));
        result.add(new LibraryInfo("AndroidSupportCardView",    "25.0.1",   "AOSP", "Apache Version 2.0", "https://source.android.com"));
        result.add(new LibraryInfo("AndroidSupportPercent",     "25.0.1",   "AOSP", "Apache Version 2.0", "https://source.android.com"));
        result.add(new LibraryInfo("AndroidAnnotations",        "4.2.0",    "androidannotations", "Apache Version 2.0", "https://github.com/androidannotations/androidannotations"));
        result.add(new LibraryInfo("RecyclerAdapters",          "0.2.6",    "Alex Michenko", "Apache Version 2.0", "https://github.com/alex-michenko/RecyclerAdapters"));
        result.add(new LibraryInfo("RxJava",                    "1.1.6",    "ReactiveX", "Apache Version 2.0", "https://github.com/ReactiveX/RxJava"));
        result.add(new LibraryInfo("RxBinding",                 "0.2.0",    "JakeWharton", "Apache Version 2.0", "https://github.com/JakeWharton/RxBinding"));
        result.add(new LibraryInfo("Retrofit|ConverterGson",    "2.1.0",    "Square", "Apache Version 2.0", "https://github.com/square/retrofit/wiki/Converters"));
        result.add(new LibraryInfo("Retrofit|AdapterRxJava",    "2.1.0",    "Square", "Apache Version 2.0", "https://mvnrepository.com/artifact/com.squareup.retrofit2/adapter-rxjava/2.0.0-beta3"));
        result.add(new LibraryInfo("Logging-interceptor",       "3.4.1",    "Square", "Apache Version 2.0", "https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor"));
        result.add(new LibraryInfo("Picasso",                   "2.5.2",    "Square", "Apache Version 2.0", "https://github.com/square/picasso"));
        result.add(new LibraryInfo("MPAndroidChart",            "2.0.8",    "PhilJay", "Apache Version 2.0", "https://github.com/PhilJay/MPAndroidChart"));
        result.add(new LibraryInfo("FlowLayout",                "1.0",      "ismaeltoe", "Apache Version 2.0", "https://github.com/ismaeltoe/flowlayout"));
        result.add(new LibraryInfo("CircleIndicator",           "1.3.0",    "angakeur", "Apache Version 2.0", "https://github.com/ongakuer/CircleIndicator"));

        result.add(new LibraryInfo("Stetho",                    "1.4.2",    "Facebook", "BSD", "http://facebook.github.io/stetho/"));
        result.add(new LibraryInfo("Crashlytic",                "2.6.6",    "Twitter", "Apache Version 2.0", "http://try.crashlytics.com/"));

        return result;
    }
}
