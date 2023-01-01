package com.example.hdbackground.services;

import com.example.hdbackground.searchphotos.SearchPhotosModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchPixelApi {
    @Headers({
            "Accept: application/json",
            "Authorization: "+ RetrofitInstance.API_KEY
    })

    @GET("search")
    Call<SearchPhotosModel> searchPixelPhotos(
            @Query("query") String searchQuery,
            @Query("page") int page,
            @Query("per_page") int perPage

    );
}
