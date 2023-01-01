package com.example.hdbackground.services;

import com.example.hdbackground.curated_model.PixelApiResponse;
import com.example.hdbackground.curatedphotos.CuratedPhotos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CallPixelApi {

    @Headers({
            "Accept: application/json",
            "Authorization: "+ RetrofitInstance.API_KEY
    })
    @GET("curated")
    Call<PixelApiResponse> callCuratedApi(
            @Query("page") int page,
            @Query("per_page") int perPage
    );

}
