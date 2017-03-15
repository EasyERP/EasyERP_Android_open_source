package com.thinkmobiles.easyerp.presentation.screens.gallery;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Lynx on 3/14/2017.
 */

public interface GalleryContract {
    interface GalleryView extends BaseView<GalleryPresenter> {
        void displayGallery(List<ImageItem> imageModels);
        void displayIndicator(int current, int total);
        void displayTitle(String title);

        void showSupportViews(boolean isShown);
        void close();
    }
    interface GalleryPresenter extends BasePresenter {
        void back();
        void screenClicked();
    }
    interface GalleryModel extends BaseModel {

    }
}
