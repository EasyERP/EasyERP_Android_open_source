package com.thinkmobiles.easyerp.presentation.managers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import com.squareup.picasso.Transformation;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 1/18/2017.
 */

public abstract class ImageHelper {

    public static Observable<Bitmap> getBitmapFromBase64(final String base64) {
        return Observable.fromCallable(() -> getBitmap(base64))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public static Observable<Bitmap> getBitmapFromBase64(final String base64, final Transformation transformation) {
        return Observable.fromCallable(() -> transformation.transform(getBitmap(base64)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    private static Bitmap getBitmap(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.substring(str.indexOf(","));    // cut useless values
            byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else return null;
    }

}
