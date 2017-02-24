package com.thinkmobiles.easyerp.presentation.screens.about.tabs.libraries;

import com.thinkmobiles.easyerp.data.model.LibraryInfo;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LibraryDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/23/2017.
 */

public class LibrariesPresenter implements LibrariesContract.LibrariesPresenter {

    private LibrariesContract.LibrariesView view;
    private LibrariesContract.LibrariesModel model;

    public LibrariesPresenter(LibrariesContract.LibrariesView view, LibrariesContract.LibrariesModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void openGithubLink(String link) {
        view.openLink(link);
    }

    @Override
    public void subscribe() {
        view.displayLibraries(prepareLibraryDHs(model.getLibraries()));
    }

    @Override
    public void unsubscribe() {

    }

    private ArrayList<LibraryDH> prepareLibraryDHs(ArrayList<LibraryInfo> libraries) {
        ArrayList<LibraryDH> result = new ArrayList<>();
        for(LibraryInfo info : libraries)
            result.add(new LibraryDH(info));
        return result;
    }
}
