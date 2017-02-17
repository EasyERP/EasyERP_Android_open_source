package com.thinkmobiles.easyerp.presentation.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Alex Michenko (Created on 09.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private final int visibleThreshold = 3;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;
    private int totalItemCount;
    private int lastVisibleItemPosition;

    private LinearLayoutManager mLayoutManager;
    private OnNextPageListener listener;

    public EndlessScrollListener(LinearLayoutManager mLayoutManager, OnNextPageListener listener) {
        this.mLayoutManager = mLayoutManager;
        this.listener = listener;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            loading = listener.onLoadMore();
        }
    }

    public void reset() {
        previousTotalItemCount = 0;
    }
}
