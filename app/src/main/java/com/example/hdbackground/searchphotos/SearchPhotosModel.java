package com.example.hdbackground.searchphotos;

import com.example.hdbackground.curated_model.Photos;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchPhotosModel implements Serializable {
    private int total_results;
    private int page;
    private int per_page;
    private ArrayList<Photos> photos;
    private String next_page;

    public int getTotal_results() {
        return total_results;
    }

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
