package com.thinkmobiles.easyerp.presentation.screens.about.tabs.libraries;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.LibrariesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.LibrariesAdapter;
import com.thinkmobiles.easyerp.presentation.base.BaseSupportFragment;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LibraryDH;
import com.thinkmobiles.easyerp.presentation.screens.about.AboutUsActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/23/2017.
 */

@EFragment(R.layout.fragment_libraries)
public class LibrariesFragment extends BaseSupportFragment<AboutUsActivity> implements LibrariesContract.LibrariesView {

    private LibrariesContract.LibrariesPresenter presenter;

    private GridLayoutManager glm;

    @ViewById
    protected RecyclerView rvLibraries_FL;

    @Bean
    protected LibrariesRepository librariesRepository;
    @Bean
    protected LibrariesAdapter librariesAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new LibrariesPresenter(this, librariesRepository);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3;
        glm = new GridLayoutManager(getActivity(), spanCount);
        rvLibraries_FL.setLayoutManager(glm);
        rvLibraries_FL.setAdapter(librariesAdapter);
        librariesAdapter.setOnCardClickListener((view, position, viewType) -> presenter.openGithubLink(librariesAdapter.getItem(position).getLibraryInfo().link));

        presenter.subscribe();
    }

    @Override
    public void displayLibraries(ArrayList<LibraryDH> libraryDHs) {
        librariesAdapter.setListDH(libraryDHs);
    }

    @Override
    public void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void setPresenter(LibrariesContract.LibrariesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "About us (Libraries) screen";
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        glm.setSpanCount(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null) presenter.unsubscribe();
    }
}
