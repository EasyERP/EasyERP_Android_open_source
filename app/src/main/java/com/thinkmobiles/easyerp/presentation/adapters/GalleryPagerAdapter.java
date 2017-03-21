package com.thinkmobiles.easyerp.presentation.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 3/14/2017.
 */

@EBean
public class GalleryPagerAdapter extends PagerAdapter {

    private AppCompatActivity activity;
    private int currentPosition;

    private ArrayList<ImageItem> data = new ArrayList<>();
    private PhotoViewAttacher photoViewAttacher;
    private PhotoViewAttacher.OnViewTapListener onViewTapListener;

    public void updateData(List<ImageItem> imageModels) {
        data.addAll(imageModels);
        notifyDataSetChanged();
    }

    public void setActivity(AppCompatActivity activity, int position) {
        this.activity = activity;
        currentPosition = position;
    }

    public ImageItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.view_gallery_item, container, false);
        ImageView ivFullscreenImage_LGI = (ImageView) view.findViewById(R.id.ivFullscreenImage_VGI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivFullscreenImage_LGI.setTransitionName("gallery" + position);
        }

        photoViewAttacher = new PhotoViewAttacher(ivFullscreenImage_LGI, true);
        photoViewAttacher.setOnViewTapListener(onViewTapListener);

        Picasso.with(container.getContext())
                .load(StringUtil.getImageURL(data.get(position).imageSrc))
                .noFade()
                .into(ivFullscreenImage_LGI, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (currentPosition == position && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ivFullscreenImage_LGI.getViewTreeObserver().addOnPreDrawListener(() -> {
                                        ActivityCompat.startPostponedEnterTransition(activity);
                                        ivFullscreenImage_LGI.requestLayout();
                                        return true;
                                    }
                            );
                        }

                    }

                    @Override
                    public void onError() {
                        if (currentPosition == position) {
                            ActivityCompat.startPostponedEnterTransition(activity);
                        }
                    }
                });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener onViewTapListener) {
        this.onViewTapListener = onViewTapListener;
    }
}
