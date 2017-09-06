package com.thinkmobiles.easyerp.presentation.base.rules.master.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michenko.simpleadapter.RecyclerDH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.listeners.EndlessScrollListener;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

@EFragment
public abstract class MasterListFragment extends ContentFragment implements MasterListView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }

    protected abstract MasterListPresenter getPresenter();
    protected abstract SimpleRecyclerAdapter getAdapter();

    @ViewById
    protected RecyclerView listRecycler;

    protected EndlessScrollListener scrollListener;

    @AfterViews
    protected void initList() {
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());

        scrollListener = new EndlessScrollListener(recyclerLayoutManager, () -> {
            if (srlHolderRefresh.isRefreshing() || getCurrentProgressType() == Constants.ProgressType.BOTTOM) {
                return false;
            } else {
                getPresenter().loadNextPage();
                return true;
            }
        });

        listRecycler.setLayoutManager(recyclerLayoutManager);
        listRecycler.addOnScrollListener(scrollListener);
        getAdapter().setOnCardClickListener((view, position, viewType) -> getPresenter().clickItem(position));
        listRecycler.setAdapter(getAdapter());
        getPresenter().subscribe();
    }

    protected void onRefreshData() {
        super.onRefreshData();
        scrollListener.reset();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setDataList(ArrayList<? extends RecyclerDH> list, boolean needClear) {
        if (needClear) {
            getAdapter().setListDH(list);
        } else {
            getAdapter().addListDH(list);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void changeItem(RecyclerDH dh, int position) {
        getAdapter().changeItem(dh, position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateItem(int position) {
        getAdapter().updateItem(position);
    }

    @Override
    public void displayErrorToast(Constants.ErrorType errorType) {
        super.displayErrorToast(errorType);
        scrollListener.reset();
    }

    @Override
    protected View getHiddenView() {
        return listRecycler;
    }

    @Override
    protected boolean showErrorState(Constants.ErrorType errorType) {
        if (!super.showErrorState(errorType)) {
            switch (errorType) {
                case LIST_EMPTY:
                    hideHeader();
                    btnHolderTry.setVisibility(View.GONE);
                    srlHolderRefresh.setEnabled(true);
                    return true;
                default:
                    return false;
            }
        } else {
            return true;
        }
    }

    private void hideHeader() {
        View header = getHeaderView();
        if (header != null)
            header.setVisibility(View.GONE);
    }

    protected View getHeaderView() {
        return null;
    }
}
