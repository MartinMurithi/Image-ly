package com.example.hdbackground.curated_model;

import java.io.Serializable;
import java.util.ArrayList;

public class PixelApiResponse implements Serializable {
    private int page;
    private int per_page;
    private ArrayList<Photos> photos;
    private String next_page;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public ArrayList<Photos> getPhotos() {
        return photos;
    }

    public String getNext_page() {
        return next_page;
    }
}
