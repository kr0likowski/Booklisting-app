package com.example.macos.booklisting_app;

import java.net.URL;

/**
 * Created by macos on 07.09.2017.
 */

public class Book {
    public Book(String i, String tit, String des, URL site){
        imageSrc = i;
        title = tit;
        desc = des;
        url = site;
    }

    public String getImageSrc() {return imageSrc;}
    public String getTitle() {return title;}
    public String getDesc() {return desc;}

    private String imageSrc;
    private String title;
    private String desc;

    public URL getUrl() {
        return url;
    }

    private URL url;

}
