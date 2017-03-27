package com.thinkmobiles.easyerp.presentation.screens.gallery;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.presentation.adapters.GalleryPagerAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.FixedZoomViewPager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Lynx on 3/14/2017.
 */

@Fullscreen
@EActivity(R.layout.activity_gallery)
public class GalleryActivity extends AppCompatActivity implements GalleryContract.GalleryView {

    private GalleryContract.GalleryPresenter presenter;

    @Extra
    protected String title;
    @Extra
    protected int position;
    @Extra
    protected ArrayList<ImageItem> imageItems;

    @ViewById
    protected TextView tvPageIndicator_AG;
    @ViewById
    protected Toolbar toolbarGallery_AG;
    @ViewById
    protected FixedZoomViewPager vpFullscreenImage_AG;

    @StringRes(R.string.pattern_gallery_indicator)
    protected String patternIndicator;

    @Bean
    protected GalleryPagerAdapter galleryPagerAdapter;

    private ObjectAnimator toolbarAnimator;
    private ObjectAnimator indicatorAnimator;
    private AnimatorSet animatorSet;

    @AfterInject
    @Override
    public void initPresenter() {
        ActivityCompat.postponeEnterTransition(this);

        new GalleryPresenter(this, position, imageItems, title);
    }

    @AfterViews
    protected void initUI() {
        galleryPagerAdapter.setActivity(this, position);
        setupToolbar();
        prepareAnimations();

        vpFullscreenImage_AG.setAdapter(galleryPagerAdapter);
        vpFullscreenImage_AG.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                displayIndicator(position, galleryPagerAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        galleryPagerAdapter.setOnViewTapListener((view, x, y) -> presenter.screenClicked());

        presenter.subscribe();
    }

    @Override
    public void setPresenter(GalleryContract.GalleryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Gallery screen";
    }

    @Override
    public void displayGallery(List<ImageItem> imageModels) {
        galleryPagerAdapter.updateData(imageModels);
        vpFullscreenImage_AG.setCurrentItem(position);
    }

    @Override
    public void displayIndicator(int current, int total) {
        tvPageIndicator_AG.setText(String.format(Locale.US, patternIndicator, current + 1, total));
    }

    @Override
    public void displayTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showSupportViews(boolean isShown) {
        if (isShown) {
            toolbarAnimator.setFloatValues(0, 1);
            indicatorAnimator.setFloatValues(0, 1);
            toolbarGallery_AG.setVisibility(View.VISIBLE);
            tvPageIndicator_AG.setVisibility(View.VISIBLE);
        } else {
            toolbarAnimator.setFloatValues(1, 0);
            indicatorAnimator.setFloatValues(1, 0);
        }
        if (!isShown)
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    toolbarGallery_AG.setVisibility(View.GONE);
                    tvPageIndicator_AG.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        else
            animatorSet.removeAllListeners();
        animatorSet.start();
    }

    @Override
    public void close() {
        finish();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbarGallery_AG);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    private void prepareAnimations() {
        animatorSet = new AnimatorSet();
        toolbarAnimator = ObjectAnimator.ofFloat(toolbarGallery_AG, View.ALPHA, 0, 1);
        indicatorAnimator = ObjectAnimator.ofFloat(tvPageIndicator_AG, View.ALPHA, 0, 1);
        animatorSet.playTogether(toolbarAnimator, indicatorAnimator);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finishAfterTransition() {
        if (vpFullscreenImage_AG.getCurrentItem() == position)
            super.finishAfterTransition();
        else
            finish();
    }
}
