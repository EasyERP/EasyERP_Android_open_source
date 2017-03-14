package com.thinkmobiles.easyerp.presentation.screens.gallery;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/14/2017.
 */

public class GalleryPresenter implements GalleryContract.GalleryPresenter {

    private GalleryContract.GalleryView view;
    private int position;
    private ArrayList<ImageItem> imageItems;
    private String title;

    private boolean isSupportViewsVisible = true;

    public GalleryPresenter(GalleryContract.GalleryView view, int position, ArrayList<ImageItem> imageItems, String title) {
        this.view = view;
        this.position = position;
        this.imageItems = imageItems;
        this.title = title;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.displayTitle(title);
        view.displayIndicator(position, imageItems.size());
        view.displayGallery(imageItems);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void back() {
        view.close();
    }

    @Override
    public void screenClicked() {
        isSupportViewsVisible = !isSupportViewsVisible;
        view.showSupportViews(isSupportViewsVisible);
    }
}
