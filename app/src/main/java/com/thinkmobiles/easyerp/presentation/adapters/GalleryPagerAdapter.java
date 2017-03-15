package com.thinkmobiles.easyerp.presentation.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 3/14/2017.
 */

@EBean
public class GalleryPagerAdapter extends PagerAdapter {

    private ArrayList<ImageItem> data = new ArrayList<>();
    private PhotoViewAttacher photoViewAttacher;
    private PhotoViewAttacher.OnViewTapListener onViewTapListener;

    public void updateData(List<ImageItem> imageModels) {
        data.addAll(imageModels);
        notifyDataSetChanged();
    }

    public ImageItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.view_gallery_item, container, false);
        ImageView ivFullscreenImage_LGI = (ImageView) view.findViewById(R.id.ivFullscreenImage_VGI);

        Picasso.with(container.getContext())
                .load(StringUtil.getImageURL(data.get(position).imageSrc))
                .into(ivFullscreenImage_LGI, new Callback() {
                    @Override
                    public void onSuccess() {
                        photoViewAttacher = new PhotoViewAttacher(ivFullscreenImage_LGI, true);
                        photoViewAttacher.setOnViewTapListener(onViewTapListener);
//                        photoViewAttacher.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    }

                    @Override
                    public void onError() {

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
