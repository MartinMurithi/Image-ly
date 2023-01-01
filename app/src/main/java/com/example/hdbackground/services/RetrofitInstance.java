package com.example.hdbackground.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String API_KEY = "563492ad6f917000010000018445b241bedd4fbc98731d7f220de1c3";
    private static final String baseUrl ="https://api.pexels.com/v1/";
    private static  Retrofit retrofit;

    public static synchronized Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
