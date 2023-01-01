package com.example.hdbackground.curated_model;

import java.io.Serializable;

public class Photos implements Serializable {
    private int width;
    private int height;
    private String url;
    private String photographer_url;
    private int photographer_id;
    private int id;
    private String photographer;
    private String avg_color;
    private boolean liked;
    private String alt;
    private Src src;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getPhotographer_url() {
        return photographer_url;
    }

    public int getPhotographer_id() {
        return photographer_id;
    }

    public int getId() {
        return id;
    }

    public String getPhotographer() {
        return photographer;
    }

    public String getAvg_color() {
        return avg_color;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getAlt() {
        return alt;
    }

    public Src getSrc() {
        return src;
    }
}
