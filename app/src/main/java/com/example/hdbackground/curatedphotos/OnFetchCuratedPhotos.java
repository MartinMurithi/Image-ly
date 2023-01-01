package com.example.hdbackground.curatedphotos;

import com.example.hdbackground.curated_model.PixelApiResponse;


public interface OnFetchCuratedPhotos {
    void fetchCuratedPhotos(PixelApiResponse pixelApiResponse);

    void onFailure(String message);
}
