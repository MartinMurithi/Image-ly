package com.example.hdbackground.curatedphotos;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hdbackground.HomeFragment;
import com.example.hdbackground.curated_model.Photos;
import com.example.hdbackground.curated_model.PixelApiResponse;
import com.example.hdbackground.services.CallPixelApi;
import com.example.hdbackground.services.RetrofitInstance;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuratedPhotos extends AppCompatActivity implements Serializable {

    //Acts as the single source of truth - Repository

    private Call<PixelApiResponse> apiCall;
    private CallPixelApi callPixelApi;
    private int page;

    public void getPixelImages(OnFetchCuratedPhotos listener) {

        page = 1;

        callPixelApi = RetrofitInstance.getRetrofit().create(CallPixelApi.class);
        apiCall = callPixelApi.callCuratedApi(page, 78);
        apiCall.enqueue(new Callback<PixelApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<PixelApiResponse> call, @NonNull Response<PixelApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CuratedPhotos.this, "An error occurred " + response.message(), Toast.LENGTH_SHORT).show();
                } else {
                    listener.fetchCuratedPhotos(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixelApiResponse> call, @NonNull Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void nextPageCuratedImages(OnFetchCuratedPhotos listener) {

        HomeFragment.getNextFab().setOnClickListener(v -> {
            page++;
            callPixelApi = RetrofitInstance.getRetrofit().create(CallPixelApi.class);
            apiCall = callPixelApi.callCuratedApi(page, 78);
            apiCall.enqueue(new Callback<PixelApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<PixelApiResponse> call, @NonNull Response<PixelApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "An error occurred " + response.message(), Toast.LENGTH_SHORT).show();
                    } else {
                        listener.fetchCuratedPhotos(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PixelApiResponse> call, @NonNull Throwable t) {
                    t.getStackTrace();
                }
            });
        });
    }

    public void prevPageCuratedPhotos(OnFetchCuratedPhotos listener) {

        HomeFragment.getPrevFab().setOnClickListener(v -> {
            page --;
            callPixelApi = RetrofitInstance.getRetrofit().create(CallPixelApi.class);
            apiCall = callPixelApi.callCuratedApi(page, 78);
            apiCall.enqueue(new Callback<PixelApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<PixelApiResponse> call, @NonNull Response<PixelApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(CuratedPhotos.this, "An error occurred " + response.message(), Toast.LENGTH_SHORT).show();
                    } else {
                        listener.fetchCuratedPhotos(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PixelApiResponse> call, @NonNull Throwable t) {
                    t.getStackTrace();
                }
            });
        });
    }

}