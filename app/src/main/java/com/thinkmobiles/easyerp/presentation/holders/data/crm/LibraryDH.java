package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.LibraryInfo;

/**
 * Created by Lynx on 2/23/2017.
 */

public class LibraryDH extends RecyclerDH {

    private LibraryInfo libraryInfo;

    public LibraryDH(LibraryInfo libraryInfo) {
        this.libraryInfo = libraryInfo;
    }

    public LibraryInfo getLibraryInfo() {
        return libraryInfo;
    }
}
