package com.thinkmobiles.easyerp.data.model;

/**
 * Created by Lynx on 2/23/2017.
 */

public class LibraryInfo {

    public String name;
    public String version;
    public String author;
    public String license;
    public String link;

    public LibraryInfo(String name, String version, String author, String license, String link) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.license = license;
        this.link = link;
    }

}
