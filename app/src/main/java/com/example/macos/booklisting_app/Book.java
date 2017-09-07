package com.example.macos.booklisting_app;

/**
 * Created by macos on 07.09.2017.
 */

public class Book {
    public Book(int i, String tit, String des){
        imageSrc = i;
        title = tit;
        desc = des;
    }

    public int getImageSrc() {return imageSrc;}
    public String getTitle() {return title;}
    public String getDesc() {return desc;}

    private int imageSrc;
    private String title;
    private String desc;

}
