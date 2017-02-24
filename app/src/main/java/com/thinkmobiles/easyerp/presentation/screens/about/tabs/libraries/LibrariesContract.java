package com.thinkmobiles.easyerp.presentation.screens.about.tabs.libraries;

import com.thinkmobiles.easyerp.data.model.LibraryInfo;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LibraryDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 2/23/2017.
 */

public interface LibrariesContract {
    interface LibrariesView extends BaseView<LibrariesPresenter> {
        void displayLibraries(ArrayList<LibraryDH> libraryDHs);
        void openLink(String link);
    }
    interface LibrariesPresenter extends BasePresenter {
        void openGithubLink(String link);
    }
    interface LibrariesModel {
        ArrayList<LibraryInfo> getLibraries();
    }
}
